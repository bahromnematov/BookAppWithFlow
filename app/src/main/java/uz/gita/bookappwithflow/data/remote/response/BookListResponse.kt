package uz.gita.bookappwithflow.data.remote.response


data class BookListResponse(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int=0,
    val fav: Boolean=false
)




//"id": 8,
//"title": "Yengil Amallar",
//"author": "Muhammad Usmon Taqiy",
//"description": "Bu kitob qanday yengil amal qilsak ko'proq savob ishlab olishimiz haqida",
//"pageCount": 191,
//"fav": false