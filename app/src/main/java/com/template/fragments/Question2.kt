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
import com.template.databinding.FragmentQuestion2Binding

class Question2 : Fragment() {
    private lateinit var mBinding: FragmentQuestion2Binding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentQuestion2Binding.inflate(layoutInflater)

        //load health
        var health = this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            ?.getString("USER_HEALTH", null)
            ?.toInt()
        var user: User = User(health)

        mBinding.quest2Text.text =
            "2. What was the first city to host the Olympics twice?"

        mBinding.answer21.setOnClickListener {
            user.health = user.health?.minus(1)

            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question3())
            fr?.commit()
            showSnackBar(mBinding.answer24.text.toString())

            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()

            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer22.setOnClickListener {
            user.health = user.health?.minus(1)


            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question3())
            fr?.commit()
            showSnackBar(mBinding.answer24.text.toString())

            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()

            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer23.setOnClickListener {
            showSnackBar(mBinding.answer24.text.toString())
            user.health = user.health?.minus(1)

            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question3())
            fr?.commit()

            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()

            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer24.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question3())
            fr?.commit()
            showSnackBar("Wright answer")
        }

        return mBinding.root

        showSnackBar(health.toString())
    }

    @SuppressLint("ResourceAsColor")
    private fun showSnackBar(message: String) {
        val snack = Snackbar.make(mBinding.mainQuest2, message, Snackbar.LENGTH_SHORT)
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
