package com.darkos.core.presentation.activity

import com.darkos.core.presentation.router.ActivityRouter

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