package com.marysugar.firebase_sample

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val commonViewModel by viewModels<CommonViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        MyFirebaseInstanceIdService.Events.serviceEvent.observe(this,{
            Log.d(TAG, it)
        })
    }

    companion object {
        val TAG = MainActivity::class.simpleName
    }
}