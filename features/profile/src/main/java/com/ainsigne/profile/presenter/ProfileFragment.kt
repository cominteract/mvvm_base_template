package com.ainsigne.profile.presenter

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import com.ainsigne.common.base.BaseFragment
import com.ainsigne.common.utils.extension.EMPTY
import com.ainsigne.common.utils.extension.gone
import com.ainsigne.common.utils.extension.loadUrl
import com.ainsigne.common.utils.extension.setVisible
import com.ainsigne.common.utils.extension.visible
import com.ainsigne.common.utils.network.NetworkStatus
import com.ainsigne.core.di.coreComponent
import com.ainsigne.profile.R
import com.ainsigne.profile.databinding.FragmentProfileBinding
import com.ainsigne.profile.di.DaggerProfileComponent
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::inflate
){


    /** Gallery Permission Request */
    private val galleryRequest = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        viewModel.userProfilePhotoLiveData.postValue(uri.toString())
    }

    @Inject
    lateinit var viewModel: ProfileViewModel

//    val getContent = requireActivity().registerForActivityResult(GetContent()) { uri: Uri? ->
//        // Handle the returned Uri
//    }

    private fun toggleEdit(isEditing: Boolean) {
        binding.buttonEdit.setVisible(!isEditing && !isNewUser(), false)
        Timber.d(" Why is it open $isEditing ${isNewUser() }${isEditing && !isNewUser()}")
        binding.containerSave.setVisible(isEditing && !isNewUser(), false)
        binding.editProfileGender.setVisible(isEditing, false)
        binding.editProfileName.setVisible(isEditing, false)
        binding.editProfileOccupation.setVisible(isEditing, false)
        binding.textProfileGender.setVisible(!isEditing, false)
        binding.textProfileName.setVisible(!isEditing, false)
        binding.textProfileOccupation.setVisible(!isEditing, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refreshProfile()
        getProfileData()
        binding.buttonEdit.setOnClickListener {
            toggleEdit(true)
        }
        binding.buttonCancel.setOnClickListener {
            toggleEdit(false)
        }
        binding.buttonSave.setOnClickListener {
            viewModel.saveProfile(
                binding.editProfileName.text.toString(),
                binding.editProfileGender.text.toString(),
                binding.editProfileOccupation.text.toString(),
                binding.editProfileUsername.text.toString(),
                binding.editProfilePassword.text.toString()
            )
            toggleEdit(false)
        }

        binding.buttonUpload.setOnClickListener {
            galleryRequest.launch("image/*")
        }

        binding.buttonLoginOrLogout.setOnClickListener {
            if (isNewUser() && binding.editProfileUsername.length() > 4 &&
                    binding.editProfilePassword.length() > 8) {
                viewModel.saveProfile(
                    binding.editProfileName.text.toString(),
                    binding.editProfileGender.text.toString(),
                    binding.editProfileOccupation.text.toString(),
                    binding.editProfileUsername.text.toString(),
                    binding.editProfilePassword.text.toString()
                )
                toggleEdit(false)
            } else if (isNewUser().not()) {
                viewModel.logoutProfile()
            }
        }
    }

    private fun isNewUser(): Boolean {
        return binding.buttonLoginOrLogout.text == getString(R.string.login_profile)
    }

    private fun getProfileData() {
        viewModel.userProfilePhotoLiveData.observe(viewLifecycleOwner) { result ->
            if (result.isNullOrEmpty().not()) {
                binding.imageProfile.loadUrl(
                    result
                )
            }
        }
        viewModel.userProfileLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkStatus.Loading -> {
                    showLoading()
                }
                is NetworkStatus.Success -> {
                    hideLoading()
                    result.data?.let {
                        activity?.runOnUiThread {
                            binding.imageProfile.loadUrl(it.image)
                            binding.imageProfile.visible()
                            binding.textProfileOccupation.text = it.occupation
                            binding.textProfileGender.text = it.gender
                            binding.textProfileName.text = requireActivity().getString(
                                R.string.full_name,
                                it.name.first,
                                it.name.middle,
                                it.name.last
                            )
                            binding.editProfileOccupation.setText(it.occupation)
                            binding.editProfileGender.setText(it.gender)
                            binding.editProfileName.setText(
                                requireActivity().getString(
                                    R.string.full_name,
                                    it.name.first,
                                    it.name.middle,
                                    it.name.last
                                )
                            )
                            binding.editProfileUsername.setText(
                                it.username
                            )
                            binding.editProfilePassword.setText(
                                it.password
                            )
                            binding.editProfilePassword.gone()
                            binding.editProfileUsername.gone()
                            binding.buttonLoginOrLogout.text = getString(R.string.logout_profile)
                            toggleEdit(false)
                        }
                    }
                }
                is NetworkStatus.Error -> {
                    hideLoading()
                    result.errorMessage?.let {
                        show(it, true, com.ainsigne.ui.R.color.inline_error)
                        binding.imageProfile.loadUrl(EMPTY)
                        binding.editProfilePassword.visible()
                        binding.editProfileUsername.visible()
                        binding.buttonLoginOrLogout.text = getString(R.string.login_profile)
                        toggleEdit(true)
                    }
                }
                else -> Timber.d(" Unhandled DB ")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerProfileComponent.factory().create(coreComponent()).inject(this)
    }

    override fun showLoading() {
        binding.includeProgressBar.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.includeProgressBar.progressBar.visibility = View.GONE
    }

}
