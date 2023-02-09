package uz.gita.bookappwithflow.domain

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.bookappwithflow.data.local.AppPreferences
import uz.gita.bookappwithflow.data.remote.ApiClient
import uz.gita.bookappwithflow.data.remote.api.BookApi
import uz.gita.bookappwithflow.data.remote.request.AddRequest
import uz.gita.bookappwithflow.data.remote.request.ById
import uz.gita.bookappwithflow.data.remote.response.BookListResponse
import uz.gita.bookappwithflow.data.remote.response.ErrorResponse

class BookRepositoryImpl private constructor(
    private val authApi: BookApi,
    private val pref: AppPreferences,
    private val gson: Gson
) : BookRepository {


    companion object {
        private lateinit var repository: BookRepository

        fun init() {
            val api = ApiClient.retrofit.create(BookApi::class.java)
            repository = BookRepositoryImpl(api, AppPreferences.getInstance(), Gson())
        }

        fun getInstance(): BookRepository = repository
    }

    override fun getAllBooks(): Flow<Result<List<BookListResponse>>> = flow<Result<List<BookListResponse>>> {
      val response=authApi.getMyBooks("Bearer ${pref.token}")
        if(response.isSuccessful){
            response.body()?.let {
                emit(Result.success(it))
            }
        }else{
            response.errorBody()?.let {
                val error=gson.fromJson(it.string(),ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)

    override fun addBook(data: AddRequest): Flow<Result<BookListResponse>> = flow<Result<BookListResponse>> {
        val response=authApi.addBook("Bearer ${pref.token}",data)
        if(response.isSuccessful){
            response.body()?.let {
                emit(Result.success(it))
            }
        }else{
            response.errorBody()?.let {
                val error=gson.fromJson(it.string(),ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }.catch {
            emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)

    override fun deletedBook(id: ById): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun editBook(data: BookListResponse): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

}