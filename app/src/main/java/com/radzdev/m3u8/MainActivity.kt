package com.radzdev.m3u8

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.radzdev.sniff.m3u8

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hardcoded URL
        val url = "https://vidlink.pro/movie/786892"

        // Start the m3u8 activity with the hardcoded URL
        val intent = Intent(this, m3u8::class.java).apply {
            putExtra("url", url)
        }
        startActivity(intent)

        // Finish the MainActivity immediately
        finish()
    }
}
