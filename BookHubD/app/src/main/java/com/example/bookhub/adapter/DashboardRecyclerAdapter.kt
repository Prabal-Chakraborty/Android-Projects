package com.example.bookhub.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhub.R
import com.example.bookhub.model.Book
import com.squareup.picasso.Picasso

class DashboardRecyclerAdapter(val context: Context, val itemList:ArrayList<Book>): RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() { //String --> Book

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtBookName : TextView = view.findViewById(R.id.txtBookName)
        val txtBookAuthor : TextView = view.findViewById(R.id.txtBookAuthor)
        val txtBookPrice : TextView = view.findViewById(R.id.txtBookPrice)
        val txtBookRating : TextView = view.findViewById(R.id.txtBookRating)
        val imaBookImage : ImageView = view.findViewById(R.id.imgBookImage)
        val llContent : LinearLayout = view.findViewById(R.id.llContent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = itemList[position]

        holder.txtBookName.text = book.BookName
        holder.txtBookAuthor.text = book.BookAuthor
        holder.txtBookPrice.text = book.BookPrice
        holder.txtBookRating.text = book.BookRating
       // holder.imaBookImage.setImageResource(book.BookImg)
        Picasso.get().load(book.BookImg).into(holder.imaBookImage)

        holder.llContent.setOnClickListener{
            Toast.makeText(context,"Clicked on ${holder.txtBookName.text}",Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}