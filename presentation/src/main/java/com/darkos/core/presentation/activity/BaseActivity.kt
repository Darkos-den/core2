package com.darkos.core.presentation.activity

import android.content.Intent
import com.darkos.core.presentation.common.ActivityResultProcessor
import org.kodein.di.generic.instance

abstract class BaseActivity : KodeinActivity() {

    internal val resultProcessor: ActivityResultProcessor by instance<ActivityResultProcessor>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        resultProcessor.onActivityResult(requestCode, resultCode, data)
    }
}