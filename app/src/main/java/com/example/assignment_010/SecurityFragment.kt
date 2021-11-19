package com.example.assignment_010

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment_010.databinding.SecurityFragmentBinding


class SecurityFragment :
    BaseFragment<SecurityFragmentBinding,
            SecurityViewModel>(SecurityFragmentBinding::inflate) {


    private val pass = "0934"
    private var newPass = ""


    private val buttonAdapter = ButtonRecyclerAdapter()
    private val layoutManager = GridLayoutManager(context, 3)

    private val buttonList = mutableListOf(
        ButtonDigits(number = 1),
        ButtonDigits(number = 2),
        ButtonDigits(number = 3),
        ButtonDigits(number = 4),
        ButtonDigits(number = 5),
        ButtonDigits(number = 6),
        ButtonDigits(number = 7),
        ButtonDigits(number = 8),
        ButtonDigits(number = 9),
        ButtonDigits(number = 10, icon = R.drawable.ic_baseline_fingerprint_24),
        ButtonDigits(number = 0),
        ButtonDigits(number = 11, icon = R.drawable.ic_baseline_arrow_back_24),
    )


    override fun getViewModel() = SecurityViewModel::class.java

    override var useSharedViewModel = false

    override fun start() {


        setRecycler()


    }


    private fun setRecycler() {


        buttonAdapter.setData(buttonList)
        binding.recycler.adapter = buttonAdapter
        binding.recycler.layoutManager = layoutManager

        var counter = 0

        buttonAdapter.onButtonClick = {
            newPass += it.number.toString()
            counter += 1
            if (counter==4){
                if (newPass == pass){
                    counter=0
                    newPass=""
                    Toast.makeText(context, "correct password", Toast.LENGTH_SHORT).show()
                }else{
                    counter=0
                    newPass=""
                    Toast.makeText(context, "incorrect password", Toast.LENGTH_SHORT).show()}
            }
        }






    }
}



