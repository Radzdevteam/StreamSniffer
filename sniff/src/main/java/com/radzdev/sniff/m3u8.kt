package com.radzdev.sniff

import android.app.AlertDialog
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.activity.ComponentActivity
import com.radzdev.radzexoplayer.ExoPlayerManager

class m3u8 : ComponentActivity() {

    private lateinit var sniffingWebView: WebView
    private lateinit var progressDialog: AlertDialog
    private val detectedM3u8Urls = mutableSetOf<String>() // Store unique m3u8 URLs
    private val detectedSubtitleUrls = mutableSetOf<String>() // Store unique subtitle URLs (srt/vtt)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the progress dialog
        progressDialog = AlertDialog.Builder(this)
            .setMessage("Please wait...")
            .setCancelable(false)
            .create()

        // Show the progress dialog
        progressDialog.show()

        // Initialize the WebView
        sniffingWebView = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.mediaPlaybackRequiresUserGesture = false
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                    val interceptedUrl = request?.url.toString()
                    when {
                        interceptedUrl.contains(".m3u8") -> handleM3u8Url(interceptedUrl)
                        interceptedUrl.contains(".srt") -> handleSubtitleUrl(interceptedUrl, "SRT")
                        interceptedUrl.contains(".vtt") -> handleSubtitleUrl(interceptedUrl, "VTT")
                    }
                    return super.shouldInterceptRequest(view, request)
                }

                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    handler?.proceed() // Ignore SSL errors (use with caution)
                }
            }
        }

        // Load the URL passed via intent
        val url = intent.getStringExtra("url") ?: ""
        if (url.isNotEmpty()) {
            sniffingWebView.loadUrl(url)
        }
    }

    private fun handleM3u8Url(url: String) {
        if (detectedM3u8Urls.add(url)) {
            Log.d("M3U8 Sniffer", "Detected m3u8 URL: $url")

            // Dismiss the progress dialog
            progressDialog.dismiss()

            // Send the URL to ExoPlayer
            val intent = Intent(this, ExoPlayerManager::class.java).apply {
                putExtra("videoUrl", url)
                putExtra("subtitleUrl", null as String?)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun handleSubtitleUrl(url: String, subtitleType: String) {
        if (detectedSubtitleUrls.add(url)) {
            Log.d("Subtitle Sniffer", "Detected $subtitleType subtitle URL: $url")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sniffingWebView.destroy()
    }


}
