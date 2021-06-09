package com.marysugar.firebase_sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommonViewModel : ViewModel(){
    val test = "test"

    val testName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}