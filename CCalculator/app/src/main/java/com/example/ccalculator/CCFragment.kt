package com.example.ccalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccalculator.databinding.CcFragmentBinding

class CCFragment : Fragment() {
    private var _binding: CcFragmentBinding? = null
    private val binding: CcFragmentBinding get() = _binding!!
    
    private var _adapter: CCAdapter? = null
    private val adapter: CCAdapter get() = _adapter!!

    private var buttons = arrayListOf<Buttons>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CcFragmentBinding.inflate(layoutInflater)
        _adapter = CCAdapter()

        val recyclerView = binding.fragmentRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textField.hint = "Enter sequence..."

        buttons.add(Buttons.FIFTH)
        buttons.add(Buttons.FOURTH)
        buttons.add(Buttons.THIRD)
        buttons.add(Buttons.SECOND)
        buttons.add(Buttons.FIRST)

        adapter.setData(buttons)

        binding.textField.setOnClickListener {
            onButtonClick("df")
        }

    }

    fun onButtonClick(button: String) {
    }

}