package com.android.assignment.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.view.*
import androidx.annotation.StringRes
import com.android.assignment.R
import com.android.assignment.data.model.CategoryWithFacts
import com.android.assignment.data.model.State
import com.android.assignment.databinding.MainFragmentBinding
import com.android.assignment.di.ActivityScope
import com.android.assignment.ui.list.FactAdapter
import com.android.assignment.util.Constants
import com.android.assignment.util.NetworkHelper
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@ActivityScope
class MainFragment : DaggerFragment() {

    @Inject lateinit var viewModel: MainViewModel

    @Inject lateinit var networkHelper: NetworkHelper

    private lateinit var binding:MainFragmentBinding
    private lateinit var factAdapter: FactAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
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
        networkHelper.networkCallback {
            viewModel.syncData()
        }
        binding.swipeRefreshView.setOnRefreshListener {
            showMessage(R.string.syncing, Snackbar.LENGTH_SHORT)
            viewModel.syncData(true)
        }
        savedInstanceState?.let {
                Handler(Looper.getMainLooper())
                    .postDelayed({
                        val listState: Parcelable = it.getParcelable(Constants.KEY_SCROLL_POSITION)!!
                        binding.rvItems.layoutManager?.onRestoreInstanceState(listState)

                    },100)
        }
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                State.Loading -> {
                    if(!binding.swipeRefreshView.isRefreshing)
                    binding.showProgress = true
                }
                is State.Error -> {
                    binding.showProgress = false
                    showMessage(it.msg){
                        viewModel.getOrSyncData()
                    }
                    binding.swipeRefreshView.isRefreshing = false
                }
                is State.Success<*> -> {
                    val categoryWithFacts = (it as State.Success<CategoryWithFacts>).data
                    requireActivity().title = categoryWithFacts.category.title
                    factAdapter.submitList(categoryWithFacts.fact)
                    binding.showProgress = false
                    if (categoryWithFacts.fact.isEmpty())
                        showMessage(R.string.no_data)
                    binding.swipeRefreshView.isRefreshing = false
                }
                else -> {
                    binding.showProgress = false
                }
            }
        }
    }

    private fun showMessage(@StringRes msg:Int, duration:Int = Snackbar.LENGTH_LONG, action:((view:View) -> Unit)? = null){
        Snackbar.make(binding.root,msg,duration).apply{
                    action?.let {
                        setAction(R.string.retry,it)}
                    show()
                }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.refresh)
        {
            showMessage(R.string.syncing, Snackbar.LENGTH_SHORT)
            viewModel.syncData(true)
            return false
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(Constants.KEY_SCROLL_POSITION,binding.rvItems.layoutManager?.onSaveInstanceState())
        super.onSaveInstanceState(outState)
    }


}