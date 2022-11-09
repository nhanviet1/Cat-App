package com.example.catapp.view.homescreen

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.catapp.R
import com.example.catapp.data.model.responsemodel.favourite.FavouriteItem
import com.example.catapp.data.model.responsemodel.favourite.FavouriteResponse
import com.example.catapp.databinding.ActivityBigImageBinding
import com.example.catapp.databinding.BottomSheetLayoutBinding
import com.example.catapp.utils.IMAGE_URL
import com.example.catapp.utils.USER_API
import com.example.catapp.utils.downloadImg
import com.example.catapp.utils.REQUEST_CODE
import com.example.catapp.utils.IMAGE_ID
import com.example.catapp.utils.FAV_SCREEN_TAG
import com.example.catapp.utils.NONE
import com.example.catapp.utils.loadBigImage
import com.example.catapp.utils.RELOAD_FAV
import com.example.catapp.utils.SCREEN_ADDRESS
import com.example.catapp.utils.shortToast
import com.example.catapp.utils.IMAGE_SCREEN_TAG
import com.example.catapp.utils.isGone
import com.example.catapp.utils.PresenterProvider
import com.example.catapp.utils.base.BaseActivity
import com.example.catapp.view.homescreen.favpresenter.singlefavpresenter.SingleFavInterface
import java.lang.Exception

class BigImageActivity : BaseActivity<ActivityBigImageBinding>(ActivityBigImageBinding::inflate),
    SingleFavInterface.View {

    private var dialog: Dialog? = null
    private val urlList = mutableListOf<String>()
    private var userAPI: String? = null
    private var imgID: String? = null
    private var imgURL: String? = null
    private var fromScreen: String? = null
    private var singleFavPresenter: SingleFavInterface.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        imgURL = intent.getStringExtra(IMAGE_URL)
        userAPI = intent.getStringExtra(USER_API)
        imgID = intent.getStringExtra(IMAGE_ID)
        fromScreen = intent.getStringExtra(SCREEN_ADDRESS)
        singleFavPresenter = PresenterProvider.singleFavPresenter(this@BigImageActivity)
        binding.root.setOnLongClickListener {
            showDialog()
            true
        }

        if (imgURL != null) {
            loadBigImage(imgURL!!, binding.imgBigImg)
        }

        if (userAPI != null) {
            singleFavPresenter?.getRemoteFav(userAPI!!)
        }
    }

    private fun showDialog() {
        dialog = Dialog(this@BigImageActivity)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogBinding = BottomSheetLayoutBinding.inflate(layoutInflater)
        dialog?.setContentView(dialogBinding.root)
        dialog?.show()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog?.window?.setGravity(Gravity.BOTTOM)
        dialogButtonSetup(dialogBinding)
    }

    private fun dialogButtonSetup(dialogBinding: BottomSheetLayoutBinding) {
        when (fromScreen) {
            IMAGE_SCREEN_TAG -> dialogBinding.textDelete.isGone()
            FAV_SCREEN_TAG -> {
                dialogBinding.textAddToFav.isGone()
                dialog?.findViewById<View>(R.id.view_divider_1)?.isGone()
            }
        }

        dialogBinding.textDownload.setOnClickListener {
            storagePermission()
            dialog?.dismiss()
        }

        dialogBinding.textDelete.setOnClickListener {
            if (userAPI != null && imgID != null) {
                singleFavPresenter?.deleteRemoteFav(userAPI!!, imgID!!)
                dialog?.dismiss()
            }
        }

        dialogBinding.textAddToFav.setOnClickListener {
            if (urlList.contains(imgURL) && imgURL != null) {
                this@BigImageActivity.shortToast(getString(R.string.text_already_added_to_favourite))
            } else {
                if (userAPI != null && imgID != null) {
                    singleFavPresenter?.postRemoteFav(userAPI!!, imgID!!)
                    dialog?.dismiss()
                }
            }
        }
    }

    override fun onGetFavSuccess(favList: MutableList<FavouriteItem>) {
        urlList.clear()
        favList.forEach {
            urlList.add(it.image.url)
        }
    }

    override fun onPostFavSuccess(favResponse: FavouriteResponse) {
        this@BigImageActivity.shortToast(favResponse.message)
        Intent(RELOAD_FAV).run {
            sendBroadcast(this)
        }
    }

    override fun onDeleteFavSuccess(favResponse: FavouriteResponse) {
        this@BigImageActivity.shortToast(getString(R.string.text_deleted_successfully))
        Intent(RELOAD_FAV).run {
            sendBroadcast(this)
        }
    }

    override fun onError(exception: Exception?) {
        this@BigImageActivity.shortToast(exception.toString())
    }

    private fun storagePermission() {
        val permission = mutableListOf<String>()
        permission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val isGranted = ContextCompat.checkSelfPermission(
            this@BigImageActivity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        if (!isGranted) {
            ActivityCompat.requestPermissions(
                this@BigImageActivity,
                permission.toTypedArray(), REQUEST_CODE
            )
        } else {
            downloadImg(imgURL!!)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && grantResults[NONE] == PackageManager.PERMISSION_GRANTED) {
            if (imgURL != null) {
                downloadImg(imgURL!!)
            }
        }
    }
}
