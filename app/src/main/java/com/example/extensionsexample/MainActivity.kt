package com.example.extensionsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    
    private val picture: ImageView by lazy { findViewById(R.id.picture) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        "https://kb.rspca.org.au/wp-content/uploads/2023/06/bearded-dragon-on-sand.jpg"
            .downloadPictureByUrl()
    }

    fun String.downloadPictureByUrl() {
        Glide.with(this@MainActivity).load(this@downloadPictureByUrl).into(picture)

    }
}

