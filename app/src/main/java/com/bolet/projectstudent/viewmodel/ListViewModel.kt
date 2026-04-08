package com.bolet.projectstudent.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bolet.projectstudent.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        studentLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<Student>>() {}.type
                val result = Gson().fromJson<List<Student>>(response, sType)
                studentsLD.value = result as ArrayList<Student>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            { error ->
                Log.d("showvoley", error.toString())
                studentLoadErrorLD.value = true
                loadingLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}