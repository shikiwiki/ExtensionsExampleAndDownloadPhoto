package com.example.extensionsexample

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import java.util.concurrent.Executors

//"https://kb.rspca.org.au/wp-content/uploads/2023/06/bearded-dragon-on-sand.jpg"

class MainActivity : AppCompatActivity() {

    private val inputUrl: EditText by lazy { findViewById(R.id.input_text) }
    private val mainButton: Button by lazy { findViewById(R.id.main_button) }
    private val picture: ImageView by lazy { findViewById(R.id.picture) }
    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainButton.setOnClickListener {
            if (inputUrl.text.toString().trim() == "") {
                Toast.makeText(this, R.string.input_link, Toast.LENGTH_LONG).show()
            } else {
                val url: String = inputUrl.text.toString()
                executor.execute {
                    try {
                        url.downloadPictureByUrl()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun String.downloadPictureByUrl() {
        if ((this@downloadPictureByUrl.startsWith("http://") || this.startsWith("https://"))
            && this@downloadPictureByUrl.endsWith(".jpg")
        ) {
            val bitmap = Glide.with(this@MainActivity)
                .asBitmap()
                .load(this@downloadPictureByUrl)
                .centerCrop()
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .get()
            picture.setImageBitmap(bitmap)
        } else {
            val oopsLogo =
                "https://cdn.dribbble.com/users/2063970/screenshots/5285981/oops-01_4x.jpg"
            Glide.with(this@MainActivity)
                .load(oopsLogo).into(picture)
        }
    }
}

