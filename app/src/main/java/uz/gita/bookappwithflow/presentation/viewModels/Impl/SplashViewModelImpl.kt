package uz.gita.bookappwithflow.presentation.viewModels.Impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.bookappwithflow.domain.RepositoryImpl
import uz.gita.bookappwithflow.presentation.viewModels.SplashViewModel

class SplashViewModelImpl:ViewModel(), SplashViewModel {
    val repository=RepositoryImpl.getInstance()
    override val openLoginScreenLiveData=MutableLiveData<Unit>()
    override val openBookScreenLiveData=MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            delay(1000)
            if (repository.startScreen()=="LOGIN") openLoginScreenLiveData.postValue(Unit)
            else openBookScreenLiveData.postValue(Unit)
        }
    }


}