package uz.gita.contactappauth.utils

import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.ContentLoadingProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun AppCompatEditText.myAddTextChangedListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString())
        }
    }
}


fun EditText.myAddTextChangedListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString())
        }
    }
}



fun ContentLoadingProgressBar.state(bool: Boolean) {
    if (bool) this.show()
    else this.hide()
}


fun AppCompatEditText.amount() : String = this.text.toString()
fun EditText.amount() : String = this.text.toString()


fun <T : ViewBinding> T.myApply(block: T.() -> Unit) {
    block(this)
}