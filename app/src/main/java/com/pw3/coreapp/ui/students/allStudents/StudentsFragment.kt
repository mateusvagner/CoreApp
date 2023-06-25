package com.pw3.coreapp.ui.students.allStudents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pw3.CoreApp.R
import com.pw3.CoreApp.databinding.FragmentStudentsBinding
import com.pw3.coreapp.model.Student
import com.pw3.coreapp.ui.students.allStudents.adapter.BirthdayItemAdapter
import com.pw3.coreapp.ui.students.allStudents.adapter.StudentItemAdapter
import com.pw3.coreapp.ui.students.allStudents.adapter.TuitionItemAdapter
import com.pw3.coreapp.ui.util.showDialogCustom

class StudentsFragment : Fragment() {

    private var _binding: FragmentStudentsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StudentsViewModel by viewModels()

    private lateinit var studentItemAdapter: StudentItemAdapter
    private lateinit var birthdayItemAdapter: BirthdayItemAdapter
    private lateinit var tuitionItemAdapter: TuitionItemAdapter

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
        setupAdapters()
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
        viewModel.isGetAllStudentsFailed.observe(viewLifecycleOwner) { errorMessage ->
            showDialogCustom(
                requireContext(),
                R.string.get_all_students_failed_title,
                errorMessage,
                R.string.close
            ) { }
        }

        viewModel.allStudents.observe(viewLifecycleOwner) {
            studentItemAdapter.submitList(it)
        }

        viewModel.studentsUpcomingBirthday.observe(viewLifecycleOwner) {
            birthdayItemAdapter.submitList(it)
        }

        viewModel.studentsPaymentDueDate.observe(viewLifecycleOwner) {
            tuitionItemAdapter.submitList(it)
        }
    }

    private fun setupAdapters() {
        birthdayItemAdapter = BirthdayItemAdapter(::navigateToStudentDetailScreen)
        binding.recyclerViewUpcomingBirthdays.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewUpcomingBirthdays.adapter = birthdayItemAdapter

        tuitionItemAdapter = TuitionItemAdapter(::navigateToStudentDetailScreen)
        binding.recyclerViewTuition.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewTuition.adapter = tuitionItemAdapter

        studentItemAdapter = StudentItemAdapter(::navigateToStudentDetailScreen)
        binding.recyclerViewAllStudents.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewAllStudents.adapter = studentItemAdapter
    }

    private fun navigateToStudentDetailScreen(student: Student) {
        findNavController().navigate(
            StudentsFragmentDirections.actionStudentsToEditStudentFragment(student)
        )
    }
}
