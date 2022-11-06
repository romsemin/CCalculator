package com.example.ccalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccalculator.databinding.ButtonRowItemBinding

class CalculatorAdapter(
    val onClick: ((String) -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var buttonsList: MutableList<ButtonRow> = mutableListOf()
    var input: String = ""

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ButtonRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ButtonsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val buttonsHolder = holder as ButtonsViewHolder
        val item = buttonsList[position]
        buttonsHolder.bind(item)
    }

    override fun getItemCount(): Int = buttonsList.size

    fun setButtons(buttons: MutableList<ButtonRow>) {
        buttonsList.clear()
        buttonsList.addAll(buttons)
        notifyDataSetChanged()
    }

    inner class ButtonsViewHolder(
        private val binding: ButtonRowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(row: ButtonRow) {
            binding.firstButton.text = row.firstButton
            binding.secondButton.text = row.secondButton
            binding.thirdButton.text = row.thirdButton
            binding.fourthButton.text = row.fourthButton

            if (row.secondButton == null) {
                binding.secondButton.visibility = View.GONE
            }

            if (row.thirdButton == null) {
                binding.thirdButton.visibility = View.GONE
            }

            binding.firstButton.setOnClickListener {
                when (row.firstButton) {
                    "AC" -> {
                        input = ""
                        onClick(input)
                    }
                    else -> {
                        input = input.plus(row.firstButton)
                        onClick(input)
                    }
                }
            }

            binding.secondButton.setOnClickListener {
                input = input.plus(row.secondButton)
                onClick(input)
            }

            binding.thirdButton.setOnClickListener {
                input = input.plus(row.thirdButton)
                onClick(input)
            }

            binding.fourthButton.setOnClickListener {
                when (row.fourthButton) {
                    "=" -> {
                        onClick(Calculation.calculateResult(input))
                        input = ""
                    }
                    else -> {
                        input = input.plus(row.fourthButton)
                        onClick(input)
                    }
                }
            }
        }
    }
}