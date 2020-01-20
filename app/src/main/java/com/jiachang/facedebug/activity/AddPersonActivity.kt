package com.jiachang.facedebug.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaRecorder
import android.media.MediaRecorder.VideoSource.CAMERA
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider.getUriForFile
import com.alibaba.fastjson.JSON
import com.hb.dialog.myDialog.ActionSheetDialog
import com.jiachang.facedebug.BuildConfig
import com.jiachang.facedebug.R
import com.jiachang.facedebug.bean.sendFileBean
import com.jiachang.facedebug.bean.setPersonBean
import com.jiachang.facedebug.http.ApiRetrofit
import com.jiachang.facedebug.utils.NameUtil
import com.jiachang.facedebug.utils.NameUtil.*
import kotlinx.android.synthetic.main.add_person.*
import retrofit2.Call
import retrofit2.Callback
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import retrofit2.Response as Response1


/**
 * @author Mickey.Ma
 * @date 2020-01-11
 * @description
 */
class AddPersonActivity : Activity() {
    private var filePath: String? = null
    private var filePathName: String? = null
    private var bm: Bitmap? = null
    private var fileUri: Uri? = null
    private var outfileUri: Uri? = null
    private var f: File? = null
    private var person: String? = null
    private var outputStream: FileOutputStream? = null

    private var TAKE_PHOTO_PERMISSION_REQUEST_CODE: Int = 0// 拍照的权限处理返回码
    private var WRITE_SDCARD_PERMISSION_REQUEST_CODE: Int = 1 // 读储存卡内容的权限处理返回码

    private var TAKE_PHOTO_REQUEST_CODE: Int = 2 // 拍照返回的 requestCode
    private var CHOICE_FROM_ALBUM_REQUEST_CODE: Int = 3 // 相册选取返回的 requestCode
    private var CROP_PHOTO_REQUEST_CODE: Int = 4 // 裁剪图片返回的 requestCode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_person)

