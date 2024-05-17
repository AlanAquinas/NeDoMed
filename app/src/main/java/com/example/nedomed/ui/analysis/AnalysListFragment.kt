package com.example.nedomed.ui.analysis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.nedomed.R
import com.example.nedomed.databinding.AnalysItemListBinding
import com.example.nedomed.network.model.AnalysResponse
import com.example.nedomed.utils.NetworkResult
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Analyses.
 */
@AndroidEntryPoint
class AnalysListFragment : Fragment(R.layout.analys_item_list) {

    private var _binding: AnalysItemListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AnalysAdapter
    private val analysisViewModel: AnalysisViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AnalysItemListBinding.inflate(inflater, container, false)
        adapter = AnalysAdapter(::onAnalysClicked)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        analysisViewModel.getAllAnalysis()
        binding.recyclerViewAnalys.adapter = adapter

        bindObservers()
    }

    private fun onAnalysClicked(analysResponse: AnalysResponse){
        val bundle = Bundle()
        bundle.putString("analys", Gson().toJson(analysResponse))
        parentFragmentManager.commit {
            add(R.id.frame_layout, AnalysFragment(), "tag")
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            addToBackStack(null)
        }
    }

    private fun bindObservers() {
        analysisViewModel.analysisLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
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
    }
}