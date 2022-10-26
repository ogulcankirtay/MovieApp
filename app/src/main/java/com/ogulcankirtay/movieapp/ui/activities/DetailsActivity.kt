package com.ogulcankirtay.movieapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ogulcankirtay.movieapp.R

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent=intent
        val title=intent.getSerializableExtra("title")
        val post=intent.getSerializableExtra("post")
        val overv=intent.getSerializableExtra("overview")
        val ima=findViewById<ImageView>(R.id.img)
        val Txttitle=findViewById<TextView>(R.id.titleDetails)
        val TxtOverview=findViewById<TextView>(R.id.txtOverviewDetails)
      println(post)
        Txttitle.text=title.toString()
         TxtOverview.text=overv.toString()
          Glide.with(ima).load("https://image.tmdb.org/t/p/w780/"+post).into(ima)



        println(title)
    }
}