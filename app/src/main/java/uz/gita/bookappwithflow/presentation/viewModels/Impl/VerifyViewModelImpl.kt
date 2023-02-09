package uz.gita.bookappwithflow.presentation.viewModels.Impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithflow.domain.RepositoryImpl
import uz.gita.bookappwithflow.presentation.viewModels.VerifyViewModel
import uz.gita.contactappauth.utils.isConnected

class VerifyViewModelImpl : ViewModel(), VerifyViewModel {
    private val repository=RepositoryImpl.getInstance()
    override val openBookScreenLiveData = MutableLiveData<Unit>()

    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()

    override val notConnectionLiveData = MutableLiveData<Unit>()


    override fun codeSent(code: String) {
        if (!isConnected()) {
            notConnectionLiveData.value=Unit
            return
        }
        progressLiveData.value=false

        repository.verifyPart(code).onEach {
            it.onSuccess {
                progressLiveData.value=false
                openBookScreenLiveData.value=Unit
            }
            it.onFailure {
                progressLiveData.value=false
                errorLiveData.value=it.message
            }
        }.launchIn(viewModelScope)


    }
}