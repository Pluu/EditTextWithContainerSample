package com.pluu.view.edittext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pluu.view.edittext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextWithContainer.setParentContainer(binding.container)
        binding.editTextWithContainerValue.setParentContainer(binding.container2)
    }
}