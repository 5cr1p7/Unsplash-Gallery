package com.ramilkapev.kts_android_09_2021.ui.main

import android.os.Bundle
import android.system.Os.bind
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ramilkapev.kts_android_09_2021.R
import com.ramilkapev.kts_android_09_2021.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private val mainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mainBinding) {
            imagesRv.layoutManager = LinearLayoutManager(requireContext())
            imagesRv.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
            imagesRv.adapter = ImagesAdapter()
        }
    }
}