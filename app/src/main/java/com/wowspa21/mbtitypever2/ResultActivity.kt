package com.wowspa21.mbtitypever2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var resultTextView: TextView = findViewById(R.id.resultText)
        var userName = intent.getStringExtra("UserName")
        var scoreSF = intent.getIntExtra("TypeSF", -1)
        var scorePE = intent.getIntExtra("TypePE", -1)
        if (scoreSF < 3) {
            if (scorePE < 3) {
                //FE 타입
                FirebaseAnalytics.getInstance(this).setUserProperty("user_test_type", "FE")
                resultTextView.text = "${userName}님의 유형은 일석이조 유니콘"
            } else {
                //FP 타입
                FirebaseAnalytics.getInstance(this).setUserProperty("user_test_type", "FP")
                resultTextView.text = "${userName}님의 유형은 준비된 완벽주의자"
            }
        } else {
            if (scorePE < 3) {
                //SE 타입
                FirebaseAnalytics.getInstance(this).setUserProperty("user_test_type", "PE")
                resultTextView.text = "${userName}님의 유형은 효율적인 미니멀리스트"
            } else {
                //SP 타입
                FirebaseAnalytics.getInstance(this).setUserProperty("user_test_type", "SP")
                resultTextView.text = "${userName}님의 유형은 완벽추구 심플리스트"
            }
        }
    }
}