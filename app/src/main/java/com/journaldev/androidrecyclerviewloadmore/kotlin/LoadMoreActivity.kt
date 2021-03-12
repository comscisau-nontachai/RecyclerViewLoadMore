package com.journaldev.androidrecyclerviewloadmore.kotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.journaldev.androidrecyclerviewloadmore.R

class LoadMoreActivity : AppCompatActivity() {

    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_more)

        val arrayItems = ArrayList<String?>()
        for (i in 0..10) {
            arrayItems.add("item : $i")
        }


        val mAdapter = RecyclerViewAdapterKotlin(arrayItems)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLoadMore)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    if (!isLoading) {

                        //load more data
                        arrayItems.add(null)
                        mAdapter.notifyItemChanged(arrayItems.size - 1)

                        Handler(Looper.getMainLooper()).postDelayed({

                            arrayItems.removeAt(arrayItems.size - 1)
                            var scrollPosition = arrayItems.size
                            mAdapter.notifyItemRemoved(scrollPosition)
                            val nextLimit = scrollPosition + 9

                            while (scrollPosition - 1 < nextLimit){
                                arrayItems.add("item : $scrollPosition")
                                scrollPosition++
                            }

                            isLoading = false
                        }, 2000)



                        mAdapter.notifyDataSetChanged()
                        isLoading = true
                    }
                }
            }
        })

    }

}