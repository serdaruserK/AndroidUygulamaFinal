package com.example.newspapers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.GridView
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private val client = OkHttpClient()
    var adapter : NewsAdapter? = null
    private lateinit var newsList: List<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var gridView = findViewById(R.id.gvNews) as GridView
        //newsApiUrl
        newsApiCallWithUrl("http://newsapi.org/v2/top-headlines?country=tr&apiKey=9dc708da3dec4b1b8ef623eba766e145")

        Handler().postDelayed({
            adapter = NewsAdapter(this, newsList as ArrayList<News>)
            gvNews.adapter = adapter
        },2000)

    }
    fun newsApiCallWithUrl(url: String) {
        val req = Request.Builder().url(url).build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Hata  : ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseStr = response.body?.string()
                val data = JSONObject(responseStr)
                newsList =
                    Gson().fromJson(data.getString("articles"), Array<News>::class.java).toList()
            }
        })
    }
}