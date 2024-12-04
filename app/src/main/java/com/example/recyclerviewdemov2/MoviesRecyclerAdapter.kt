package com.example.recyclerviewdemov2

import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MoviesRecyclerAdapter(context: Context, dbHelper: DBHelper) : RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder>() {

    private val db: SQLiteDatabase
    private val cursor: Cursor
    private val resources: Resources
    private val context: Context


    private val titles = arrayOf("The Shawshank Redemption",
        "The Godfather", "The Godfather: Part II ", "The Dark Knight",
        "12 Angry Men", "Schindler's List", "The Lord of the Rings: The Return of the King",
        "Pulp Fiction", "The Good, the Bad and the Ugly", "Fight Club" )

    private val rating = arrayOf("9.2", "9.2",
        "9.0", "9.0",
        "8.9", "8.9",
        "8.9", "8.9", "8.8", "8.8")

    private val images = intArrayOf(R.drawable.movie01,
        R.drawable.movie02, R.drawable.movie03,
        R.drawable.movie04, R.drawable.movie05,
        R.drawable.movie06, R.drawable.movie07,
        R.drawable.movie08, R.drawable.movie09, R.drawable.movie10)



    init {
        db = dbHelper.readableDatabase
        //cursor = dbHelper.getAll()
        cursor = dbHelper.getFromTo(8.0, 8.9)
        //cursor = dbHelper.search("The")
        this.context = context
        resources = context.resources
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemRating: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemRating = itemView.findViewById(R.id.item_rating)

            itemView.setOnClickListener {v: View ->
                var position: Int = this.adapterPosition
                Toast.makeText(itemView.context, "You've picked: " + itemTitle.text, Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.movie_card, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        //return titles.size
        return cursor.count
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
//        viewHolder.itemTitle.text = titles[i]
//        viewHolder.itemRating.text = rating[i]
//        viewHolder.itemImage.setImageResource(images[i])
        cursor.moveToPosition(i)
        viewHolder.itemTitle.text = cursor.getString(cursor.getColumnIndex("title"))
        viewHolder.itemRating.text = cursor.getDouble(cursor.getColumnIndex("rating")).toString()
        val fileName = cursor.getString(cursor.getColumnIndex("poster_url"))
        val resID = resources.getIdentifier(fileName,"drawable", context.packageName)
        viewHolder.itemImage.setImageResource(resID)
    }


}