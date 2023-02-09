package uz.gita.bookappwithflow.data.local

import android.content.Context
import android.content.SharedPreferences

class AppPreferences {
    companion object{
        private lateinit var pref: SharedPreferences
        private lateinit var instance:AppPreferences


        fun init(context: Context){
            pref=context.getSharedPreferences("MYBOOK", Context.MODE_PRIVATE)
            instance= AppPreferences()
        }

        fun getInstance():AppPreferences = instance
    }

    var startSceen:String
        set(value)= pref.edit().putString("START_SCREEN",value).apply()
        get()= pref.getString("START_SCREEN","LOGIN")!!


    var token:String
        set(value)= pref.edit().putString("TOKEN",value).apply()
        get()= pref.getString("TOKEN","")!!

    var name:String
        set(value)= pref.edit().putString("NAME",value).apply()
        get()= pref.getString("NAME","")!!

    var password:String
        set(value)= pref.edit().putString("PASSWORD",value).apply()
        get()= pref.getString("PASSWORD","")!!
}