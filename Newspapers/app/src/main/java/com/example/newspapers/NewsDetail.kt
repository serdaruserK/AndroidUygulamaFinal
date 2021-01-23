package com.example.newspapers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.activity_news.*

class NewsDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        var bundle = intent.extras


        textTitle.text = bundle?.getString("title")
        textDes.text = bundle?.getString("description")
        txtUrl.text = bundle?.getString("author")
        Picasso.get().load(bundle?.getString("image")).into(findViewById<ImageView>(R.id.imageView));

    }
}