package com.example.shopping_app_part5_chapter02.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopping_app_part5_chapter02.R
import com.example.shopping_app_part5_chapter02.databinding.ActivityProductDetailBinding
import com.example.shopping_app_part5_chapter02.presentation.BaseActivity
import org.koin.android.ext.android.inject

internal class ProductDetailActivity : BaseActivity<ProductDetailViewModel, ActivityProductDetailBinding>() {

    override val viewModel  by inject<ProductDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
    }
}