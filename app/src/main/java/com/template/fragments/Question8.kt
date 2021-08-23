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
import com.template.databinding.FragmentQuestion8Binding

class Question8 : Fragment() {
    private lateinit var mBinding: FragmentQuestion8Binding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentQuestion8Binding.inflate(layoutInflater)

        //load health
        var health = this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            ?.getString("USER_HEALTH", null)
            ?.toInt()
        var user: User = User(health)

        mBinding.quest8Text.text =
            " 8. What sport is called the “sport of kings”"

        mBinding.answer81.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question9())
            fr?.commit()
            showSnackBar("Wright answer")
        }

        mBinding.answer82.setOnClickListener {
            user.health = user.health?.minus(1)

            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question9())
            fr?.commit()
            showSnackBar(mBinding.answer81.text.toString())

            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()

            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer83.setOnClickListener {
            user.health = user.health?.minus(1)

            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question9())
            fr?.commit()
            showSnackBar(mBinding.answer81.text.toString())

            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()

            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer84.setOnClickListener {
            user.health = user.health?.minus(1)

            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question9())
            fr?.commit()
            showSnackBar(mBinding.answer81.text.toString())

            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()
            (activity as QuestRoom).checkHealth()
        }

        return mBinding.root

        showSnackBar(health.toString())
    }

    @SuppressLint("ResourceAsColor")
    private fun showSnackBar(message: String) {
        val snack = Snackbar.make(mBinding.mainQuest8, message, Snackbar.LENGTH_SHORT)
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
