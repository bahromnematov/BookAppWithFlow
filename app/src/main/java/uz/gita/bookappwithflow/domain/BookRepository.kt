package uz.gita.bookappwithflow.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappwithflow.data.remote.request.AddRequest
import uz.gita.bookappwithflow.data.remote.request.ById
import uz.gita.bookappwithflow.data.remote.request.UserRequestLoginApi
import uz.gita.bookappwithflow.data.remote.response.BookListResponse

interface BookRepository {
    fun getAllBooks(): Flow<Result<List<BookListResponse>>>
    fun addBook(data: AddRequest): Flow<Result<BookListResponse>>
    fun deletedBook(id: ById): Flow<Result<Unit>>
    fun editBook(data: BookListResponse): Flow<Result<Unit>>
}