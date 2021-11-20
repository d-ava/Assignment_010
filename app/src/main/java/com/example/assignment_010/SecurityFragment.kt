package com.example.assignment_010

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
        ButtonDigits(type = "del", icon = R.drawable.ic_baseline_arrow_back_24),
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
                view?.showSnackBar("code - $newPass counter - $counter")

            }
            if (it.type == "num") {

                newPass += it.number.toString()
                counter += 1

                when (counter) {
                    1 -> binding.dot1.setImageResource(R.drawable.dot_green)
                    2 -> binding.dot2.setImageResource(R.drawable.dot_green)
                    3 -> binding.dot3.setImageResource(R.drawable.dot_green)
                    4 -> binding.dot4.setImageResource(R.drawable.dot_green)

                }
                view?.showSnackBar("code - $newPass counter - $counter")

            }


            if (counter == 4) {
                if (newPass == pass) {
                    counter = 0
                    newPass = ""
                    view?.showToast("correct password")

                } else {
                    counter = 0
                    newPass = ""
                    binding.dot1.setImageResource(R.drawable.dot_grey)
                    binding.dot2.setImageResource(R.drawable.dot_grey)
                    binding.dot3.setImageResource(R.drawable.dot_grey)
                    binding.dot4.setImageResource(R.drawable.dot_grey)
                    view?.showToast("incorrect password")
                }
            }


        }


    }
}



