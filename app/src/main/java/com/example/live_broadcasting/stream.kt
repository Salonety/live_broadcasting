package com.example.live_broadcasting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import video.api.client.ApiVideoClient
import video.api.client.api.ApiException
import video.api.client.api.models.*
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class stream : AppCompatActivity() {
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stream)
    }

    override fun onResume() {
        super.onResume()

        val apiVideoClient = ApiVideoClient("YOUR_API_KEY")
        // if you rather like to use the sandbox environment:
        // val apiVideoClient = ApiVideoClient("YOU_SANDBOX_API_KEY", Environment.SANDBOX)

        val myVideoFile = File("my-video.mp4")

        /**
         * Notice: you must not call API from the UI/main thread. Dispatch with Thread, Executors or Kotlin coroutines.
         */
        executor.execute {
            try {
                var video = apiVideoClient.videos().create(VideoCreationPayload().title("my video"))
                video = apiVideoClient.videos().upload(video.videoId, myVideoFile)
                Log.i("Example", "$video")
            } catch (e: ApiException) {
                Log.e("Example", "Exception when calling VideoApi")
                e.message?.let {
                    Log.e("Example", "Reason: ${it}")
                }
            }
        }
    }
}
