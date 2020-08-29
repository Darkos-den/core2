package com.darkos.presentation.router

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import com.darkos.presentation.fragment.argumented.Argumented
import com.darkos.presentation.fragment.argumented.ArgumentedFragment
import com.darkos.presentation.fragment.argumented.BaseArguments
import com.darkos.presentation.fragment.base.BaseFragment

abstract class BaseRouter : IBaseRouter, LifecycleObserver {

    protected abstract val containerId: Int

    abstract val manager: FragmentManager

    protected fun Fragment.replace() {
        manager.beginTransaction()
            .replace(containerId, this, this::class.java.simpleName)
            .commit()
    }

    protected fun Fragment.replaceWithBackStack() {
        manager.beginTransaction()
            .replace(containerId, this, this::class.java.simpleName)
            .addToBackStack(this::class.java.simpleName)
            .commit()
    }

    protected fun Fragment.addWithBackStack() {
        manager.beginTransaction()
            .add(containerId, this, this::class.java.simpleName)
            .addToBackStack(this::class.java.simpleName)
            .commit()
    }

    protected fun popBackStack() {
        manager.popBackStack()
    }

    protected fun currentFragment() = manager.findFragmentById(containerId)

    protected inline fun <reified T : BaseFragment> createInstance(): T {
        val instance = T::class.constructors.first().call()

        instance.arguments = Bundle()

        return instance
    }

    protected inline fun <A : BaseArguments, reified T : Argumented<A>> createArgumentedInstance(
        args: BaseArguments
    ): T {
        val instance = T::class.constructors.first().call()

        (instance as Fragment).arguments = Bundle().apply {
            putParcelable(ArgumentedFragment.KEY_ARGS, args)
        }

        return instance
    }
}