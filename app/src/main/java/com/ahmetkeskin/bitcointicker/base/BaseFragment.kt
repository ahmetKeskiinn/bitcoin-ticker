package com.ahmetkeskin.bitcointicker.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    lateinit var binding: B
    abstract fun onInitDataBinding()
    private var viewModelStoreOwner: ViewModelStoreOwner? = null

    //private val viewModel by viewModels<VM>()
    lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vmClass = this.javaClass.findGenericWithType<VM>(ViewModel::class.java)
        if (vmClass != null)
            viewModel = ViewModelProvider(this)[vmClass]

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        onInitDataBinding()
        return binding.root
    }

    open fun showToast(body: String?) {
        Toast.makeText(context, body, Toast.LENGTH_LONG).show()
    }
}