package com.lms.mpasho_lms_news.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.lms.mpasho_lms_news.R
import com.lms.mpasho_lms_news.adapter.Adapter
import com.lms.mpasho_lms_news.api.ApiClient
import com.lms.mpasho_lms_news.api.ApiInterface
import com.lms.mpasho_lms_news.models.Article
import com.lms.mpasho_lms_news.models.News
import com.lms.mpasho_lms_news.util.Utils
import com.lms.mpasho_lms_news.view.details.NewsDetailActivity

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    private var articles: MutableList<Article>? = ArrayList()
    private var adapter: Adapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var errorLayout: RelativeLayout? = null
    private var errorImage: ImageView? = null
    private var errorTitle: TextView? = null
    private var errorMessage: TextView? = null
    private var btnRetry: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        implement swipe down to refresh
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout!!.setOnRefreshListener(this)
        swipeRefreshLayout!!.setColorSchemeResources(R.color.colorAccent)

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false

        onLoadingSwipeRefresh("")

        errorLayout = findViewById(R.id.errorLayout)
        errorImage = findViewById(R.id.errorImage)
        errorTitle = findViewById(R.id.errorTitle)
        errorMessage = findViewById(R.id.errorMessage)
        btnRetry = findViewById(R.id.btnRetry)

    }


    fun loadJson(keyword: String) {

        errorLayout!!.visibility = View.GONE
        swipeRefreshLayout!!.isRefreshing = true

        val apiInterface = ApiClient.apiClient?.create(ApiInterface::class.java!!)

        val country = Utils.country
        val language = Utils.language

        val call: Call<News>

        if (keyword.length > 0) {
            call = apiInterface?.getNewsSearch(keyword, language, "publishedAt", API_KEY)!!
        } else {
            call = apiInterface?.getNews(country, API_KEY)!!
        }

        call.enqueue(object : Callback<News> {

            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful && response.body()!!.article != null) {

                    if (!articles!!.isEmpty()) {
                        articles!!.clear()
                    }

                    articles = response.body()!!.article as MutableList<Article>?
                    adapter = Adapter(articles, this@MainActivity)
                    recyclerView.adapter = adapter
                    adapter!!.notifyDataSetChanged()

                    initListener()

                    swipeRefreshLayout!!.isRefreshing = false

                } else {

                    swipeRefreshLayout!!.isRefreshing = false
                    val errorCode: String
                    when (response.code()) {
                        404 -> errorCode = getString(R.string.error_404)
                        500 -> errorCode = getString(R.string.error_500)
                        else -> errorCode = getString(R.string.error_unknown)
                    }

                    showErrorMessage(
                            R.drawable.no_result,
                            getString(R.string.no_result),
                            getString(R.string.try_again) + errorCode
                    )
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                swipeRefreshLayout!!.isRefreshing = false
                showErrorMessage(
                        R.drawable.oops,
                        getString(R.string.oops),
                        getString(R.string.error_network_failure) + t.toString())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        val searchMenuItem = menu.findItem(R.id.action_search)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Search Latest News..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.length > 2) {
                    onLoadingSwipeRefresh(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        searchMenuItem.icon.setVisible(false, false)

        return true
    }

    override fun onRefresh() {
        loadJson("")
    }

    private fun onLoadingSwipeRefresh(keyword: String) {
        swipeRefreshLayout!!.post { loadJson(keyword) }
    }

    private fun initListener() {

//        adapter!!.setOnItemClickListener { view, position ->
//            val imageView = view.findViewById<ImageView>(R.id.img)
//            val intent = Intent(this@MainActivity, NewsDetailActivity::class.java)
//
//            val article = articles!![position as Int]
//            intent.putExtra("url", article.url)
//            intent.putExtra("title", article.title)
//            intent.putExtra("img", article.urlToImage)
//            intent.putExtra("date", article.publishedAt)
//            intent.putExtra("source", article.source!!.name)
//            intent.putExtra("author", article.author)
//
//            val pair: Pair<View, String>
//            pair = Pair.create(imageView as View, ViewCompat.getTransitionName(imageView))
//            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    this@MainActivity,
//                    pair
//            )
//
//            startActivity(intent, optionsCompat.toBundle())
//        }
    }

    private fun showErrorMessage(imageView: Int, title: String, message: String) {

        if (errorLayout!!.visibility == View.GONE) {
            errorLayout!!.visibility = View.VISIBLE
        }

        errorImage!!.setImageResource(imageView)
        errorTitle!!.text = title
        errorMessage!!.text = message

        btnRetry!!.setOnClickListener { onLoadingSwipeRefresh("") }

    }

    companion object {

        val API_KEY = "ccf1c18922cb4be88aeb1b6a08b13a5d"
    }
}
