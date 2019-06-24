package com.lms.mpasho_lms_news.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Article(@field:SerializedName("author")
              @field:Expose
              var author: String?, @field:SerializedName("title")
              @field:Expose
              var title: String?, @field:SerializedName("description")
              @field:Expose
              var description: String?, @field:SerializedName("url")
              @field:Expose
              var url: String?, @field:SerializedName("urlToImage")
              @field:Expose
              var urlToImage: String?) {

    @SerializedName("source")
    @Expose
    var source: Source? = null

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null
}