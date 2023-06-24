package com.pw3.coreapp.ui.students.newStudent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pw3.CoreApp.R
import com.pw3.CoreApp.databinding.FragmentNewStudentBinding
import com.pw3.coreapp.model.PaymentStatus
import com.pw3.coreapp.model.Student
import com.pw3.coreapp.model.StudentModality
import com.pw3.coreapp.model.StudentPlan
import com.pw3.coreapp.model.StudentStatus
import com.pw3.coreapp.ui.util.showDialogCustom
import com.pw3.coreapp.ui.util.showSnackbar

class NewStudentFragment : Fragment() {

    private var _binding: FragmentNewStudentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewStudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        setupListeners()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapters() {
        setupStudentModalityAdapter()
        setupStudentPlanAdapter()
        setupStudentStatusAdapter()
        setupStudentPaymentStatusAdapter()
    }

    private fun setupStudentModalityAdapter() {
        val modality = StudentModality()
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            modality.all
        )

        binding.textModality.setAdapter(adapter)
    }

    private fun setupStudentPlanAdapter() {
        val plan = StudentPlan()
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            plan.all
        )

        binding.textPlan.setAdapter(adapter)
    }

    private fun setupStudentStatusAdapter() {
        val status = StudentStatus.values().map {
            getString(it.stringRes)
        }
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            status
        )

        binding.textStatus.setAdapter(adapter)
    }

    private fun setupStudentPaymentStatusAdapter() {
        val paymentStatus = PaymentStatus.values().map {
            getString(it.stringRes)
        }
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            paymentStatus
        )

        binding.textPaymentStatus.setAdapter(adapter)
    }

    private fun setupListeners() {
        binding.buttonSave.setOnClickListener {
            val imm: InputMethodManager? =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(requireView().windowToken, 0)

            val name = binding.editTextName.text.toString()
            val birthDate = binding.editTextBirthDate.text.toString()
            val enrollmentDate = binding.editTextEnrollmentDate.text.toString()
            val paymentDueDate = binding.editTextPaymentDueDate.text.toString()
            val modality = binding.textModality.text.toString()
            val plan = binding.textPlan.text.toString()
            val status = binding.textStatus.text.toString()
            val paymentStatus = binding.textPaymentStatus.text.toString()

            val student = Student(
                name = name,
                birthDate = birthDate,
                enrollmentDate = enrollmentDate,
                paymentDueDate = paymentDueDate,
                modality = modality,
                plan = plan,
                status = status,
                paymentStatus = paymentStatus
            )

            viewModel.saveStudent(student)
        }
    }

    private fun setupObservers() {
        viewModel.isSaveStudentSucceeded.observe(viewLifecycleOwner) { isSucceeded ->
            if (isSucceeded) {
                showSnackbar(getString(R.string.new_user_save_succeeded_message))
                findNavController().popBackStack()
            }
        }

        viewModel.isSaveStudentFailed.observe(viewLifecycleOwner) { errorMessage ->
            showDialogCustom(
                requireContext(), R.string.new_user_failed_title, errorMessage, R.string.close
            ) { }
        }
    }
}