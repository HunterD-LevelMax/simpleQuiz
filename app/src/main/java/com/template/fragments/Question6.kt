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
import com.template.databinding.FragmentQuestion6Binding

class Question6 : Fragment() {
    private lateinit var mBinding: FragmentQuestion6Binding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentQuestion6Binding.inflate(layoutInflater)

        //load health
        var health = this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            ?.getString("USER_HEALTH", null)
            ?.toInt()
        var user: User = User(health)

        mBinding.quest6Text.text =
            "6. When an Olympic athlete wins first place, what color medal do they get?"

        mBinding.answer61.setOnClickListener {
            user.health = user.health?.minus(1)

            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question7())
            fr?.commit()
            showSnackBar(mBinding.answer62.text.toString())

            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()

            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer62.setOnClickListener {
            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question7())
            fr?.commit()
            showSnackBar("Wright answer")
        }

        mBinding.answer63.setOnClickListener {
            user.health = user.health?.minus(1)

            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question7())
            fr?.commit()
            showSnackBar(mBinding.answer62.text.toString())

            val sharedPreferences: SharedPreferences? =
                this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.apply {
                putString("USER_HEALTH", user?.health.toString())
            }?.apply()

            (activity as QuestRoom).checkHealth()
        }

        mBinding.answer64.setOnClickListener {
            user.health = user.health?.minus(1)

            var fr = getFragmentManager()?.beginTransaction()
            fr?.replace(R.id.questContainer, Question7())
            fr?.commit()
            showSnackBar(mBinding.answer62.text.toString())

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
        val snack = Snackbar.make(mBinding.mainQuest6, message, Snackbar.LENGTH_SHORT)
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
