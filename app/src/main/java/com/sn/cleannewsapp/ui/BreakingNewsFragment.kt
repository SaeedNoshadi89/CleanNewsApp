package com.sn.cleannewsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sn.cleannewsapp.databinding.FragmentBreakingNewsBinding
import com.sn.cleannewsapp.utils.Resource
import com.sn.cleannewsapp.viewmodel.BreakingNewsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BreakingNewsFragment : Fragment() {

    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModels<BreakingNewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentBreakingNewsBinding.inflate(inflater, container, false).run {
            _binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            viewModel.apply {
                lifecycleScope.launch {
                    getBreakingNews("us", 1).collect {
                        when(it){
                            is Resource.Success -> {
                                Log.e("TTT", it.data.toString())
                            }
                            is Resource.Error -> {

                            }
                            is Resource.Loading -> {

                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}