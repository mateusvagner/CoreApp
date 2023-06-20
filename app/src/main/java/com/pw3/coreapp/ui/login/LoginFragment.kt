package com.pw3.coreapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.navigation.fragment.findNavController
import com.pw3.CoreApp.R
import com.pw3.CoreApp.databinding.FragmentLoginBinding
import com.pw3.CoreApp.databinding.FragmentMainBinding
import com.pw3.coreapp.MainActivity
import com.pw3.coreapp.ui.main.MainFragmentDirections
import com.pw3.coreapp.ui.main.MainViewModel
import com.pw3.coreapp.ui.util.showDialogCustom
import com.pw3.coreapp.util.isValidEmail
import com.pw3.coreapp.util.isValidPassword
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        lifecycleScope.launch {
            viewLifecycleOwner.withCreated {
                (requireActivity() as? MainActivity)?.setBottomNavViewVisibility(false)
                (requireActivity() as? MainActivity)?.setDrawerMenuVisibility(false)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
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

    private fun setupListeners() {
        binding.loginButton.setOnClickListener {
            val allEntriesValid = checkUserEntries()
            if (!allEntriesValid)
                return@setOnClickListener

            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            signIn(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        viewModel.signInWithEmailAndPassword(email, password) { task ->
            if (task.isSuccessful) {
                // Restart activity
                val intent = requireActivity().intent
                requireActivity().finish()
                startActivity(intent)
            } else {
                // If sign in fails, display a message to the user.
                showDialogCustom(
                    requireContext(),
                    R.string.create_account_failed_title,
                    task.exception?.message ?: "",
                    R.string.ok
                ) { }
            }
        }
    }

    private fun checkUserEntries(): Boolean {
        var entriesValid = true

        val email = binding.editTextEmail.text

        if (email.isNullOrBlank()) {
            binding.layoutEmail.error = getText(R.string.email_needed_error_message)
            entriesValid = false
        } else if (!email.isValidEmail()) {
            binding.layoutEmail.error = getText(R.string.email_not_valid_error_message)
            entriesValid = false
        } else {
            binding.layoutEmail.error = null
        }

        val password = binding.editTextPassword.text

        if (password.isNullOrBlank()) {
            binding.layoutPassword.error = getText(R.string.password_needed_error_message)
            entriesValid = false
        } else if (!password.isValidPassword()) {
            binding.layoutPassword.error = getText(R.string.password_not_valid_error_message)
            entriesValid = false
        } else {
            binding.layoutPassword.error = null
        }

        return entriesValid
    }
}
