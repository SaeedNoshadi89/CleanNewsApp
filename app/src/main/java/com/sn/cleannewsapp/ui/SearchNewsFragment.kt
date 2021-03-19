package com.sn.cleannewsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sn.cleannewsapp.databinding.FragmentSearchNewsBinding
import com.sn.cleannewsapp.ui.adapter.SearchNewsAdapter
import com.sn.cleannewsapp.utils.Status
import com.sn.cleannewsapp.viewmodel.SearchNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchNewsFragment : Fragment() {

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding get() = _binding
    private val viewModel: SearchNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSearchNewsBinding.inflate(inflater, container, false).run {
            _binding = this
            root
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleScope.launch {
                etSearch.getQueryTextChangeStateFlow()
                    .debounce(300)
                    .filter { query ->
                        return@filter query.isNotEmpty()
                    }
                    .distinctUntilChanged()
                    .flatMapLatest { query ->
                        viewModel.getSearchNews(query).catch {
//                        emitAll(flowOf(""))
                            return@catch
                        }
                    }
                    .flowOn(Dispatchers.Main)
                    .collect {result ->
                        when(result.status){
                            Status.SUCCESS -> {
                                rvSearchNews.apply {
                                    adapter = SearchNewsAdapter({}, {}).apply {
                                        differ.submitList(result.data?.articles)
                                    }
                                }
                            }
                            Status.LOADING -> {}
                            Status.ERROR -> {
                                Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            }
        }
    }

    private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String>{
        val query = MutableStateFlow("")
        setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
              return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               query.value = newText ?: ""
                return true
            }
        })

        return query
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}