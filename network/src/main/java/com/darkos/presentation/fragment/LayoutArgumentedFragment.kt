package com.darkos.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.darkos.presentation.fragment.argumented.ArgumentedFragment
import com.darkos.presentation.fragment.argumented.BaseArguments

abstract class LayoutArgumentedFragment<A : BaseArguments>(
    private val layoutId: Int
) : ArgumentedFragment<A>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }
}