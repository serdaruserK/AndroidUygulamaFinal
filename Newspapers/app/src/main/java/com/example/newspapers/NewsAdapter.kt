package com.example.newspapers

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_news.view.*

class NewsAdapter : BaseAdapter {
    var newsList = ArrayList<News>()
    var context: Context? = null

    constructor(context: Context, newsList: ArrayList<News>) : super() {
        this.context = context
        this.newsList = newsList
    }
    override fun getCount(): Int {
        return newsList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val news = this.newsList[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var newsView = inflator.inflate(R.layout.activity_news, null)



        Picasso.get().load(news.urlToImage).into(newsView.imgNews);
        newsView.newsName.text = news.title!!

        newsView.setOnClickListener{
            var intent = Intent(context, NewsDetail::class.java)
            intent.putExtra("title",news.title!!)
            intent.putExtra("description",news.description!!)
            intent.putExtra("image", news.urlToImage)
            intent.putExtra("author",news.author)
            context!!.startActivity(intent)

        }

        return newsView

    }

    override fun getItem(position: Int): Any {
        return newsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}