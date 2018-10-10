package com.example.juliobanda.udemy.ListView

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.juliobanda.udemy.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val phoneCode = 200

    private fun olderVersion() {
        val phoneNumber = etPhone.text.toString()

        val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
        if (checkPermission(android.Manifest.permission.CALL_PHONE)) {
            startActivity(intentCall)
        } else {
            Toast.makeText(this, "You decline the access", Toast.LENGTH_SHORT).show()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun newerVersion() {
        requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), phoneCode)
    }

    private val onClick = View.OnClickListener {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val phoneNumber = etPhone.text.toString()

            if (checkPermission(Manifest.permission.CALL_PHONE)){
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
                if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_DENIED) {
                    startActivity(intent)
                }
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                    requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), phoneCode)
                } else {
                    Toast.makeText(this@MainActivity, "Please enable permissions", Toast.LENGTH_SHORT).show()
                    val settings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    settings.addCategory(Intent.CATEGORY_DEFAULT)
                    settings.data = Uri.parse("package: $packageName")
                    settings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    settings.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    settings.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                    startActivity(settings)
                }
            }

        } else {
            olderVersion()
        }

    }

    private val onWebClickListener = View.OnClickListener {
        val url = etWeb.text.toString()
        if (url != null && !url.isEmpty()){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://$url"))
            startActivity(intent)
        }


    }

    private val onCameraClickListener = View.OnClickListener {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        ibPhone.setOnClickListener(onClick)
        ibWeb.setOnClickListener(onWebClickListener)
        ibCamera.setOnClickListener(onCameraClickListener)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {


        when (requestCode) {
            phoneCode -> {
                permissions.forEach { Log.i("Permissions", it) }

                if (permissions[0] == android.Manifest.permission.CALL_PHONE) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        val phoneNumber = etPhone.text.toString()
                        val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))


                        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_DENIED) {
                            startActivity(intentCall)
                        }

                    } else {
                        Toast.makeText(this, "You decline the access", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }

    private fun checkPermission(permission: String): Boolean {
        val result = this.checkCallingOrSelfPermission(permission)
        return result == PackageManager.PERMISSION_GRANTED
    }

}
