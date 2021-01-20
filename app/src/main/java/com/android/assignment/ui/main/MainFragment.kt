package com.android.assignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.android.assignment.R
import com.android.assignment.data.model.CategoryWithFacts
import com.android.assignment.data.model.State
import com.android.assignment.databinding.MainFragmentBinding
import com.android.assignment.ui.list.FactAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    @Inject lateinit var viewModel: MainViewModel

    private lateinit var binding:MainFragmentBinding
    private lateinit var factAdapter: FactAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        MainFragmentBinding.inflate(inflater, container, false)
            .apply {
                binding =this
                return root
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factAdapter = FactAdapter()
        binding.rvItems.adapter = factAdapter
        setupObservers()
        viewModel.getOrSyncData()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                State.Loading -> binding.showProgress = true
                is State.Error -> {
                    binding.showProgress = false
                    showMessage(it.msg)
                }
                is State.Success<*> -> {
                    val categoryWithFacts = (it.data as CategoryWithFacts)
                    requireActivity().actionBar?.title = categoryWithFacts.category.title
                    factAdapter.submitList(categoryWithFacts.fact)
                }
                else -> {

                }
            }
        }
    }

    private fun showMessage(@StringRes msg:Int){
        Snackbar.make(binding.root,msg,Snackbar.LENGTH_LONG)
            .setAction(R.string.retry){
                viewModel.getOrSyncData()
            }.show()
    }

}