package uz.gita.bookappwithflow.presentation.viewModels

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val openLoginScreenLiveData:LiveData<Unit>
    val openBookScreenLiveData:LiveData<Unit>
}