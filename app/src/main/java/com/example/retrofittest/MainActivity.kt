package com.example.retrofittest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.adapter.MyAdapter
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        //val myPost = Post(2, 2, "Minyu-Kim", "Android Developer")
        viewModel.getPost("11112222")
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.headers().toString())
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerview() {
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}