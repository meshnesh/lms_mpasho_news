package com.lms.mpasho_lms_news.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.lms.mpasho_lms_news.R
import com.lms.mpasho_lms_news.models.Article
import com.lms.mpasho_lms_news.util.Utils


class Adapter(private val articles: MutableList<Article>?, private val context: Context) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
//    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val model = articles?.get(position)

        val requestOptions = RequestOptions()
        requestOptions.placeholder(Utils.randomDrawbleColor)
        requestOptions.error(Utils.randomDrawbleColor)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions.centerCrop()

        Glide.with(context)
                .load(model!!.urlToImage)
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        viewHolder.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        viewHolder.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewHolder.imageView)

        if (model != null) {
            viewHolder.title.text = model.title
            viewHolder.desc.text = model.description
            viewHolder.source.text = model.source!!.name
            viewHolder.time.text = " \u2022 " + Utils.DateToTimeFormat(model.publishedAt)!!
            viewHolder.published_ad.text = Utils.DateFormat(model.publishedAt)
            viewHolder.author.text = model.author
        }
    }

    override fun getItemCount(): Int {
        return articles!!.size
    }

//    fun setOnItemClickListener(onItemClickListener: (Any, Any) -> Unit) {
//        this.onItemClickListener = onItemClickListener
//    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        internal var title: TextView
        internal var desc: TextView
        internal var author: TextView
        internal var published_ad: TextView
        internal var source: TextView
        internal var time: TextView
        internal var imageView: ImageView
        internal var progressBar: ProgressBar

        init {

            itemView.setOnClickListener(this)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.desc)
            author = itemView.findViewById(R.id.author)
            published_ad = itemView.findViewById(R.id.publishedAt)
            source = itemView.findViewById(R.id.source)
            time = itemView.findViewById(R.id.time)
            imageView = itemView.findViewById(R.id.img)
            progressBar = itemView.findViewById(R.id.progress_load_photo)
        }

//        override fun onClick(v: View) {
//            onItemClickListener.onItemClick(v, adapterPosition)
//        }
    }
}
