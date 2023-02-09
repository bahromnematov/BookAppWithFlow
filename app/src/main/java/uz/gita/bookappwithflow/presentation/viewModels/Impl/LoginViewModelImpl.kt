package uz.gita.bookappwithflow.presentation.viewModels.Impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithflow.data.remote.request.UserRequestLoginApi
import uz.gita.bookappwithflow.domain.RepositoryImpl
import uz.gita.bookappwithflow.presentation.viewModels.LoginViewModel
import uz.gita.contactappauth.utils.isConnected

class LoginViewModelImpl : ViewModel(), LoginViewModel {
    private val repository = RepositoryImpl.getInstance()

    override val openVerifyScreenLiveData = MutableLiveData<Unit>()

    override val notConnectionLiveData = MutableLiveData<Unit>()

    override val changeButtonStatusLiveData = MutableLiveData<Boolean>()

    override val progressLiveData = MutableLiveData<Boolean>()

    override val errorLiveData = MutableLiveData<String>()


    override fun userLogin(name: String, password: String) {
        if (!isConnected()) {
            notConnectionLiveData.value = Unit
            return
        }


        progressLiveData.value = true
        changeButtonStatusLiveData.value = false
        repository.userLogin(UserRequestLoginApi(password, name)).onEach {
            it.onSuccess {
                progressLiveData.value = false
                openVerifyScreenLiveData.value = Unit
            }
            it.onFailure {
                progressLiveData.value = false
                errorLiveData.value = it.message
                Log.d("TTT","${it.message}")
            }
        }.launchIn(viewModelScope)


    }
}

