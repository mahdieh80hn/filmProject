package com.example.filmproject.adapters

import android.content.Context
import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmproject.DataSource
import com.example.filmproject.R
import com.example.filmproject.models.Film

class FilmRecyclerAdapter(val context: Context, var films: List<Film>): RecyclerView.Adapter<FilmRecyclerAdapter.Holder>(), Filterable {

    private var filmsFiltered:List<Film> = arrayListOf()

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val filmTitle = itemView.findViewById<TextView>(R.id.filmTitle)
        val filmGenre = itemView.findViewById<TextView>(R.id.filmGenre)
        val filmDescription = itemView.findViewById<TextView>(R.id.filmDescription)
        val filmPoster = itemView.findViewById<ImageView>(R.id.filmPoster)
        val createdBy = itemView.findViewById<TextView>(R.id.createdBy)

        fun bindFilm(film: Film, context: Context){
            val resourceId = context.resources.getIdentifier(film.filmposter,"drawable", context.packageName)

            filmTitle.text = film.filmTitle
            filmGenre.text = film.filmGenre
            createdBy.text = film.createdBy
            filmDescription.text = film.filmDescription
            filmPoster.setImageResource(resourceId)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.film_item, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindFilm(films[position], context)
    }

    override fun getItemCount(): Int {
        return films.count()
    }

    public fun setData(films: List<Film>){
        this.films = films
        this.filmsFiltered = films
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        var filter = object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filterResults = FilterResults()
                if (p0 == null || p0.isEmpty()){
                    filterResults.values = filmsFiltered
                    filterResults.count = filmsFiltered.size
                }else{
                    var searchChar = p0.toString().toLowerCase()
                    var filteredResult = ArrayList<Film>()

                    for (film in filmsFiltered){
                        if (film.filmTitle.toLowerCase().contains(searchChar)){
                            filteredResult.add(film)
                        }
                    }
                    filterResults.values = filteredResult
                    filterResults.count = filteredResult.size
                }
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                films = p1!!.values as List<Film>
                notifyDataSetChanged()
            }

        }
        return filter
    }
}