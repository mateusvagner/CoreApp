package com.pw3.coreapp.ui.students.allStudents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pw3.CoreApp.databinding.FragmentStudentsBinding

class StudentsFragment : Fragment() {

    private var _binding: FragmentStudentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StudentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() {
        binding.addStudentButton.setOnClickListener {
            findNavController().navigate(
                StudentsFragmentDirections.actionStudentsToNewStudentFragment()
            )
        }
    }

    private fun setupObservers() {

    }
}