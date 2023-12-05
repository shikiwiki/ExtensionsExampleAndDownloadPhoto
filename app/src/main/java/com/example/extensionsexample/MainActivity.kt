package com.example.extensionsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private val mainText: ImageView by lazy { findViewById(R.id.main_text) }
    private lateinit var inputUrl: EditText
    private val mainButton: Button by lazy { findViewById(R.id.main_button) }
    private val picture: ImageView by lazy { findViewById(R.id.picture) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url: String = "https://kb.rspca.org.au/wp-content/uploads/2023/06/bearded-dragon-on-sand.jpg"
        url.downloadPictureByUrl()
    }

    fun String.downloadPictureByUrl() {
        if ((this@downloadPictureByUrl.startsWith("http://") || this.startsWith("https://"))
            && this@downloadPictureByUrl.endsWith(".jpg")) {
            Glide.with(this@MainActivity)
                .load(this@downloadPictureByUrl).into(picture)
        } else {
            val oopsLogo = "https://cdn.dribbble.com/users/2063970/screenshots/5285981/oops-01_4x.jpg"
            Glide.with(this@MainActivity)
                .load(oopsLogo).into(picture)
        }
    }
}

