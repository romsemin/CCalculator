package com.example.ccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding : ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val cCalculator = CCFragment()
//        val ft = supportFragmentManager.beginTransaction()
//        ft.add(R.id.recycler_view, cCalculator)
//        ft.commit()

//        binding.textFiled.textView.text = "Enter sequence here"
//        binding.recyclerView.fragmentRecyclerView.adapter.set
    }
}