package com.example.nedomed.ui.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nedomed.repository.AnalysisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(private val analysRepository: AnalysisRepository) : ViewModel() {

    val analysisLiveData get() = analysRepository.analysisLiveData
    val statusLiveData get() = analysRepository.statusLiveData

    fun getAllAnalysis() {
        viewModelScope.launch {
            analysRepository.getAnalysis()
        }
    }
}