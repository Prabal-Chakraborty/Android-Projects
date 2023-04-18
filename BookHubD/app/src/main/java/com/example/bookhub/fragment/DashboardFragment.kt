package com.example.bookhub.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookhub.R
import com.example.bookhub.adapter.DashboardRecyclerAdapter
import com.example.bookhub.model.Book
import com.example.bookhub.util.ConnectionManager


class DashboardFragment : Fragment() {

    lateinit var recyclerDasboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var btnCheckIntener : Button

    lateinit var recyclerAdapter: DashboardRecyclerAdapter
    val bookInfoList = arrayListOf<Book>(
//        Book("P.S. I love You", "Cecelia Ahern", "Rs. 299", "4.5", R.drawable.ps_ily),
//        Book("The Great Gatsby", "F. Scott Fitzgerald", "Rs. 399", "4.1", R.drawable.great_gatsby),
//        Book("Anna Karenina", "Leo Tolstoy", "Rs. 199", "4.3", R.drawable.anna_kare),
//        Book("Madame Bovary", "Gustave Flaubert", "Rs. 500", "4.0", R.drawable.madame),
//        Book("War and Peace", "Leo Tolstoy", "Rs. 249", "4.8", R.drawable.war_and_peace),
//        Book("Lolita", "Vladimir Nabokov", "Rs. 349", "3.9", R.drawable.lolita),
//        Book("Middlemarch", "George Eliot", "Rs. 599", "4.2", R.drawable.middlemarch),
//        Book("The Adventures of Huckleberry Finn", "Mark Twain", "Rs. 699", "4.5", R.drawable.adventures_finn),
//        Book("Moby-Dick", "Herman Melville", "Rs. 499", "4.5", R.drawable.moby_dick),
//        Book("The Lord of the Rings", "J.R.R Tolkien", "Rs. 749", "5.0", R.drawable.lord_of_rings)
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerDasboard = view.findViewById(R.id.recyclerDashboard)
        btnCheckIntener = view.findViewById(R.id.btnCheckInternet)
        btnCheckIntener.setOnClickListener{
            if (ConnectionManager().checkConnectivity(activity as Context)){
                // Internet is available
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Success!!")
                dialog.setMessage("Internet Connection Found")
                dialog.setPositiveButton("Ok"){ text, Listner->
                }
                dialog.setNegativeButton("Cancel"){ text,Listner->
                }
                dialog.create()
                dialog.show()
            }else{
                // Internet is not available
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error!!")
                dialog.setMessage("Internet Connection Not Found")
                dialog.setPositiveButton("Ok"){ text, Listner->
                }
                dialog.setNegativeButton("Cancel"){ text,Listner->
                }
                dialog.create()
                dialog.show()
            }
        }
        layoutManager = LinearLayoutManager(activity)


        val queue = Volley.newRequestQueue(activity as Context)
        val url = "http://13.235.250.119/v1/book/fetch_books/"
        if (ConnectionManager().checkConnectivity(activity as Context)){
            val jsonObjectRequest = @SuppressLint("SuspiciousIndentation")
            object : JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {

                // Here We Will Write The The Response
                val success = it.getBoolean("success")
                if (success){
                    val data = it.getJSONArray("data")
                    for (i in 0 until data.length()){
                        val bookJsonObject = data.getJSONObject(i)
                        val bookObject = Book(
                            bookJsonObject.getString("book id"),
                            bookJsonObject.getString("name"),
                            bookJsonObject.getString("author"),
                            bookJsonObject.getString("rating"),
                            bookJsonObject.getString("price"),
                            bookJsonObject.getString("image"),
                        )
                        bookInfoList.add(bookObject)
                        recyclerAdapter = DashboardRecyclerAdapter(activity as Context, bookInfoList)
                        recyclerDasboard.adapter = recyclerAdapter
                        recyclerDasboard.layoutManager = layoutManager
                        recyclerDasboard.addItemDecoration(DividerItemDecoration(recyclerDasboard.context,(layoutManager as LinearLayoutManager).orientation))
                    }
                }else{
                    Toast.makeText(activity as Context, "some Error has Occurred !!",Toast.LENGTH_SHORT).show()
                }

            },Response.ErrorListener {

                //Here We Will Handle The Errors
            })
            {
                override fun getHeaders(): MutableMap<String, String> {
                    val Headers = HashMap<String, String>()
                    Headers["Content_type"] = "application/json"
                    Headers["token"] = "ba8f8adf75303b"
                    return Headers
                }

            }

            queue.add(jsonObjectRequest)
        }else{
            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error!!")
            dialog.setMessage("Internet Connection Not Found")
            dialog.setPositiveButton("Open Settings"){ text, Listner->
                val settingInterbet = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingInterbet)
                activity?.finish()
            }
            dialog.setNegativeButton("Exit"){ text,Listner->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()
        }

        return view
    }

}