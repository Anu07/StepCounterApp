package com.sd.src.stepcounterapp.activities

import android.content.Context
import android.content.Intent
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProviders
import com.sd.src.stepcounterapp.R
import com.sd.src.stepcounterapp.viewModels.BaseViewModel
import com.sd.src.stepcounterapp.viewModels.BaseViewModelFactory
import com.sdi.sdeiarchitecturemvvm.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_synchronizing_device.*
import android.view.animation.Animation




class DeviceSynchronizingActivity : BaseActivity<BaseViewModel>(){
    override val layoutId: Int
        get() = R.layout.activity_synchronizing_device
    override val viewModel: BaseViewModel
        get() = ViewModelProviders.of(
            this,
            BaseViewModelFactory { BaseViewModel(application) }).get(BaseViewModel::class.java)
    override val context: Context
        get() = this@DeviceSynchronizingActivity //To change initializer of created properties use File | Settings | File Templates.

    override fun onCreate() {
        val aniRotate = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_clock)
        syncimg.startAnimation(aniRotate)
        aniRotate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {}
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                startNext()
            }
        })
    }

    fun startNext(){
            val intent = Intent(mContext, DeviceListActivity::class.java)
//                    val options = ActivityOptions.makeSceneTransitionAnimation(this@SignInActivity)
            startActivity(intent)
    }


    override fun initListeners() {
    }

}