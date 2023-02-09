package uz.gita.bookappwithflow.presentation.ui.screens

import `in`.aabhasjindal.otptextview.OTPListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookappwithflow.R
import uz.gita.bookappwithflow.databinding.ScreenVerifyBinding
import uz.gita.bookappwithflow.presentation.viewModels.Impl.VerifyViewModelImpl
import uz.gita.contactappauth.utils.showToast
import java.util.*


class VerifyScreen:Fragment(R.layout.screen_verify) {
    private val viewModel by viewModels<VerifyViewModelImpl>()
    private val binding by viewBinding(ScreenVerifyBinding::bind)
    private lateinit var code:String
    private lateinit var timer:Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openBookScreenLiveData.observe(this, openBookScreenObserver)
        viewModel.notConnectionLiveData.observe(this, notConnectionObserver)
        viewModel.errorLiveData.observe(this,errorMessageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)=with(binding) {
        loadTime()
        inputSmsCode.otpListener = object : OTPListener {
            override fun onInteractionListener() {
                inputSmsCode.requestFocusOTP()


            }

            override fun onOTPComplete(otp: String) {
                if (otp.length==6) {
                    inputSmsCode.showSuccess()
                    binding.buttonEnter.isEnabled=true
                   code=otp
                }
            }
        }

        buttonEnter.setOnClickListener {
            viewModel.progressLiveData.value=true
            viewModel.codeSent(code)
            Log.d("TTT","$code")
        }
    }


    private fun loadTime() {
        var timeCount=120
         timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                requireActivity().runOnUiThread(Runnable {
                    timeCount--
                    if(timeCount!=0) {
                        setTime(timeCount)
                    }
                })
            }
        }, 1000, 1000)
    }

    @SuppressLint("DefaultLocale")
    private fun setTime(timeCount: Int) {
        val second = timeCount % 60
        val hour = timeCount / 3600
        val minute = (timeCount - hour * 3600) / 60
        binding.timer.text = String.format(Locale.getDefault(), "%02d:%02d", minute, second)
    }

    private val openBookScreenObserver = Observer<Unit> { findNavController().navigate(R.id.action_LoginverifyScreen_to_BookScreen) }
    private val notConnectionObserver = Observer<Unit> { showToast("Not connection!") }
    private val errorMessageObserver = Observer<String>{showToast(it)}

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }
}