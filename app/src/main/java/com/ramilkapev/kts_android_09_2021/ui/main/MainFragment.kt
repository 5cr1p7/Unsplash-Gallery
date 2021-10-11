package com.ramilkapev.kts_android_09_2021.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ramilkapev.kts_android_09_2021.PhotoDetailsFragment
import com.ramilkapev.kts_android_09_2021.PhotoDetailsFragmentArgs
import com.ramilkapev.kts_android_09_2021.utils.PaginationScrollListener
import com.ramilkapev.kts_android_09_2021.R
import com.ramilkapev.kts_android_09_2021.databinding.FragmentMainBinding
import com.ramilkapev.kts_android_09_2021.networking.models.ProfileImage
import com.ramilkapev.kts_android_09_2021.networking.models.Result
import com.ramilkapev.kts_android_09_2021.networking.models.Urls
import com.ramilkapev.kts_android_09_2021.networking.models.User
import com.ramilkapev.kts_android_09_2021.utils.autoCleared
import java.util.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private val mainBinding by viewBinding(FragmentMainBinding::bind)
    private var imagesAdapter: ImagesAdapter by autoCleared()
    private var query: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        mainBinding.searchBtn.setOnClickListener {
            query = mainBinding.searchEt.editableText.toString()
            mainViewModel.search(query)
        }

        mainViewModel.photosState.observe(viewLifecycleOwner, {
            imagesAdapter.items = it
        })

        mainViewModel.likesState.observe(viewLifecycleOwner, {
            imagesAdapter.notifyDataSetChanged()
        })

        mainViewModel.error.observe(viewLifecycleOwner, {
            mainBinding.errorTextView.text = getString(R.string.network_error)
        })

        mainViewModel.loadingLiveData.observe(viewLifecycleOwner, ::updateIsLoading)

    }

    private fun init() {
        imagesAdapter = ImagesAdapter(
            onIncreaseLike = { item ->
                mainViewModel.likesCounter(item)
            },
            onClick = {
                val action = MainFragmentDirections.actionMainFragmentToPhotoDetailsFragment(it.id)
                findNavController().navigate(action)
                Toast.makeText(requireContext(), it.likes.toString(), Toast.LENGTH_SHORT).show()
            }
        )

        with(mainBinding.imagesRv) {
            val orientation = RecyclerView.VERTICAL
            adapter = imagesAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            addOnScrollListener(
                PaginationScrollListener(
                    layoutManager = layoutManager as LinearLayoutManager,
                    requestNextItems = ::loadMoreItems,
                    visibilityThreshold = 0
                )
            )

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun updateIsLoading(isLoading: Boolean) = with(mainBinding) {
        progressBar.isVisible = isLoading
        errorTextView.isVisible = isLoading
    }

    private fun getDefaultItems() = List(20) {
        val randomUUID = Random().nextInt()
        when ((1..2).random()) {
            1 -> Result(
                id = randomUUID.toString(),
                likedByUser = false,
                likes = 10,
                user = User(
                    id = randomUUID.toString(),
                    name = "vasya",
                    profileImage = ProfileImage("https://images.unsplash.com/profile-1596578750003-cc29df35976eimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"),
                    username = "adasd"
                ),
                urls = Urls("https://images.unsplash.com/photo-1497215728101-856f4ea42174?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNjA2NDl8MHwxfHNlYXJjaHwyfHxvZmZpY2V8ZW58MHx8fHwxNjMzMjc3ODQy&ixlib=rb-1.2.1&q=80&w=1080"),
                description = ""
            )
            2 -> User(
                id = randomUUID.toString(),
                name = "vasya",
                profileImage = ProfileImage("https://images.unsplash.com/profile-1596578750003-cc29df35976eimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"),
                username = "adasd"
            )
            else -> error("Wrong random number")
        }
    }

    private fun loadMoreItems() {
        val newItems = imagesAdapter.items.toMutableList().apply {
            if (lastOrNull() is LoadingItem) {
                removeLastOrNull()
            }
        } + getDefaultItems() + LoadingItem()
        imagesAdapter.items = newItems
        Log.d("Pagination", newItems.size.toString())
    }
}