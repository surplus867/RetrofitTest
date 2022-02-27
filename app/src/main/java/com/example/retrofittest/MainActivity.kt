package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
           if (response.isSuccessful) {
               Log.d("Response", response.body()?.userId.toString())
               Log.d("Response", response.body()?.id.toString())
               binding.textView.text = response.body()?.title.toString()
               Log.d("Response", response.body()?.body!!)
           } else {
               Log.d("Response", response.errorBody().toString())
               binding.textView.text = response.code().toString()
           }
        })
    }
}