package com.darkos.presentation.activity

import com.darkos.presentation.router.ActivityRouter

abstract class RounterActivity : BaseActivity() {

    protected abstract val router: ActivityRouter

    override fun onStart() {
        super.onStart()
        router.attach(this)
    }

    override fun onStop() {
        router.detach()
        super.onStop()
    }
}