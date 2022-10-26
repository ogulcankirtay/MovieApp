package com.ogulcankirtay.movieapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ogulcankirtay.movieapp.R
import com.ogulcankirtay.movieapp.di.dao.GenreData
import com.ogulcankirtay.movieapp.models.Result
import com.ogulcankirtay.movieapp.ui.activities.DetailsActivity


class RecentMovieAdapter(private val isFirstScreen: Boolean=true): RecyclerView.Adapter<RecentMovieAdapter.MyCustomHolder>() {
    var liveData: List<Result>?=null
    var genreList: List<GenreData>?=null

    fun setLists(liveData: List<Result>,genreList: List<GenreData>){
        this.liveData=liveData
        this.genreList=genreList
        notifyDataSetChanged()
    }

    class MyCustomHolder(val view: View): RecyclerView.ViewHolder(view){

        val TxtTitle=view.findViewById<TextView>(R.id.txtTitle)
        val TxtGenre=view.findViewById<TextView>(R.id.txtGenre)
        val posterView=view.findViewById<ImageView>(R.id.posterView)
        val txtDate=view.findViewById<TextView>(R.id.txtReleaseDate)
        val txtVote=view.findViewById<TextView>(R.id.txtVoteAverage)

        fun bind(data: Result, genreList: List<GenreData>){
            TxtTitle.text=data.title
            var genres=""
            for (id in data.genre_ids){
                val result = genreList.find { x -> x.genre_id == id}
                if(result != null){
                    genres += result!!.en_name + ", "
                }
            }
            genres = genres.substring(0,genres.length-2)
            TxtGenre.text = genres

            txtDate.text=data.release_date
            txtVote.text=""+data.vote_average+" / 10.0"
            Glide.with(posterView).load("https://image.tmdb.org/t/p/w342/"+data.poster_path).into(posterView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMovieAdapter.MyCustomHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.recent_movie_item,parent,false)
        return MyCustomHolder(view)
    }

    override fun onBindViewHolder(holder: RecentMovieAdapter.MyCustomHolder, position: Int) {
        holder.bind(liveData!!.get(position),genreList!!)
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("title", liveData!!.get(position).title)
            intent.putExtra("post", liveData!!.get(position).poster_path)
            intent.putExtra("overview", liveData!!.get(position).overview)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        if(liveData==null){
            return 0
        }
        else if(isFirstScreen){
            return 10
        }
        else
        {
            return liveData!!.size
        }
    }
}