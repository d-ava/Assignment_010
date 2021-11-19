package com.example.assignment_010

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_010.databinding.ItemBinding

typealias OnButtonClick = (btn: ButtonDigits) -> Unit

class ButtonRecyclerAdapter : RecyclerView.Adapter<ButtonRecyclerAdapter.ButtonViewHolder>() {


    lateinit var onButtonClick: OnButtonClick

    private val list = mutableListOf<ButtonDigits>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<ButtonDigits>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        return ButtonViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
       holder.onBind()
    }

    override fun getItemCount() = list.size

    inner class ButtonViewHolder(private var binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var btn:ButtonDigits

        fun onBind(){
            binding.root.setOnClickListener(this)
            btn = list[adapterPosition]
            binding.btnText.text = btn.number.toString()
            if (btn.number == 10 ){
                binding.btnIcon.visibility= View.VISIBLE
                binding.btnIcon.setImageResource(btn.icon!!)
                binding.btnText.visibility=View.INVISIBLE
            }

            if (btn.number == 11 ){
                binding.btnIcon.visibility= View.VISIBLE
                binding.btnIcon.setImageResource(btn.icon!!)
                binding.btnText.visibility=View.INVISIBLE
            }

        }


        override fun onClick(p0: View?) {
            onButtonClick.invoke(list[adapterPosition])
        }


    }


}