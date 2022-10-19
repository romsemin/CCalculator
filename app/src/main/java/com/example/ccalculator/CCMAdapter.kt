package com.example.ccalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccalculator.databinding.RvItemBinding
import com.example.ccalculator.databinding.RvTextViewBinding


class CCMAdapter(val onClick: ((String) -> Unit)?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val adapterData = mutableListOf<DataModel>()
    var adapterText: String = ""

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == TYPE_BUTTONS) {
            val binding = RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            ButtonsViewHolder(binding)
        } else {
            val binding = RvTextViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            TextViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(adapterData[position]) {
            is DataModel.ButtonsRow -> TYPE_BUTTONS
            is DataModel.TextView -> TYPE_TEXT
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (TYPE_BUTTONS == getItemViewType(position)) {
            val buttonsViewHolder = holder as ButtonsViewHolder
            val buttonsItem = adapterData[position] as DataModel.ButtonsRow
            buttonsViewHolder.bind(buttonsItem)
            return
        }
        val textViewHolder = holder as TextViewHolder
        val textItem = adapterData[position] as DataModel.TextView
        textViewHolder.bind(textItem)
    }

    override fun getItemCount(): Int = adapterData.size

    fun setData(data: List<DataModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ButtonsViewHolder(
        private val binding: RvItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rvItem: DataModel.ButtonsRow) {
            binding.rwRowFirstButton.text = rvItem.firstButton
            binding.rwRowSecondButton.text = rvItem.secondButton
            binding.rwRowThirdButton.text = rvItem.thirdButton
            binding.rwRowFourthButton.text = rvItem.fourthButton
            binding.rwTextView.text = rvItem.textField
            if (rvItem.firstButton == null) {
                binding.rwRowSecondButton.visibility = View.GONE
            }

            if (rvItem.secondButton == null) {
                binding.rwRowSecondButton.visibility = View.GONE
            }

            if (rvItem.thirdButton == null) {
                binding.rwRowThirdButton.visibility = View.GONE
            }

            if (rvItem.fourthButton == null) {
                binding.rwRowFourthButton.visibility = View.GONE
            }

            if (rvItem.textField == null) {
                binding.rwTextView.visibility = View.GONE
            }

            binding.rwRowFirstButton.setOnClickListener {
                when (rvItem.firstButton ) {
                    "AC" -> {
                        adapterText = ""
                        onClick?.let { text -> text(adapterText) }
                    } else -> {
                        adapterText += rvItem.firstButton
                        onClick?.let { text -> text(adapterText) }
                    }
                }
            }

            binding.rwRowSecondButton.setOnClickListener {
                adapterText += rvItem.secondButton
                onClick?.let { text -> text(adapterText) }
            }

            binding.rwRowThirdButton.setOnClickListener {
                adapterText += rvItem.thirdButton
                onClick?.let { text -> text(adapterText) }
            }

            binding.rwRowFourthButton.setOnClickListener {
                if (rvItem.fourthButton == "=") {
                    onClick?.let { text -> text(Calculation.calculateResult(adapterText)) }
                    adapterText = ""
                } else {
                    adapterText += rvItem.fourthButton
                    onClick?.let { text -> text(adapterText) }
                }
            }
        }
    }

    inner class TextViewHolder(
        private val binding: RvTextViewBinding
        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rvItem: DataModel.TextView) {
            binding.textView.text = rvItem.text
        }
    }

    companion object {
        private const val TYPE_BUTTONS = 0
        private const val TYPE_TEXT = 1
    }
}