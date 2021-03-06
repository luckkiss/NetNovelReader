package com.netnovelreader.ui.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.netnovelreader.R
import com.netnovelreader.common.obtainViewModel
import com.netnovelreader.databinding.FragmentLoginBinding
import com.netnovelreader.viewmodel.SettingViewModel

class LoginFragment : Fragment() {
    var mViewModel: SettingViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = activity?.obtainViewModel(SettingViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
            .apply { viewModel = mViewModel }
        binding.login.setOnClickListener {
            mViewModel?.login(binding.username.text.toString(), binding.password.text.toString())
        }
        return binding.root
    }
}