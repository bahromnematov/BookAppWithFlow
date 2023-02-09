package uz.gita.bookappwithflow.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.bookappwithflow.data.remote.request.AddRequest
import uz.gita.bookappwithflow.data.remote.request.ById
import uz.gita.bookappwithflow.data.remote.request.UserRequestLoginApi
import uz.gita.bookappwithflow.data.remote.response.BookListResponse

interface Repository {
    fun userLogin(data : UserRequestLoginApi) : Flow<Result<Unit>>
    suspend fun startScreen() : String

    fun verifyPart(code:String):Flow<Result<Unit>>

}