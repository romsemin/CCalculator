package com.example.ccalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccalculator.databinding.CalculatorButtonsRowItemBinding

class CalculatorAdapter(
    val onClick: ((String) -> Unit)?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val adapterData = mutableListOf<ButtonsRowDataModel>()
    var adapterInputText: String = ""

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = CalculatorButtonsRowItemBinding.inflate(
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
        val buttonsViewHolder = holder as ButtonsViewHolder
        val buttonsItem = adapterData[position] as ButtonsRowDataModel
        buttonsViewHolder.bind(buttonsItem)
        return
    }

    override fun getItemCount(): Int = adapterData.size

    fun setData(data: List<ButtonsRowDataModel>) {
        adapterData.clear()
        adapterData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ButtonsViewHolder(
        private val binding: CalculatorButtonsRowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rvItem: ButtonsRowDataModel) {
            binding.rwRowFirstButton.text = rvItem.firstButton
            binding.rwRowSecondButton.text = rvItem.secondButton
            binding.rwRowThirdButton.text = rvItem.thirdButton
            binding.rwRowFourthButton.text = rvItem.fourthButton

            if (rvItem.secondButton == null) {
                binding.rwRowSecondButton.visibility = View.GONE
            }

            if (rvItem.thirdButton == null) {
                binding.rwRowThirdButton.visibility = View.GONE
            }

            binding.rwRowFirstButton.setOnClickListener {
                if (rvItem.firstButton == "AC" ) {
                    adapterInputText = ""
                    onClick?.let { it(adapterInputText) }
                } else {
                    adapterInputText = adapterInputText.plus(rvItem.firstButton)
                    onClick?.let { it(adapterInputText) }
                }
            }


            binding.rwRowSecondButton.setOnClickListener {
                adapterInputText = adapterInputText.plus(rvItem.secondButton)
                onClick?.let { it(adapterInputText) }
            }

            binding.rwRowThirdButton.setOnClickListener {
                adapterInputText = adapterInputText.plus(rvItem.thirdButton)
                onClick?.let { it(adapterInputText) }
            }

            binding.rwRowFourthButton.setOnClickListener {
                if (rvItem.fourthButton == "=") {
                    onClick?.let { it(Calculation.calculateResult(adapterInputText)) }
                    adapterInputText = ""
                } else {
                    adapterInputText = adapterInputText.plus(rvItem.fourthButton)
                    onClick?.let { it(adapterInputText) }
                }
            }
        }
    }
}