package uz.gita.bookappwithflow.presentation.ui.screens

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookappwithflow.R
import uz.gita.bookappwithflow.databinding.ScreenLoginBinding
import uz.gita.bookappwithflow.presentation.viewModels.Impl.LoginViewModelImpl
import uz.gita.contactappauth.utils.amount
import uz.gita.contactappauth.utils.myAddTextChangedListener
import uz.gita.contactappauth.utils.showToast
import uz.gita.contactappauth.utils.state

class LoginScreen : Fragment(R.layout.screen_login) {
    private val viewModel by viewModels<LoginViewModelImpl>()
    private val binding by viewBinding(ScreenLoginBinding::bind)
    private var boolPass = false
    private var boolPhone = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.notConnectionLiveData.observe(this, notConnectionObserver)
        viewModel.openVerifyScreenLiveData.observe(this, openVerifyScreenObserver)
        viewModel.errorLiveData.observe(this,errorObserver)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        inputName.myAddTextChangedListener {
            boolPhone = it.length > 3
            check()
        }


        inputPassword.myAddTextChangedListener {
            boolPass = it.length > 3
            check()
        }

        showPassword.setOnClickListener {
            showPassword.visibility = View.INVISIBLE
            inputPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            hidePassword.visibility = View.VISIBLE
        }

        hidePassword.setOnClickListener {
            showPassword.visibility = View.VISIBLE
            inputPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            hidePassword.visibility = View.INVISIBLE
        }

        buttonLogin.setOnClickListener {
            Log.d("TTT","LoginButton")
            viewModel.userLogin(
                inputName.amount(), inputPassword.text.toString()
            )
        }

        viewModel.changeButtonStatusLiveData.observe(viewLifecycleOwner, changeButtonStatusObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }

    private val openVerifyScreenObserver = Observer<Unit> {findNavController().navigate(R.id.action_loginScreen_to_verifyScreen)}
    private val notConnectionObserver = Observer<Unit> { showToast("Not connection!") }
    private val changeButtonStatusObserver = Observer<Boolean> { binding.buttonLogin.isEnabled = it }
    private val progressObserver = Observer<Boolean> { binding.progress.state(it) }
    private val errorObserver = Observer<String> { showToast(it) }


    private fun check() {
        binding.buttonLogin.isEnabled = boolPhone && boolPass
    }


}