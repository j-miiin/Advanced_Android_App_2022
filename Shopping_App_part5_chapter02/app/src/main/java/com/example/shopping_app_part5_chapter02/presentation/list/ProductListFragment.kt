package com.example.shopping_app_part5_chapter02.presentation.list

import com.example.shopping_app_part5_chapter02.databinding.FragmentProductListBinding
import com.example.shopping_app_part5_chapter02.presentation.BaseFragment
import org.koin.android.ext.android.inject

internal class ProductListFragment: BaseFragment<ProductListViewModel, FragmentProductListBinding>() {

    companion object {
        const val TAG = "ProductListFragment"
    }

    override val viewModel: ProductListViewModel by inject<ProductListViewModel>()

    override fun getViewBinding(): FragmentProductListBinding = FragmentProductListBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.productListStateLiveData.observe(this) {

        when(it) {
            is ProductListState.UnInitialized -> {
                initViews(binding)
            }

            is ProductListState.Loading -> {
                handleLoadingState()
            }

            is ProductListState.Success -> {
                handleSuccessState(it)
            }

            is ProductListState.Error -> {
                handleErrorState()
            }
        }
    }
}