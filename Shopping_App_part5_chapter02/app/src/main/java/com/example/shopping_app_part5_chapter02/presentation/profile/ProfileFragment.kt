package com.example.shopping_app_part5_chapter02.presentation.profile

import com.example.shopping_app_part5_chapter02.databinding.FragmentProfileBinding
import com.example.shopping_app_part5_chapter02.presentation.BaseFragment
import org.koin.android.ext.android.inject

internal class ProfileFragment: BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    companion object {
        const val TAG = "ProfileFragment"
    }

    override val viewModel: ProfileViewModel by inject<ProfileViewModel>()

    override fun getViewBinding(): FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)

    override fun observeData() {
    }
}