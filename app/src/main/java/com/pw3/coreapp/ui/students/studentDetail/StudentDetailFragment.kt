package com.pw3.coreapp.ui.students.studentDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pw3.CoreApp.R
import com.pw3.CoreApp.databinding.FragmentStudentDetailBinding
import com.pw3.coreapp.ui.util.showDialog

class StudentDetailFragment : Fragment() {

    private var _binding: FragmentStudentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StudentDetailViewModel by viewModels()

    private val args: StudentDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMenu()
        setupViews()
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.student_detail_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.update_student -> {
                        findNavController().navigate(
                            StudentDetailFragmentDirections.actionStudentDetailFragmentToEditStudentFragment(
                                args.student
                            )
                        )
                        return true
                    }

                    R.id.delete_student -> {
                        showDialog(
                            requireContext(),
                            R.string.delete_student_alert_title,
                            R.string.delete_student_alert_message,
                            R.string.delete,
                            ::deleteStudent
                        )

                        return true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun deleteStudent() {
        viewModel.deleteStudent(args.student)
    }

    private fun setupViews() {
        val student = args.student
        binding.textStudentName.text = student.name
        binding.textBirthday.text = student.birthDate
        binding.textEnrollmentDate.text = student.enrollmentDate
        binding.textPaymentDueDate.text =
            getString(R.string.student_payment_due_day, student.paymentDueDate.orEmpty())
        binding.textModality.text = student.modality
        binding.textPlan.text = student.plan
        binding.textStatus.text = student.status
        binding.textPaymentStatus.text = student.paymentStatus
    }
}
