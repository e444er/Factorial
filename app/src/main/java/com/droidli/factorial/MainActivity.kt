package com.droidli.factorial

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.droidli.factorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        viewModel.calculate(binding.editTextNumber.text.toString())
    }

    private fun observeViewModel() {
        viewModel.progress.observe(this) {
            if (it) {
                binding.progressBarLoading.isVisible = true
                binding.buttonCalculate.isEnabled = false
            } else {
                binding.progressBarLoading.isVisible = false
                binding.buttonCalculate.isEnabled = true
            }
        }
        viewModel.error.observe(this) {
            if (it) {
                Toast.makeText(this, "You did not entered value", Toast.LENGTH_SHORT).show()

            }
        }
        viewModel.factorial.observe(this) {
            binding.textView.text = it
        }
    }
}