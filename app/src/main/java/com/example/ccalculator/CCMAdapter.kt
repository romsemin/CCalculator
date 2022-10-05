package com.example.ccalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccalculator.databinding.RvItemBinding
import com.example.ccalculator.databinding.RvTextViewBinding


class CCMAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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

//        val layout = when (viewType) {
//            TYPE_BUTTONS -> R.layout.rv_item
//            TYPE_EDIT_TEXT -> R.layout.rv_edit_text
//            else -> throw java.lang.IllegalArgumentException("Invalid type")
//        }
//
//        val view = LayoutInflater
//            .from(parent.context)
//            .inflate(layout, parent, false)
//
//        return CCMAdapterViewHolder(view)
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

//    inner class CCMAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private fun bindButtons(item: DataModel.ButtonsRow) {
//            itemView.findViewById<AppCompatButton>(R.id.rw_row_first_button).text = item.firstButton
//            itemView.findViewById<AppCompatButton>(R.id.rw_row_second_button).text =
//                item.secondButton
//            itemView.findViewById<AppCompatButton>(R.id.rw_row_third_button).text = item.thirdButton
//            itemView.findViewById<AppCompatButton>(R.id.rw_row_fourth_button).text =
//                item.fourthButton
//        }
//
//        private fun bindEditTextView(item: DataModel.TextView) {
//            itemView.findViewById<AppCompatEditText>(R.id.rw_edit_text).setText(
//                itemView.findViewById<AppCompatEditText>(R.id.rw_edit_text).text.toString() + item.text.toString())
//        }
//
//        fun bind(dataModel: DataModel) {
//            when (dataModel) {
//                is DataModel.ButtonsRow -> bindButtons(dataModel)
//                is DataModel.TextView -> bindEditTextView(dataModel)
//                else -> {}
//            }
//        }
//    }

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
                    "AC" -> adapterText = ""
                    else -> adapterText += rvItem.firstButton
                }
                println(adapterText)
            }

            binding.rwRowSecondButton.setOnClickListener {
                adapterText += rvItem.secondButton
            }

            binding.rwRowThirdButton.setOnClickListener {
                adapterText += rvItem.thirdButton
            }

            binding.rwRowFourthButton.setOnClickListener {
                if (rvItem.fourthButton == "=") {
                    Calculation.calculateResult(adapterText)
                    adapterText = ""
                } else {
                    adapterText += rvItem.fourthButton
                }
                println(adapterText)
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