package com.actividad.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val selectedDoctor = MutableLiveData<Doctor>()
    val selectedDateTime = MutableLiveData<Pair<String, String>>()

    fun selectDoctor(doctor: Doctor) {
        selectedDoctor.value = doctor
    }

    fun selectDateTime(date: String, time: String) {
        selectedDateTime.value = Pair(date, time)
    }
}
