package uz.gita.bookappwithflow.data.remote.request

data class AddRequest(
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int

)