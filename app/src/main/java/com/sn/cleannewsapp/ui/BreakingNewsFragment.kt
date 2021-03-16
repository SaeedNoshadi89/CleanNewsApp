package com.sn.cleannewsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sn.cleannewsapp.databinding.FragmentBreakingNewsBinding
import com.sn.cleannewsapp.ui.adapter.BreakingNewsAdapter
import com.sn.cleannewsapp.utils.Status
import com.sn.cleannewsapp.viewmodel.BreakingNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
                    getBreakingNewsStateFlow.collect {
                        when (it.status) {
                            Status.SUCCESS -> {
                                rvBreakingNews.adapter = BreakingNewsAdapter { article ->
                                    findNavController().navigate(
                                        BreakingNewsFragmentDirections.actionBreackingNewsFragmentToArticleFragment(
                                            article
                                        )
                                    )
                                }.apply {
                                    it.data?.let { response ->
                                        differ.submitList(response.articles)
                                    }
                                }

                                paginationProgressBar.visibility = View.GONE
                                rvBreakingNews.visibility = View.VISIBLE
                            }

                            Status.ERROR -> {
                                Toast.makeText(requireContext(), "You have an error", Toast.LENGTH_SHORT).show()
                                paginationProgressBar.visibility = View.VISIBLE
                                rvBreakingNews.visibility = View.GONE
                            }

                            Status.LOADING -> {
                                paginationProgressBar.visibility = View.VISIBLE
                                rvBreakingNews.visibility = View.GONE
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