package com.pw3.coreapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.navigation.fragment.findNavController
import com.pw3.CoreApp.databinding.FragmentMainBinding
import com.pw3.coreapp.MainActivity
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.studentsButton.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainToStudents()
            )
        }

        lifecycleScope.launch {
            viewLifecycleOwner.withCreated {
                (requireActivity() as? MainActivity)?.setBottomNavViewVisibility(false)
                (requireActivity() as? MainActivity)?.setDrawerMenuVisibility(false)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as? MainActivity)?.setBottomNavViewVisibility(true)
        (requireActivity() as? MainActivity)?.setDrawerMenuVisibility(true)
    }
}