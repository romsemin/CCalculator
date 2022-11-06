package com.example.ccalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ccalculator.databinding.CalculatorFragmentBinding

class CalculatorFragment : Fragment() {
    private lateinit var binding: CalculatorFragmentBinding
    private lateinit var adapter: CalculatorAdapter
    private val calculatorRepository: CalculatorRepository =  CalculatorRepository()

    private val onItemClick: ((String) -> Unit) = { binding.resultText.text = it }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CalculatorFragmentBinding.inflate(layoutInflater)
        adapter = CalculatorAdapter(onItemClick)

        val recyclerView = binding.calculatorRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setButtons(calculatorRepository.getButtonsList())
    }
}