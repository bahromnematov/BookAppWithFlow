package uz.gita.bookappwithflow.data.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import uz.gita.bookappwithflow.data.remote.request.AddRequest
import uz.gita.bookappwithflow.data.remote.request.ById
import uz.gita.bookappwithflow.data.remote.response.BookListResponse

interface BookApi {
    @GET("/books")
    suspend fun getMyBooks(@Header("Authorization") token :String) : Response<List<BookListResponse>>

    @POST("/book")
   suspend fun addBook(@Header("Authorization") token :String, @Body data : AddRequest) : Response<BookListResponse>


    @POST("book/change-fav")
    suspend fun changeBookFav(@Header("Authorization") token :String, @Query("id")id:Int) : Response<Unit>


    @HTTP(method = "DELETE", path = "book", hasBody = true)
    suspend fun deleteBook(@Header("Authorization") Authorization: String, @Body bookId: ById): Response<Unit>

    @PUT("/book")
    suspend fun editContact(@Header("Authorization") token :String, @Body data: BookListResponse) : Response<BookListResponse>

}