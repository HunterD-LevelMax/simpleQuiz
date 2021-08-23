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
import com.template.R
import com.template.User
import com.template.databinding.FragmentQuestion1Binding

class Question1 : Fragment() {
    private lateinit var mBinding: FragmentQuestion1Binding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentQuestion1Binding.inflate(layoutInflater)

        var user: User = User(3)

        val sharedPreferences: SharedPreferences? =
            this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.apply {
            putString("USER_HEALTH", user?.health.toString())
        }?.apply()

        mBinding.quest1Text.text =
            "1. What is the very center of a target called in archery or darts?"

        mBinding.answer11.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question2())
            fr?.commit()
            showSnackBar("Wright answer")
        }

        mBinding.answer12.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question2())
            fr?.commit()
            showSnackBar(mBinding.answer12.text.toString())
            user.health = user.health?.minus(1)

            showSnackBar(user.health.toString())


            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()

            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer13.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question2())
            fr?.commit()
            showSnackBar(mBinding.answer13.text.toString())
            user.health = user.health?.minus(1)

            showSnackBar(user.health.toString())


            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()

            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer14.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question2())
            fr?.commit()
            showSnackBar(mBinding.answer14.text.toString())
            user.health = user.health?.minus(1)

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
        val snack = Snackbar.make(mBinding.mainQuest1, message, Snackbar.LENGTH_SHORT)
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
