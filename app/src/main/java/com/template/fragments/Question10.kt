package com.template.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.template.QuestRoom
import com.template.User
import com.template.databinding.FragmentQuestion10Binding

class Question10 : Fragment() {
    private lateinit var mBinding: FragmentQuestion10Binding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentQuestion10Binding.inflate(layoutInflater)


        var health = this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            ?.getString("USER_HEALTH", null)
            ?.toInt()

        var user: User? = User(health)

        mBinding.quest10Text.text =
            "10. How many rings are there on the Olympic flag?"

        mBinding.answer101.setOnClickListener {
            user?.health = user?.health?.minus(1)
            showSnackBar(user?.health.toString())
            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()
            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer102.setOnClickListener {
            showSnackBar(mBinding.answer102.text.toString())
            user?.health = user?.health?.minus(1)
            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()
            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer103.setOnClickListener {
            showSnackBar("Wright answer")
            (activity as QuestRoom).showDialogWinLose("win")
        }

        mBinding.answer104.setOnClickListener {
            showSnackBar(mBinding.answer104.text.toString())
            user?.health = user?.health?.minus(1)
            showSnackBar(user?.health.toString())
            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()
            (activity as QuestRoom).checkHealth()

        }
        return mBinding.root
    }

    @SuppressLint("ResourceAsColor")
    private fun showSnackBar(message: String) {
        val snack = Snackbar.make(mBinding.mainQuest10, message, Snackbar.LENGTH_SHORT)
        snack.show()
    }

    override fun onResume() {
        super.onResume()
        if (view == null) {
            return
        }
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { v, keyCode, event ->
            event.action === KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK
        }
    }


}
