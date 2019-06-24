package com.lms.mpasho_lms_news.view.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lms.mpasho_lms_news.R
import com.lms.mpasho_lms_news.util.Utils

import java.util.Objects

class NewsDetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private var isHideToolbarView = false
    private var date_behavior: FrameLayout? = null
    private var titleAppbar: LinearLayout? = null
    private var mUrl: String? = null
    private var mTitle: String? = null
    private var mSource: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        Objects.requireNonNull<ActionBar>(supportActionBar).setTitle("")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbarLayout.title = ""

        val appBarLayout = findViewById<AppBarLayout>(R.id.appbar)
        appBarLayout.addOnOffsetChangedListener(this)
        date_behavior = findViewById(R.id.date_behavior)
        titleAppbar = findViewById(R.id.title_appbar)
        val imageView = findViewById<ImageView>(R.id.backdrop)
        val appbar_title = findViewById<TextView>(R.id.title_on_appbar)
        val appbar_subtitle = findViewById<TextView>(R.id.subtitle_on_appbar)
        val date = findViewById<TextView>(R.id.date)
        val time = findViewById<TextView>(R.id.time)
        val title = findViewById<TextView>(R.id.title)

        val intent = intent
        mUrl = intent.getStringExtra("url")
        val mImg = intent.getStringExtra("img")
        mTitle = intent.getStringExtra("title")
        val mDate = intent.getStringExtra("date")
        mSource = intent.getStringExtra("source")
        val mAuthor = intent.getStringExtra("author")

        val requestOptions = RequestOptions()
        requestOptions.error(Utils.randomDrawbleColor)

        Glide.with(this)
                .load(mImg)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)

        appbar_title.text = mSource
        appbar_subtitle.text = mUrl
        date.text = Utils.DateFormat(mDate)
        title.text = mTitle

        val author: String
        if (mAuthor != null) {
            author = " \u2022 $mAuthor"
        } else {
            author = ""
        }

        time.text = mSource + author + " \u2022 " + Utils.DateToTimeFormat(mDate)

        initWebView(mUrl)

    }

    private fun initWebView(url: String?) {
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        val maxScroll = appBarLayout.totalScrollRange
        val percentage = Math.abs(i).toFloat() / maxScroll.toFloat()

        if (percentage == 1f && isHideToolbarView) {
            date_behavior!!.visibility = View.GONE
            titleAppbar!!.visibility = View.VISIBLE
            isHideToolbarView = !isHideToolbarView

        } else if (percentage < 1f && !isHideToolbarView) {
            date_behavior!!.visibility = View.VISIBLE
            titleAppbar!!.visibility = View.GONE
            isHideToolbarView = !isHideToolbarView
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.view_web) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(mUrl)
            startActivity(i)
            return true
        } else if (id == R.id.share) {
            try {

                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plan"
                i.putExtra(Intent.EXTRA_SUBJECT, mSource)
                val body = mTitle + "\n" + mUrl + "\n" + getString(R.string.sahre_from) + "\n"
                i.putExtra(Intent.EXTRA_TEXT, body)
                startActivity(Intent.createChooser(i, getString(R.string.share_with)))

            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.cannot_share), Toast.LENGTH_SHORT).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
