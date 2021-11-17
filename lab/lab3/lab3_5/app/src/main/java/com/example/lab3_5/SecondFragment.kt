package com.example.lab3_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab3_5.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSecondBinding.inflate(layoutInflater)

        val navController = findNavController()

        binding.toThird.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        binding.toFirst.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.toAbout.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.about -> navController.navigate(R.id.global_about)
            }
        }


        return binding.root
    }
}