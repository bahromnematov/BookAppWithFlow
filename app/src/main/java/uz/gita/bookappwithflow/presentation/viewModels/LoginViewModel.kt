package uz.gita.bookappwithflow.presentation.viewModels

import androidx.lifecycle.LiveData

interface LoginViewModel {
    val openVerifyScreenLiveData: LiveData<Unit>
    val notConnectionLiveData: LiveData<Unit>
    val changeButtonStatusLiveData: LiveData<Boolean>
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>

    fun userLogin(name: String, password: String)
}