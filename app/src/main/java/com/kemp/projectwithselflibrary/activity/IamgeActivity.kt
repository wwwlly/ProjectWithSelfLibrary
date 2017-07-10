package com.kemp.projectwithselflibrary.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.kemp.kemplibrary.utils.CommonUtils

import com.kemp.projectwithselflibrary.R

class IamgeActivity : AppCompatActivity() {

    private val REQUEST_CODE_ALBUM: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iamge)

        init()
    }

    fun init() {
        val btn = findViewById(R.id.btn)

        btn.setOnClickListener {
            CommonUtils.intentToAlbum(this, REQUEST_CODE_ALBUM)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_ALBUM) {
            if (data == null) {
                return
            }

            val tv: TextView = findViewById(R.id.tv) as TextView

            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(selectedImage,
                    filePathColumn, null, null, null) ?: return
            cursor.moveToFirst()

            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close()

            if (!TextUtils.isEmpty(picturePath)) {
                tv.text = picturePath

                val iv: ImageView = findViewById(R.id.iv) as ImageView
                val option: BitmapFactory.Options = BitmapFactory.Options()
                option.inSampleSize = 2
                val bitmap: Bitmap = BitmapFactory.decodeFile(picturePath, option)
                iv.setImageBitmap(bitmap)
            } else {
                tv.text = "data is null"
            }
        }
    }
}
