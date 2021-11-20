package com.example.lab3_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab3_5.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentThirdBinding.inflate(layoutInflater)

        val navController = findNavController()

        binding.bnToSecond.setOnClickListener {
            navController.navigate(R.id.action_thirdFragment_to_secondFragment)
        }

        binding.bnToFirst.setOnClickListener {
            navController.navigate(R.id.action_thirdFragment_to_firstFragment)
        }

        binding.navView.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.about -> navController.navigate(R.id.global_about)
            }
        }

        return binding.root
    }

}