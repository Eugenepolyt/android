package com.example.lab3_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab3_5.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFirstBinding.inflate(layoutInflater)

        val navController = findNavController()

        binding.toSecond.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_secondFragment)
        }

        binding.toAbout.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.about -> navController.navigate(R.id.global_about)
            }
        }

        return binding.root
    }


}