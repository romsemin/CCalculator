package com.example.ccalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccalculator.databinding.CcFragmentBinding

class CCFragment : Fragment() {
    private var onItemClick: ((String) -> Unit)? = { binding.testTestView.text = it }

    private var _binding: CcFragmentBinding? = null
    private val binding: CcFragmentBinding get() = _binding!!

    private var _adapter: CCMAdapter? = null
    private val adapter: CCMAdapter get() = _adapter!!

    private val data : List<DataModel> = listOf(
        DataModel.ButtonsRow("AC", null, null, "/", null),
        DataModel.ButtonsRow("7", "8", "9", "*", null),
        DataModel.ButtonsRow("4", "5", "6", "-", null),
        DataModel.ButtonsRow("1", "2", "3", "+", null),
        DataModel.ButtonsRow("0", ".",  null, "=", null)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CcFragmentBinding.inflate(layoutInflater)
        _adapter = CCMAdapter(onItemClick)

        val recyclerView = binding.fragmentRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setData(data)
    }
}