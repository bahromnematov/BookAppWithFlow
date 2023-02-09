package uz.gita.bookappwithflow.app

import android.app.Application
import android.content.Context
import uz.gita.bookappwithflow.data.local.AppPreferences
import uz.gita.bookappwithflow.domain.BookRepositoryImpl
import uz.gita.bookappwithflow.domain.RepositoryImpl

class App:Application() {

    companion object{
        lateinit var appContext: Context
        private set
    }
    override fun onCreate() {
        super.onCreate()
        appContext=this
        AppPreferences.init(this)
        RepositoryImpl.init()
        BookRepositoryImpl.init()
    }
}