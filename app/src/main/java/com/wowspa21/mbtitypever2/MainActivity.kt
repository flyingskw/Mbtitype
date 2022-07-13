package com.wowspa21.mbtitypever2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this)
        var mainAdView: AdView = findViewById(R.id.adView)
        var adRequest = AdRequest.Builder().build()
        mainAdView.loadAd(adRequest)
    }

    fun clickSubmit(view: View) {
        var editText: EditText = findViewById(R.id.nameEditText)
        var userName = editText.text.toString()

        var barQ1: SeekBar = findViewById(R.id.barQ1)
        var answer1 = barQ1.progress
        var barQ2: SeekBar = findViewById(R.id.barQ2)
        var answer2 = barQ2.progress
        var barQ3: SeekBar = findViewById(R.id.barQ3)
        var answer3 = barQ3.progress
        var barQ4: SeekBar = findViewById(R.id.barQ4)
        var answer4 = barQ4.progress

        var scoreSF = (answer1 + answer4) / 2
        var scorePE = (answer2 + answer3) / 2
        Toast.makeText(this, "${scoreSF}, ${scorePE}", Toast.LENGTH_SHORT).show()
        var intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("UserName", userName)
        intent.putExtra("TypeKey", answer1)
        intent.putExtra("TypeSF", scoreSF)
        intent.putExtra("TypePE", scorePE)
        startActivity(intent)

        FirebaseAnalytics.getInstance(this).logEvent("click_submit") {
            param("answer1", answer1.toLong())
            param("answer2", answer2.toLong())
            param("answer3", answer3.toLong())
            param("answer4", answer4.toLong())
        }

        //값 초기화
        barQ1.progress = 3
        barQ2.progress = 3
        barQ3.progress = 3
        barQ4.progress = 3
        editText.setText("")
    }

}