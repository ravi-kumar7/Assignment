package com.android.assignment.data.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.assignment.R
import com.bumptech.glide.Glide

@Entity
data class Fact(
        @PrimaryKey val title: String = "",
        val description: String?,
        val imageHref:String?,
        var category:String?=null
){

    companion object{

        @BindingAdapter("android:src")
        @JvmStatic
        fun loadImage(view: ImageView, res:String?)
        {
            Glide.with(view.context).load(res).fitCenter().error(R.drawable.ic_image).fallback(R.drawable.ic_image).into(view)
        }
    }
}
