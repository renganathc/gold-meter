package com.market.goldmeter

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import java.text.SimpleDateFormat
import java.util.Date

class SplashScreenActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        this.window.statusBarColor = Color.parseColor("#FF5246")

        var device = android.os.Build.MANUFACTURER

        val database = FirebaseDatabase.getInstance().getReference("/Users/${device} ${android.os.Build.MODEL}/${SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Date())}")

        database.setValue(Info( SimpleDateFormat("HH:mm").format(Date()), SimpleDateFormat("dd-MM-yyyy").format(Date())))


        Handler().postDelayed({
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("SPLASH_SCREEN_INTENT", "sound")
            startActivity(intent)
            finish()
        }, 100)

    }
}

class Info(val time : String, val date : String) {
    constructor() : this("","",)
}