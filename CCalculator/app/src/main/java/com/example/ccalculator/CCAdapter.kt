package com.example.ccalculator

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccalculator.databinding.ActivityMainBinding
import com.example.ccalculator.databinding.RvItemBinding

class CCAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClick: ((String) -> Unit)? = null
    private var buttonsList: MutableList<Buttons> = mutableListOf()

    fun setData(items: ArrayList<Buttons>) {
        buttonsList.clear()
        buttonsList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CCViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as CCViewHolder
        val item = buttonsList[position]
        holder.itemView.setOnClickListener(View.OnClickListener {

        })


        itemViewHolder.bind(item.properties)
    }

    override fun getItemCount(): Int {
        return buttonsList.size
    }

    inner class CCViewHolder(private val binding: RvItemBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root), OnClickListener {
        fun bind(rvItem: ButtonsRow) {
            binding.rwRowFirstButton.text = rvItem.firstButton
            binding.rwRowSecondButton.text = rvItem.secondButton
            binding.rwRowThirdButton.text = rvItem.thirdButton
            binding.rwRowFourthButton.text = rvItem.fourthButton

            if (binding.rwRowSecondButton.text == "") {
                binding.rwRowSecondButton.visibility = View.GONE
            }

            if (binding.rwRowThirdButton.text == "") {
                binding.rwRowThirdButton.visibility = View.GONE
            }

            if (binding.rwRowFourthButton.text == "") {
                binding.rwRowFourthButton.visibility = View.GONE
            }
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

}