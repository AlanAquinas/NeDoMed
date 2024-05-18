package com.example.nedomed.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nedomed.network.api.API
import com.example.nedomed.network.model.AnalysResponse
import com.example.nedomed.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class AnalysisRepository @Inject constructor(private val api: API) {

    private val _analysisLiveData = MutableLiveData<NetworkResult<List<AnalysResponse>>>()
    val analysisLiveData get() = _analysisLiveData

    private val _statusLiveData = MutableLiveData<NetworkResult<Pair<Boolean, String>>>()
    val statusLiveData get() = _statusLiveData

    suspend fun getAnalysis() {
        _analysisLiveData.postValue(NetworkResult.Loading())
        val response = api.getAnalysisResultsByUserId(5)

        if (response.isSuccessful && response.body() != null) {
            Log.d("API", "${response.body()}")
            Log.d("API", "${response.headers()}")
            _analysisLiveData.postValue(NetworkResult.Success(response.body()!!))
        }

        else if (response.errorBody() != null) {
            Log.d("API", "${response}")
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _analysisLiveData.postValue(NetworkResult.Error(errorObj.getString("detail")))
        }

        else {
            Log.d("API", "${response}")
            _analysisLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

    private fun handleResponse(response: Response<AnalysResponse>, message: String) {
        if (response.isSuccessful && response.body() != null) {
            _statusLiveData.postValue(NetworkResult.Success(Pair(true, message)))
        } else {
            _statusLiveData.postValue(NetworkResult.Success(Pair(false, "Something went wrong")))
        }
    }
}