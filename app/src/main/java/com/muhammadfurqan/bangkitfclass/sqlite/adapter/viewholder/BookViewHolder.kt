package com.muhammadfurqan.bangkitfclass.sqlite.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhammadfurqan.bangkitfclass.BaseModel
import com.muhammadfurqan.bangkitfclass.databinding.ItemBooksListBinding
import com.muhammadfurqan.bangkitfclass.list.model.NationalHero
import com.muhammadfurqan.bangkitfclass.sqlite.BookModel

class BookViewHolder(private val binding: ItemBooksListBinding, private val listener: Listener) : RecyclerView.ViewHolder(binding.root){

    fun bind(element: BookModel) {
        binding.tvBookName.text = element.name
        binding.tvId.text = element.id.toString()

        itemView.setOnClickListener {
            listener.onClick(element)
        }
    }

    interface Listener {
        fun onClick(element: BookModel)
    }
}