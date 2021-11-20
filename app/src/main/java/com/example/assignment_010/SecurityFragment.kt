package com.example.assignment_010

import android.util.TypedValue
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.assignment_010.databinding.SecurityFragmentBinding
import com.example.assignment_010.extensions.showSnackBar
import com.example.assignment_010.extensions.showToast
import com.google.android.material.snackbar.Snackbar


class SecurityFragment :
    BaseFragment<SecurityFragmentBinding,
            SecurityViewModel>(SecurityFragmentBinding::inflate) {


    private val pass = "0934"
    private var newPass = ""


    private val buttonAdapter = ButtonRecyclerAdapter()
    private val layoutManager = GridLayoutManager(context, 3)

    private val buttonList = mutableListOf(
        ButtonDigits(number = 1, type = "num"),
        ButtonDigits(number = 2, type = "num"),
        ButtonDigits(number = 3, type = "num"),
        ButtonDigits(number = 4, type = "num"),
        ButtonDigits(number = 5, type = "num"),
        ButtonDigits(number = 6, type = "num"),
        ButtonDigits(number = 7, type = "num"),
        ButtonDigits(number = 8, type = "num"),
        ButtonDigits(number = 9, type = "num"),
        ButtonDigits(type = "finger", icon = R.drawable.ic_baseline_fingerprint_24),
        ButtonDigits(number = 0, type = "num"),
        ButtonDigits(type = "del", icon = R.drawable.ic_delete),
    )


    override fun getViewModel() = SecurityViewModel::class.java

    override var useSharedViewModel = false

    override fun start() {


        setRecycler()
        setClick()


    }


    private fun setRecycler() {


        buttonAdapter.setData(buttonList)
        binding.recycler.adapter = buttonAdapter
        binding.recycler.layoutManager = layoutManager


    }



    private fun setClick() {

        var counter = 0


        buttonAdapter.onButtonClick = {
            if (it.type == "finger") {
                view?.showSnackBar("fingerprint")

            } else if (it.type == "del" && counter == 0) {
                view?.showSnackBar("first enter number")

            } else if (it.type == "del" && counter > 0) {
                counter -= 1
                newPass = newPass.dropLast(1)
                dotColor(counter)
                view?.showSnackBar("code - $newPass counter - $counter")

            }
            if (it.type == "num") {

                newPass += it.number.toString()
                counter += 1

                dotColor(counter)

                view?.showSnackBar("code - $newPass counter - $counter")

            }


            if (counter == 4) {
                if (newPass == pass) {
                    counter = 0
                    newPass = ""
                    view?.showSnackBar("CORRECT PASSWORD")

                } else {
                    counter = 0
                    newPass = ""
                    dotColor(counter)
                    view?.showSnackBar("WRONG PASSWORD")
                }
            }


        }


    }

    private fun dotColor(cnt:Int){
        when (cnt) {
            1 -> {
                binding.dot1.setImageResource(R.drawable.dot_green)
                binding.dot2.setImageResource(R.drawable.dot_grey)
                binding.dot3.setImageResource(R.drawable.dot_grey)
                binding.dot4.setImageResource(R.drawable.dot_grey)
            }
            2 ->{
                binding.dot1.setImageResource(R.drawable.dot_green)
                binding.dot2.setImageResource(R.drawable.dot_green)
                binding.dot3.setImageResource(R.drawable.dot_grey)
                binding.dot4.setImageResource(R.drawable.dot_grey)
            }
            3 -> {
                binding.dot1.setImageResource(R.drawable.dot_green)
                binding.dot2.setImageResource(R.drawable.dot_green)
                binding.dot3.setImageResource(R.drawable.dot_green)
                binding.dot4.setImageResource(R.drawable.dot_grey)
            }
            4 -> {
                binding.dot1.setImageResource(R.drawable.dot_green)
                binding.dot2.setImageResource(R.drawable.dot_green)
                binding.dot3.setImageResource(R.drawable.dot_green)
                binding.dot4.setImageResource(R.drawable.dot_green)
            }
            0 -> {
                binding.dot1.setImageResource(R.drawable.dot_grey)
                binding.dot2.setImageResource(R.drawable.dot_grey)
                binding.dot3.setImageResource(R.drawable.dot_grey)
                binding.dot4.setImageResource(R.drawable.dot_grey)
            }

        }
    }
}

/*  val outValue = TypedValue()
        context?.theme?.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        cardView.setBackgroundResource(outValue.resourceId)*/


// android:background="?android:attr/selectableItemBackground"



