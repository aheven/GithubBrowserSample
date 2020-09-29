package heven.holt.jetpack.github.ui.search

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import heven.holt.jetpack.github.databinding.SearchFragmentBinding
import heven.holt.jetpack.github.ui.common.RepoListAdapter
import heven.holt.jetpack.github.util.autoCleared
import heven.holt.jetpack.github.vo.Status

/**
 *Time:2020/9/25
 *Author:HevenHolt
 *Description:
 */
@AndroidEntryPoint
class SearchFragment : Fragment() {
    var binding by autoCleared<SearchFragmentBinding>()

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val repoListAdapter = RepoListAdapter()
        binding.query = searchViewModel.query
        binding.repoList.adapter = repoListAdapter
        binding.searchResult = searchViewModel.results
        searchViewModel.results.observe(viewLifecycleOwner) { resource ->
            if (resource.status != Status.SUCCESS) return@observe
            repoListAdapter.submitList(resource.data?.items)
        }

        initSearchInputListener()
    }

    private fun initSearchInputListener() {
        binding.input.setOnEditorActionListener { view, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(view)
                true
            } else {
                false
            }
        }
    }

    private fun doSearch(view: View) {
        val query = binding.input.text.toString()
        dismissKeyboard(view.windowToken)
        searchViewModel.setQuery(query)
    }

    private fun dismissKeyboard(windowToken: IBinder?) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }
}