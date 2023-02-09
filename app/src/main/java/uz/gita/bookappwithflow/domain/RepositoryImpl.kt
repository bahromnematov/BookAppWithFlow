package uz.gita.bookappwithflow.domain

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.bookappwithcoratine.data.remote.request.VerifyRequest
import uz.gita.bookappwithflow.data.local.AppPreferences
import uz.gita.bookappwithflow.data.remote.ApiClient
import uz.gita.bookappwithflow.data.remote.api.UserApi
import uz.gita.bookappwithflow.data.remote.request.UserRequestLoginApi
import uz.gita.bookappwithflow.data.remote.response.ErrorResponse

class RepositoryImpl private constructor(
    private val authApi: UserApi,
    private val pref: AppPreferences,
    private val gson: Gson
) : Repository {


    companion object {
        private lateinit var repository: Repository

        fun init() {
            val api = ApiClient.retrofit.create(UserApi::class.java)
            repository = RepositoryImpl(api, AppPreferences.getInstance(), Gson())
        }

        fun getInstance(): Repository = repository
    }

    override fun userLogin(data: UserRequestLoginApi): Flow<Result<Unit>> = flow<Result<Unit>> {
        val reposne = authApi.signInUser(data)
        if (reposne.isSuccessful) {
            reposne.body()?.let {
                pref.token = it.token
                emit(Result.success(Unit))

            }


        } else {
            reposne.errorBody()?.let {
                val error = gson.fromJson(it.string(), ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)



    override fun verifyPart(code: String): Flow<Result<Unit>> = flow<Result<Unit>> {
        val response=authApi.signInUserVerify("Bearer ${pref.token}", VerifyRequest(code))
        if (response.isSuccessful){
            response.body()?.let {
                pref.startSceen="Contact"
                pref.token=it.token
                emit(Result.success(Unit))
            }
        }else{
            response.errorBody()?.let {
                val error = gson.fromJson(it.string(), ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)

    override suspend fun startScreen(): String = pref.startSceen


}