        init()
    }

    private fun init() {
        NameUtil.name = edit_name.text.toString()
        NameUtil.id = id_edit.text.toString()
        val fileName = System.currentTimeMillis().toString() + ".jpg"
        filePathName =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath + File.separator
        filePath =
            filePathName + fileName
        f = File(filePath!!)

        photo.setOnClickListener {
            val dialog = ActionSheetDialog(this).builder().setTitle("请选择")
                .addSheetItem("相册", null) {
                    val intent = Intent()
                    intent.type = "image/*"// 开启Pictures画面Type设定为image
                    intent.action = Intent.ACTION_GET_CONTENT
                    startActivityForResult(
                        Intent.createChooser(intent, "图像选择"),
                        CHOICE_FROM_ALBUM_REQUEST_CODE
                    )
                }.addSheetItem("拍照", null) {

                    if (initImageFile()) {
                        startCamera()
                    }

                }
            dialog.show()
        }

        commit.setOnClickListener {
            Log.d("","name = "+NameUtil.name)
            Log.d("","id = "+NameUtil.id)
            person =
                "{\"id\":\"\",\"idcardNum\":\"\",\"name\":\"" + edit_name.text.toString() + "\",\"IDNumber\":\"" + id_edit.text.toString() +
                        "\",\"facePermission\":\"2\",\"idCardPermission\":\"\",\"faceAndCardPer mission\":\"\",\"IDPermission\":\"2\"}"
            println("f = "+f+", newpass = "+newpass+", person="+person)
            ApiRetrofit.initRetrofit(baseURL)
                .sendPhoto(f)
                .enqueue(object : Callback<sendFileBean> {
                    override fun onFailure(call: Call<sendFileBean>, t: Throwable) {
                        Toast.makeText(
                            this@AddPersonActivity,
                            "请求失败：" + t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<sendFileBean>,
                        response: retrofit2.Response<sendFileBean>
                    ) {
                        val fileBean = sendFileBean()
                        val bool = fileBean.isSuccess
                        Toast.makeText(this@AddPersonActivity, "返回结果为："+bool.toString(), Toast.LENGTH_LONG).show()
                        if (fileBean.isSuccess) {
                            Toast.makeText(
                                this@AddPersonActivity,
                                "请求成功",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                })
            ApiRetrofit.initRetrofit(baseURL)
                .setCreatePerson(newpass, person)
                .enqueue(object : Callback<setPersonBean?> {
                    @SuppressLint("ShowToast")
                    override fun onResponse(call: Call<setPersonBean?>,
                                            response: Response1<setPersonBean?>) {
                        val personBean = setPersonBean()
                        val bool = personBean.isSuccess
                        Toast.makeText(
                            this@AddPersonActivity,
                            "message = ${personBean.msg}",
                            Toast.LENGTH_LONG
                        )
                        if (bool) {
                            val data = personBean.data
                            Log.d("返回值", "data = $data")
                            Toast.makeText(
                                this@AddPersonActivity,
                                "data = $data",
                                Toast.LENGTH_LONG
                            )
                        }
                    }

                    override fun onFailure(call: Call<setPersonBean?>, t: Throwable) {
                        Toast.makeText(
                            this@AddPersonActivity,
                            "请求失败：" + t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }

    private fun startCamera() {
        val providerPath = File(externalCacheDir, "images")
        Log.d("文件路径：", "imagePath = $providerPath")
        if (!providerPath.exists()) {// 如果文件不存在，就创建文件
            try {
                providerPath.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            providerPath.delete()
        }
        /**
         * 因 Android 7.0 开始，不能使用 file:// 类型的 Uri 访问跨应用文件，否则报异常，
         * 因此我们这里需要使用内容提供器，FileProvider 是 ContentProvider 的一个子类，
         * 我们可以轻松的使用 FileProvider 来在不同程序之间分享数据(相对于 ContentProvider 来说)
         */
        fileUri = if (Build.VERSION.SDK_INT >= 24) {
            getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", providerPath)
        } else {
            Uri.fromFile(f) // Android 7.0 以前使用原来的方法来获取文件的 Uri
        }

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        Log.d("应用文件名：", "fileUri = $fileUri")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        startActivityForResult(
            Intent.createChooser(intent, "选择拍照"),
            TAKE_PHOTO_REQUEST_CODE
        )// 采用ForResult打开
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            // 通过返回码判断是哪个应用返回的数据
            when (requestCode) {
                // 拍照
                TAKE_PHOTO_REQUEST_CODE -> fileUri?.let { cropPhoto(it) }

                // 相册选择
                CHOICE_FROM_ALBUM_REQUEST_CODE -> data.data?.let { cropPhoto(it) }

                //截图显示
                CROP_PHOTO_REQUEST_CODE -> {
                    val file = File(outfileUri?.path)
                    if (file.exists()) {
                        bm = BitmapFactory.decodeFile(outfileUri?.path)
                        Log.d("Bitmap ", "bm = $bm")
                        photo.setImageBitmap(bm)
                        file.delete() // 选取完后删除照片
                    } else {
                        Toast.makeText(this, "找不到照片", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun cropPhoto(inputUri: Uri) {
        // 调用系统裁剪图片的 Action
        var cropPhotoIntent = Intent("com.android.camera.action.CROP")
        // 设置数据Uri 和类型
        cropPhotoIntent.setDataAndType(inputUri, "image/*")
        // 授权应用读取 Uri，这一步要有，不然裁剪程序会崩溃
        cropPhotoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        // 设置图片的最终输出目录
        outfileUri = Uri.parse("file:////sdcard/personId_faceId.jpg")
        Log.d("outfileUri", "outfileUri = $outfileUri")
        cropPhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, outfileUri)
        startActivityForResult(cropPhotoIntent, CROP_PHOTO_REQUEST_CODE)
    }

    /**
     * 裁剪图片
     */
    private fun cropPhot(input: Uri) {
        //处理小米手机不兼容的问题
        try {
            bm = BitmapFactory.decodeStream(contentResolver.openInputStream(input))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            outputStream = FileOutputStream(this.f!!)
            bm?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            photo.setImageBitmap(bm)
            f!!.delete() // 选取完后删除照片
            if (bm != null && bm!!.isRecycled) {
                photo.setImageBitmap(null) // 取消Bitmap渲染到imageView
                bm!!.recycle()      //Bitmap 回收
                bm = null
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 判断是否有SD卡
     * @return 有SD卡返回true， 否则false
     */
    private fun hasSDCard(): Boolean {
        // 获取外部存储的状态
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // 有SD卡
            return true
        }
        return false
    }

    /**
     * 初始化存储图片的文件
     * @return 初始化成功返回true，否则false
     */
    private fun initImageFile(): Boolean {
        // 有SD卡时才初始化文件
        if (hasSDCard()) {
            val imageFile = File(filePath!!)
            if (!imageFile.exists()) {// 如果文件不存在，就创建文件
                try {
                    imageFile.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return true
        }
        return false
    }
}
