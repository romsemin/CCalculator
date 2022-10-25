package com.example.ccalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccalculator.databinding.CalculatorFragmentBinding

class CalculatorFragment : Fragment() {
    private lateinit var calculatorRepository: CalculatorRepository

    private var onItemClick: ((String) -> Unit)? = { binding.resultTextView.text = it }
    private lateinit var adapter: CalculatorAdapter

    private var _binding: CalculatorFragmentBinding? = null
    private val binding: CalculatorFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CalculatorFragmentBinding.inflate(layoutInflater)
        adapter = CalculatorAdapter(onItemClick)
        calculatorRepository = CalculatorRepository()

        val recyclerView = binding.fragmentRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setData(calculatorRepository.getButtonsList())
    }
}