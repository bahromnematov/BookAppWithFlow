package uz.gita.bookappwithflow.presentation.viewModels

import androidx.lifecycle.LiveData

interface VerifyViewModel {

    val openBookScreenLiveData:LiveData<Unit>
    val progressLiveData:LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val notConnectionLiveData: LiveData<Unit>

    fun codeSent(code:String)
}