package com.netnovelreader.ui.fragment

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.netnovelreader.R
import com.netnovelreader.common.init
import com.netnovelreader.common.obtainViewModel
import com.netnovelreader.databinding.FragmentSitePreferenceBinding
import com.netnovelreader.ui.adapter.RecyclerAdapter
import com.netnovelreader.viewmodel.SettingViewModel
import kotlinx.coroutines.experimental.launch

class SitePreferenceFragment : Fragment() {
    private var settingViewModel: SettingViewModel? = null
    private lateinit var binding: FragmentSitePreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingViewModel = activity?.obtainViewModel(SettingViewModel::class.java)
        settingViewModel?.deleteAlertCommand?.observe(this, Observer {
            it ?: return@Observer
            val listener = DialogInterface.OnClickListener { _, _ ->
                launch { settingViewModel?.deleteSite(it) }
            }
            AlertDialog.Builder(this@SitePreferenceFragment.context)
                    .setTitle(getString(R.string.deleteBook).replace("book", it))
                    .setPositiveButton(R.string.yes, listener)
                    .setNegativeButton(R.string.no, null)
                    .create()
                    .show()
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSitePreferenceBinding>(
                inflater,
                R.layout.fragment_site_preference, container, false
        )
        launch { settingViewModel?.showSiteList() }
        binding.recycleSiteList.init(
            RecyclerAdapter(
                settingViewModel?.siteList,
                R.layout.item_site_preference_list,
                settingViewModel
            )
        )
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.recycleSiteList.adapter = null
    }
}
