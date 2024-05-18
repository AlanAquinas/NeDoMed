package com.example.nedomed.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.nedomed.R
import com.example.nedomed.databinding.FragmentAccountBinding
import com.example.nedomed.network.model.UserRequest
import com.example.nedomed.utils.Helper
import com.example.nedomed.utils.NetworkResult
import com.example.nedomed.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by activityViewModels<AuthViewModel>()

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            Helper.hideKeyboard(it)
            val validationResult = validateUserInput()
            if (validationResult.first) {
                val userRequest = getUserRequest()
                authViewModel.loginUser(userRequest)
            } else {
                Toast.makeText(requireContext(), "Login is unsuccessfully", Toast.LENGTH_SHORT).show()
            }
        }

        bindObservers()
    }

    private fun getUserRequest(): UserRequest {
        return binding.run {
            UserRequest(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val usernameText = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        return authViewModel.validateCredentials(usernameText, password)
    }

    private fun bindObservers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    tokenManager.saveToken(it.data!!.access_token)
                    Toast.makeText(requireContext(), "Login is successfully", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading ->{
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG: String = AccountFragment::class.java.simpleName
        fun newInstance() = AccountFragment()
    }
}