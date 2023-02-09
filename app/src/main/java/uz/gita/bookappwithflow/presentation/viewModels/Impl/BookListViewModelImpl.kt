package uz.gita.bookappwithflow.presentation.viewModels.Impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.bookappwithflow.data.remote.request.AddRequest
import uz.gita.bookappwithflow.data.remote.request.ById
import uz.gita.bookappwithflow.data.remote.response.BookListResponse
import uz.gita.bookappwithflow.domain.BookRepositoryImpl
import uz.gita.bookappwithflow.presentation.viewModels.BookListViewModel
import uz.gita.contactappauth.utils.isConnected

class BookListViewModelImpl : ViewModel(), BookListViewModel {
    private val repository=BookRepositoryImpl.getInstance()
    override val openDialogLivaData = MutableLiveData<Unit>()

    private val list=ArrayList<BookListResponse>()

    override val bookListLiveData = MutableLiveData<List<BookListResponse>>()

    override val progressLivedata=MutableLiveData<Boolean>()

    override val notConnectionLiveData=MutableLiveData<Unit>()

    override val errorLiveData=MutableLiveData<String>()

    override fun getAllBooks() {
        if (!isConnected()) {
            notConnectionLiveData.value = Unit
            return
        }

        progressLivedata.value=true
        repository.getAllBooks().onEach {
            it.onSuccess {
                progressLivedata.value = false
                list.addAll(it)
                bookListLiveData.value=it
            }
            it.onFailure {
                progressLivedata.value = false
                errorLiveData.value = it.message
            }
        }.launchIn(viewModelScope)
    }

   fun openDialog(){
       openDialogLivaData.value=Unit
   }

    override fun addBook(data: AddRequest) {
        if (!isConnected()) {
            notConnectionLiveData.value = Unit
            return
        }

        progressLivedata.value=true
        repository.addBook(data).onEach {
            it.onSuccess {
                progressLivedata.value = false
                list.add(it)
                bookListLiveData.value=list
            }
            it.onFailure {
                progressLivedata.value = false
                errorLiveData.value = it.message
            }
        }.launchIn(viewModelScope)
    }

    override fun deletedBook(id: ById) {
        TODO("Not yet implemented")
    }

    override fun editBook(data: BookListResponse) {
        TODO("Not yet implemented")
    }
}