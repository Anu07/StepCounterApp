package com.sd.src.stepcounterapp.viewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sd.src.stepcounterapp.AppApplication
import com.sd.src.stepcounterapp.model.challenge.ChallengeResponse
import com.sd.src.stepcounterapp.model.generic.BasicRequest
import com.sd.src.stepcounterapp.model.marketplace.BasicSearchRequest
import com.sd.src.stepcounterapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengeViewModel(application: Application) : AndroidViewModel(application) {

    val call = RetrofitClient.instance
    private var mChallengeProduct: MutableLiveData<ChallengeResponse>? = null


    fun getChallengeObject(): MutableLiveData<ChallengeResponse> {
        if (mChallengeProduct == null) {
            mChallengeProduct = MutableLiveData()
        }
        return mChallengeProduct as MutableLiveData<ChallengeResponse>
    }


    fun getchallenges(request: BasicRequest) {
        call!!.getChallenges(request).enqueue(object : Callback<ChallengeResponse> {

            override fun onFailure(call: Call<ChallengeResponse>?, t: Throwable?) {
                Log.v("retrofit", "call failed")
                Toast.makeText(AppApplication.applicationContext(), "Server error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ChallengeResponse>?, response: Response<ChallengeResponse>?) {
                mChallengeProduct!!.value = response!!.body()
            }
        })
    }



}