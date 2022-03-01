package com.ainsigne.home.presenter

import android.content.Context
import android.os.Bundle
import android.view.View
import com.ainsigne.common.base.BaseFragment
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.core.di.coreComponent
import com.ainsigne.home.databinding.FragmentHomeBinding
import com.ainsigne.home.di.DaggerHomeComponent
import com.ainsigne.home.presenter.adapter.SwitchGamesAdapter
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    @Inject
    lateinit var viewModel: HomeViewModel

    lateinit var adapter: SwitchGamesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SwitchGamesAdapter(emptyList())
        binding.switchGamesRecyclerview.adapter = adapter
        viewModel.refreshGames()
        getSwitchGames()
    }

    private fun getSwitchGames() {
        viewModel.switchGamesLiveData.observe(viewLifecycleOwner, { result ->
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
        DaggerHomeComponent.factory().create(coreComponent()).inject(this)
    }

    override fun showLoading() {
        binding.includeProgressBar.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.includeProgressBar.progressBar.visibility = View.GONE
    }

}
