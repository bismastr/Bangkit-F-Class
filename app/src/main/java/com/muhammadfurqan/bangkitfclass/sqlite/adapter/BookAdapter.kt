package com.muhammadfurqan.bangkitfclass.sqlite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammadfurqan.bangkitfclass.databinding.ItemBooksListBinding
import com.muhammadfurqan.bangkitfclass.databinding.ItemHeroBinding
import com.muhammadfurqan.bangkitfclass.list.viewholder.HeroViewHolder
import com.muhammadfurqan.bangkitfclass.sqlite.BookModel
import com.muhammadfurqan.bangkitfclass.sqlite.adapter.viewholder.BookViewHolder

class BookAdapter(private val listener: BookViewHolder.Listener): RecyclerView.Adapter<BookViewHolder>() {

    private val dataList = ArrayList<BookModel>()

    fun setData(data: ArrayList<BookModel>){
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            ItemBooksListBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener
        )


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}