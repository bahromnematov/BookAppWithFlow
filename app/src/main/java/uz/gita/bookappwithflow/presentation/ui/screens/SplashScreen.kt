package uz.gita.bookappwithflow.presentation.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.gita.bookappwithflow.R
import uz.gita.bookappwithflow.presentation.viewModels.Impl.SplashViewModelImpl

@SuppressLint("CustomSplashScreen")
class SplashScreen: Fragment(R.layout.screen_splash) {
    private val viewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openLoginScreenLiveData.observe(viewLifecycleOwner,openLoginScreenObserver)
        viewModel.openBookScreenLiveData.observe(viewLifecycleOwner,openBookScreenObserver)
    }


    private val openLoginScreenObserver= Observer<Unit>{
        findNavController().navigate(R.id.action_splashScreen_to_loginScreen)
    }

    private val openBookScreenObserver=Observer<Unit>{
        findNavController().navigate(R.id.action_splashScreen_to_BookScreen)
    }
}