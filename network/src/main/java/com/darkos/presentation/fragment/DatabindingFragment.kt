package com.darkos.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.darkos.presentation.common.LayoutResProcessor
import com.darkos.presentation.fragment.base.BaseFragment

abstract class DatabindingFragment<B: ViewDataBinding>: BaseFragment() {

    private val layoutResProcessor: LayoutResProcessor by lazy {
        LayoutResProcessor(
            context = requireContext(),
            superClass = this.javaClass.superclass,
            superClassGeneric = this.javaClass.genericSuperclass
        )
    }

    private lateinit var _binding: B
    protected val binding: B
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return _binding.root
    }

    @LayoutRes
    open fun getLayoutRes(): Int = layoutResProcessor.getLayoutRes()
}