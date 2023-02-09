package uz.gita.bookappwithflow.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding

import uz.gita.bookappwithflow.R
import uz.gita.bookappwithflow.data.remote.request.AddRequest
import uz.gita.bookappwithflow.data.remote.response.BookListResponse
import uz.gita.bookappwithflow.databinding.ScreenBookBinding
import uz.gita.bookappwithflow.presentation.ui.adapter.BookAdapter
import uz.gita.bookappwithflow.presentation.viewModels.Impl.BookListViewModelImpl
import uz.gita.onlinedatabase.presentation.ui.dialog.AddBookDialog


class BookScreen:Fragment(R.layout.screen_book) {
    private val binding: ScreenBookBinding by viewBinding(ScreenBookBinding::bind)
    private val viewModel by viewModels<BookListViewModelImpl>()
    private val adapter = BookAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        bookList.layoutManager = LinearLayoutManager(requireContext())

        buttonAdd.setOnClickListener {
            viewModel.openDialog()
        }


//        adapter.deletedButtonClickedListener{
//            viewModel.delete(it)
//        }

//        buttonLogOut.setOnClickListener{
//            viewModel.openLogOutScreen()
//        }
//
//        deleteAccount.setOnClickListener{
//            viewModel.openDeletedScreen()
//        }


        viewModel.bookListLiveData.observe(viewLifecycleOwner, contactObserver)
        viewModel.openDialogLivaData.observe(
            viewLifecycleOwner,
            openAddDialogContactObserver
        )


    }


    private val contactObserver = Observer<List<BookListResponse>> {
        adapter.submitList(it)
        binding.bookList.adapter = adapter

    }

    private val openAddDialogContactObserver = Observer<Unit> {
        val dialog = AddBookDialog()
        dialog.isCancelable=false
      dialog.setAddBookListener { s, s2, s3, s4 ->
          val addRequest= AddRequest(s,s2,s3,s4.toInt())
          viewModel.addBook(addRequest)
      }

        dialog.show(childFragmentManager, null)
    }



}