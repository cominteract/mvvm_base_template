package com.ainsigne.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val setUpViewBinding: (LayoutInflater) -> VB
) : Fragment() {

    lateinit var baseActivity: BaseActivity

    lateinit var binding: VB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity: BaseActivity = context
            this.baseActivity = activity
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setUpViewBinding(inflater)
        return binding.root
    }

    fun show(message: String, useToast: Boolean, @ColorRes color: Int) {
        baseActivity.show(message, useToast, color)
    }

    fun hideKeyboard() {
        baseActivity.hideKeyboard()
    }

    fun showKeyboard() {
        baseActivity.showKeyboard()
    }

    abstract fun showLoading()

    abstract fun hideLoading()

}