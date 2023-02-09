package uz.gita.bookappwithflow.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.bookappwithcoratine.data.remote.request.VerifyRequest
import uz.gita.bookappwithflow.data.remote.response.UserResponseApi
import uz.gita.bookappwithflow.data.remote.request.UserRequestLoginApi

interface UserApi {

    @POST("auth/sign-in")
   suspend fun signInUser(@Body data: UserRequestLoginApi) : Response<UserResponseApi>

    @POST("auth/sign-in/verify")
   suspend fun signInUserVerify(@Header("Authorization")authorization:String, @Body data: VerifyRequest) : Response<UserResponseApi>


}