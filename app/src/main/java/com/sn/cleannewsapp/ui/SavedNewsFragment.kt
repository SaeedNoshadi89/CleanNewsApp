package com.sn.cleannewsapp.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sn.cleannewsapp.databinding.FragmentSavedNewsBinding
import com.sn.cleannewsapp.ui.adapter.SavedNewsAdapter
import com.sn.cleannewsapp.utils.Status
import com.sn.cleannewsapp.viewmodel.SavedNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding get() = _binding
    private val viewModel: SavedNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSavedNewsBinding.inflate(inflater, container, false).run {
            _binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel.apply {
                lifecycleScope.launchWhenResumed {
                    getAllArticles().collect {
                        when(it.status){
                            Status.SUCCESS -> {
                                rvSavedNews.visibility = View.VISIBLE
                                paginationProgressBar.visibility = View.GONE
                                rvSavedNews.apply {
                                    adapter = SavedNewsAdapter({article ->
                                        findNavController().navigate(
                                            SavedNewsFragmentDirections.actionSavedFragmentToArticleFragment(
                                                article
                                            )
                                        )
                                    }, {deleteArticle ->
                                        MaterialAlertDialogBuilder(requireContext())
                                            .setTitle("Delete Article")
                                            .setMessage("Are you sure to delete this article??")
                                            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                                                lifecycleScope.launch {
                                                    deleteArticle(deleteArticle).collect {status ->
                                                        when(status.status){
                                                            Status.SUCCESS -> {
                                                                Toast.makeText(requireContext(), "delete is successfully", Toast.LENGTH_SHORT)
                                                                    .show()
                                                            }
                                                            Status.LOADING -> {}
                                                            Status.ERROR -> {
                                                                Toast.makeText(requireContext(), "wrong in the deleting", Toast.LENGTH_SHORT)
                                                                    .show()
                                                            }
                                                        }
                                                    }
                                                }
                                            }).setNegativeButton("No",  DialogInterface.OnClickListener{ dialog, which ->

                                            })
                                            .show()

                                    }).apply {
                                        differ.submitList(it.data)
                                    }
                                }
                            }
                            Status.LOADING -> {
                                rvSavedNews.visibility = View.GONE
                                paginationProgressBar.visibility = View.VISIBLE
                            }
                            Status.ERROR -> {
                                rvSavedNews.visibility = View.GONE
                                paginationProgressBar.visibility = View.VISIBLE
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