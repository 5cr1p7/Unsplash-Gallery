package com.ramilkapev.kts_android_09_2021

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ramilkapev.kts_android_09_2021.databinding.FragmentPhotoDetailsBinding
import com.ramilkapev.kts_android_09_2021.databinding.LoadingItemBinding

class PhotoDetailsFragment : Fragment(R.layout.fragment_photo_details) {

    private val binding by viewBinding(FragmentPhotoDetailsBinding::bind)
    private val args: PhotoDetailsFragmentArgs by navArgs()
    private val photoDetailsViewModel: PhotoDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoDetailsViewModel.getPhotoInfo(args.photoId)

        photoDetailsViewModel.photoInfo.observe(viewLifecycleOwner, { image ->
            Glide.with(requireView()).load(image.urls.regular).into(binding.image)
            Glide.with(requireView()).load(image.user.profileImage.medium).into(binding.avatar)
            binding.name.text = image.user.name
            binding.likesTv.text = image.likes.toString()
            setLikeImage(image.likedByUser)
        })

        photoDetailsViewModel.loadingLiveData.observe(viewLifecycleOwner, ::updateIsLoading)

        photoDetailsViewModel.error.observe(viewLifecycleOwner, {
            binding.errorTextView.text = getString(R.string.network_error)
            binding.errorTextView.isVisible = true
        })

        binding.likeBtn.setOnClickListener {
            photoDetailsViewModel.likesCounter(args.photoId)
        }
    }

    private fun updateIsLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading
        binding.likeBtn.isVisible = isLoading
    }

    private fun setLikeImage(liked: Boolean) {
        val likeImageRes = if (liked) {
            R.drawable.outline_favorite_black_24
        } else {
            R.drawable.outline_favorite_border_24
        }
        binding.likeBtn.setImageResource(likeImageRes)
    }

}