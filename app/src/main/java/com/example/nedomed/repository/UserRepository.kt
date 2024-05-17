package com.example.nedomed.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nedomed.network.api.API
import com.example.nedomed.network.model.TokenResponse
import com.example.nedomed.network.model.UserRequest
import com.example.nedomed.network.model.UserResponse
import com.example.nedomed.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: API) {

    private val _userTokenLiveData = MutableLiveData<NetworkResult<TokenResponse>>()
    val userResponseLiveData: LiveData<NetworkResult<TokenResponse>>
        get() = _userTokenLiveData

    suspend fun loginUser(userRequest: UserRequest) {
        _userTokenLiveData.postValue(NetworkResult.Loading())
        val response =userAPI.signIn(userRequest)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<TokenResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userTokenLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userTokenLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _userTokenLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}