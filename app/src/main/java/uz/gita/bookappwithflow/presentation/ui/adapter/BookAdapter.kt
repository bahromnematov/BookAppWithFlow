package uz.gita.bookappwithflow.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.bookappwithflow.R
import uz.gita.bookappwithflow.data.remote.response.BookListResponse
import uz.gita.bookappwithflow.databinding.ItemBookBinding


class BookAdapter:ListAdapter<BookListResponse,BookAdapter.holder>(myDiffUtil) {

    private var favButtonClickedListener: ((BookListResponse) -> Unit)? = null

    fun setfavButtonClickedListener(f:(BookListResponse)->Unit){
        favButtonClickedListener=f
    }

    private var buttonDeletedClickedListener: ((Int) -> Unit)? = null

    fun deletedButtonClickedListener(f:(Int)->Unit){
        buttonDeletedClickedListener=f
    }


    object myDiffUtil:DiffUtil.ItemCallback<BookListResponse>(){
        override fun areItemsTheSame(oldItem: BookListResponse, newItem: BookListResponse): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: BookListResponse, newItem: BookListResponse): Boolean {
            return oldItem==newItem
        }

    }

    inner class holder(private val binding: ItemBookBinding):RecyclerView.ViewHolder(binding.root){

        init {

            binding.deleted.setOnClickListener {
                buttonDeletedClickedListener?.invoke(getItem(absoluteAdapterPosition).id)
            }

            binding.favourate.setOnClickListener {
                val data=getItem(absoluteAdapterPosition)
                val book = if (data.fav){
                    BookListResponse(data.id,data.title,data.author,data.description,data.pageCount,false)
                }else{
                    BookListResponse(data.id,data.title,data.author,data.description,data.pageCount,true)
                }
                favButtonClickedListener?.invoke(book)
            }
        }

        fun bind(){
            getItem(absoluteAdapterPosition).apply {
                binding.titile.text=title
                binding.auther.text=author
                binding.desciribtion.text=description
                binding.pageCount.text=pageCount.toString()
                if (!fav) {
                    binding.favourate.setBackgroundResource(R.drawable.ic_baseline_star_outline_24)
                }else binding.favourate.setBackgroundResource(R.drawable.ic_baseline_star_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder =
        holder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: holder, position: Int) {
      holder.bind()
    }
}