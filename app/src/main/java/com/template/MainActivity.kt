package com.template

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonPlay: ImageButton = findViewById(R.id.imageButtonPlay)
        var buttonShare: ImageButton = findViewById(R.id.imageButtonShare)


        val sharedPreferences: SharedPreferences? =
            this?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.apply {
            putString("USER_HEALTH", "3")
        }?.apply()

        buttonPlay.setOnClickListener {
            val intent = Intent(this, QuestRoom::class.java)
            startActivity(intent)
            this.finish()
        }

        buttonShare.setOnClickListener {
            ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Chooser title")
                .setText("http://play.google.com/store/apps/details?id=" + this.getPackageName())
                .startChooser();
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun showSnackBar(message: String) {
        val snack = Snackbar.make(main, message, Snackbar.LENGTH_LONG)
        snack.show()
    }

    override fun onBackPressed() {
        //don't exit
    }

}