package uz.gita.bookappwithflow.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.bookappwithflow.data.remote.request.AddRequest
import uz.gita.bookappwithflow.data.remote.request.ById
import uz.gita.bookappwithflow.data.remote.response.BookListResponse

interface BookListViewModel {
    val openDialogLivaData:LiveData<Unit>
    val bookListLiveData:LiveData<List<BookListResponse>>
    val progressLivedata:LiveData<Boolean>
    val notConnectionLiveData:LiveData<Unit>
    val errorLiveData:LiveData<String>

    fun getAllBooks()
    fun addBook(data:AddRequest)
    fun deletedBook(id:ById)
    fun editBook(data:BookListResponse)
}