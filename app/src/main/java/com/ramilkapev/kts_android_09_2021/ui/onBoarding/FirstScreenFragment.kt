package com.ramilkapev.kts_android_09_2021.ui.onBoarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ramilkapev.kts_android_09_2021.R
import com.ramilkapev.kts_android_09_2021.databinding.FragmentFirstScreenBinding

class FirstScreenFragment : Fragment(R.layout.fragment_first_screen) {

    private val viewFirstScreenBinding by viewBinding(FragmentFirstScreenBinding::bind)
    private val firstScreenViewModel: FirstScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewFirstScreenBinding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_firstScreenFragment_to_loginFragment)
        }
    }
}