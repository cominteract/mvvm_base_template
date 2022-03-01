package com.ainsigne.futurama.presenter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ainsigne.common.base.BaseFragment
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.core.di.coreComponent
import com.ainsigne.futurama.R
import com.ainsigne.futurama.databinding.FragmentFuturamaCharactersBinding
import com.ainsigne.futurama.di.DaggerFuturamaComponent
import com.ainsigne.futurama.presenter.adapter.FuturamaCharactersAdapter
import javax.inject.Inject

class FuturamaCharactersFragment : BaseFragment<FragmentFuturamaCharactersBinding>(
    FragmentFuturamaCharactersBinding::inflate
){


    @Inject
    lateinit var viewModel: FuturamaViewModel

    lateinit var adapter: FuturamaCharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FuturamaCharactersAdapter(emptyList())
        binding.futuramaCharactersRecyclerview.adapter = adapter
        viewModel.refreshFuturamaCharacters()
        getFuturamaCharacters()
    }

    private fun getFuturamaCharacters() {
        viewModel.futuramaCharactersLiveData.observe(viewLifecycleOwner, { result ->
            when (result){
                is NetworkStatus.Loading -> {
                    showLoading()
                }
                is NetworkStatus.DBSuccess -> {
                    hideLoading()

                    result.data?.let {
                        activity?.runOnUiThread {
                            adapter.updateList(it)
                        }
                        showLoading()
                    }
                }
                is NetworkStatus.Success -> {
                    hideLoading()
                    result.data?.let {
                        activity?.runOnUiThread {
                            adapter.updateList(it)
                        }
                    }
                }
                is NetworkStatus.Error -> {
                    hideLoading()
                    result.errorMessage?.let {
                        show(it, true, com.ainsigne.ui.R.color.inline_error)
                    }
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFuturamaComponent.factory().create(coreComponent()).inject(this)
    }

    override fun showLoading() {
        binding.includeProgressBar.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.includeProgressBar.progressBar.visibility = View.GONE
    }

}
