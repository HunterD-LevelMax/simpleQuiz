package com.template

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.template.fragments.*
import kotlinx.android.synthetic.main.activity_quest_room.*
import java.util.concurrent.TimeUnit

class QuestRoom : AppCompatActivity() {

    var user: User = User(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_room)

        startQuiz1()
        countDown()
        checkHealth()
    }

    fun checkHealth() {
        var health = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getString("USER_HEALTH", null)
            ?.toInt()

        var user: User = User(health)

        when (user.health) {
            2 -> hp1.setImageResource(R.drawable.icon2)
            1 -> hp2.setImageResource(R.drawable.icon2)
            0 -> hp3.setImageResource(R.drawable.icon2)
        }

        if (user.health == 0) {
            showDialogWinLose("lose")
        }

    }

    private fun startQuiz1() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.questContainer, Question1()).commit()
    }

    //    180000
    private fun countDown() {
        val countDownTimer = object : CountDownTimer(180000, 1000) {
            override fun onTick(p0: Long) {
                val millis: Long = p0
                val hms = String.format(
                    "%02d:%02d",
                    (TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))),
                    (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millis)
                    ))
                )
                timer.setText(hms);//set text
            }

            override fun onFinish() {
                val sharedPreferences: SharedPreferences? =
                    getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
                editor?.apply {
                    putString("USER_HEALTH", "3")
                }?.apply()

                showDialogWinLose("lose")
            }
        }
        countDownTimer.start()
    }


    fun showDialogWinLose(win:String) {
        class MyCustomDialog : DialogFragment() {
            override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {
                setFinishOnTouchOutside(false)
                isCancelable = false
                window?.setBackgroundDrawable(ColorDrawable(Color.WHITE));

                return if(win == "win"){
                    inflater.inflate(R.layout.win_alert, container, false)
                }else{
                    inflater.inflate(R.layout.lose_alert, container, false)
                }
            }
        }

        var dialog: MyCustomDialog = MyCustomDialog()
        dialog.dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show(supportFragmentManager, "")
        val handler = android.os.Handler()
        handler.postDelayed(
            {
                var health = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
                    .getString("USER_HEALTH", null)
                    ?.toInt()

                    if (health in 0..3) {
                        replaceActivity(MainActivity())
                    }
            }, 3000
        )
    }

    override fun onStop() {
        super.onStop()
    }

}






