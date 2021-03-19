package com.sn.cleannewsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sn.cleannewsapp.databinding.FragmentArticleBinding
import com.sn.cleannewsapp.utils.Status
import com.sn.cleannewsapp.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding
    private val args: ArticleFragmentArgs by navArgs()
    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentArticleBinding.inflate(inflater, container, false).run {
            _binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(args.article.url ?: "")
                fab.setOnClickListener {
                    lifecycleScope.launch {
                        viewModel.insertArticle(args.article).collect {
                            when(it.status){
                                Status.SUCCESS -> {
                                    Toast.makeText(requireContext(), "Inserting is successfully", Toast.LENGTH_SHORT).show()
                                }
                                Status.LOADING -> {}
                                Status.ERROR -> {
                                    Toast.makeText(requireContext(), "Inserting is error", Toast.LENGTH_SHORT).show()

                                }
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