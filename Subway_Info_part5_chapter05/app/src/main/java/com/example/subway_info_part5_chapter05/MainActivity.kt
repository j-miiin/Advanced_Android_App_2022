package com.example.subway_info_part5_chapter05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.subway_info_part5_chapter05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}