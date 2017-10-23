package com.kemp.projectwithselflibrary.activity

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.widget.GridView
import android.widget.Toast
import com.kemp.kemplibrary.utils.BitmapUtils

import com.kemp.projectwithselflibrary.R
import com.kemp.projectwithselflibrary.adapter.GridPlusAdapter
import com.kemp.projectwithselflibrary.utils.Logger
import java.io.File
import java.io.IOException

class AddImgActivity : AppCompatActivity() {

    private val REQUEST_CODE_ALBUM = 1
    private val ADD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "add_images" + File.separator

    private val mData: MutableList<String> = ArrayList()
    private val adapter = GridPlusAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_img)

        init()
    }

    fun init() {
        val gridView: GridView = findViewById(R.id.grid_view) as GridView

        adapter.setData(mData)
        adapter.setMaxLength(5)
        gridView.adapter = adapter
        gridView.setOnItemClickListener { parent, view, position, id ->
            if (adapter.isPlusPos(position)) {
                takePicture()
            } else {
                deletePickture(position)
            }
        }
    }

    private fun takePicture() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_ALBUM)
    }

    private fun deletePickture(position: Int): Unit {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("删除").setMessage("确定要删除该图片吗？").setPositiveButton("确定", DialogInterface.OnClickListener { dialog, which ->
            removeData(position)
        }).setNegativeButton("取消", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        }).show()
    }

    fun addData(bitmapPath: String) {
        mData.add(mData.size, bitmapPath)
        adapter.notifyDataSetChanged()
    }

    fun removeData(position: Int) {
        if (position < 0 || position >= mData.size) {
            return
        }
        mData.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_ALBUM -> {
                    if (data != null) {
                        val selectedImage: Uri = data.data
                        CompressTask().execute(selectedImage)
                    }
                }
            }
        }
    }

    private inner class CompressTask : AsyncTask<Uri, Void, String>() {

        override fun doInBackground(vararg params: Uri): String? {
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

            val cursor = contentResolver.query(params[0],
                    filePathColumn, null, null, null) ?: return null
            cursor.moveToFirst()

            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close()

            val origin = File(picturePath)
            Logger.d("file name:" + origin.getName())
            val outFile = File(ADD_PATH)
            if (!outFile.exists()) {
                val success = outFile.mkdirs()
                if (!success) {
                    Logger.d("图片文件错误")
                    return null
                }
            }
            val bitmap = BitmapUtils.createBitmap(picturePath)
            if (bitmap == null) {
                Logger.d("picturePath:" + picturePath)
                return null
            }
            try {
                BitmapUtils.compressAndGenImage(bitmap, ADD_PATH + origin.getName(), 400)
                return ADD_PATH + origin.getName()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(s: String?) {
            if (s != null && !TextUtils.isEmpty(s)) {
                addData(s)
            } else {
                Toast.makeText(applicationContext, "图片获取失败", Toast.LENGTH_LONG).show()
            }
        }

        override fun onPreExecute() {
        }
    }
}
