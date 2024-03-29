package com.sd.src.stepcounterapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sd.src.stepcounterapp.AppConstants
import com.sd.src.stepcounterapp.R
import com.sd.src.stepcounterapp.model.login.LoginResponseJ
import com.sd.src.stepcounterapp.utils.SharedPreferencesManager

class SplashActivity : AppCompatActivity() {

    private lateinit var userObj: LoginResponseJ
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            if(!SharedPreferencesManager.hasKey(this@SplashActivity,"User")){
                val intent = Intent(applicationContext, OnboardingActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                userObj = SharedPreferencesManager.getUserObject(this@SplashActivity)
                if(userObj.data.basicFlag && userObj.data.rewardFlag){
                    val intent = Intent(applicationContext, SyncDeviceActivity::class.java)
                    startActivity(intent)
                    finish()
                }else if(!userObj.data.basicFlag && !userObj.data.rewardFlag){
                    val intent = Intent(applicationContext, BasicInfoActivity::class.java)
                    startActivity(intent)
                    finish()
                }else if(!userObj.data.rewardFlag){
                    val intent = Intent(applicationContext, RewardsCategorySelectionActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}