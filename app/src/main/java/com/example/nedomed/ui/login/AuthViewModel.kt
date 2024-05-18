package com.example.nedomed.ui.login

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nedomed.network.model.TokenResponse
import com.example.nedomed.network.model.UserRequest
import com.example.nedomed.network.model.UserResponse
import com.example.nedomed.repository.UserRepository
import com.example.nedomed.utils.Helper
import com.example.nedomed.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<TokenResponse>>
        get() = userRepository.userResponseLiveData


    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }
    }

    fun validateCredentials(userName: String, password: String) : Pair<Boolean, String> {

        var result = Pair(true, "")
        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
            result = Pair(false, "Please provide the credentials")
        }
        else if(!TextUtils.isEmpty(password) && password.length <= 5){
            result = Pair(false, "Password length should be greater than 5")
        }
        return result
    }

}