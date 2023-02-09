package uz.gita.onlinedatabase.presentation.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.bookappwithflow.R
import uz.gita.bookappwithflow.databinding.AddDialogBinding

@Suppress("CAST_NEVER_SUCCEEDS")
class AddBookDialog:DialogFragment(R.layout.add_dialog) {
    private var addBookListener:((String, String,String,String)->Unit)?=null
    private val binding: AddDialogBinding by viewBinding(AddDialogBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,R.style.DialogStyle)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)=with(binding){

        cancel.setOnClickListener { dismiss() }
        btnSave.setOnClickListener{
            addBookListener?.invoke(title.text.toString(),auther.text.toString(),desciribtion.text.toString(), pageCount.text.toString())
            dismiss()
        }
    }


    fun setAddBookListener(block: (String, String,String,String) -> Unit) {
        addBookListener = block
    }

}