package com.github.novotnyr.gros

import android.Manifest
import android.Manifest.permission.SEND_SMS
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission

class MainActivity : AppCompatActivity() {
    val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if(isGranted) {
            sendSms()
        } else {
            Toast.makeText(this, "Odosielanie SMS nefunguje, povolenie bolo zamietnute", Toast.LENGTH_SHORT).show()
        }
    }

    private lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appPreferences = AppPreferences(this)
    }

    fun onButtonClick(view: View) {
        //1. musim zistit, ci uz nahodou permission (povolenie) nemam udelene
        //2. ak nemam permission, tak onho musim poziadat - vybehne systemovy dialog
        //3. vysledok dialogu musim obsluzit v separatnej metode

        when(checkSelfPermission(this, SEND_SMS)) {
            PERMISSION_GRANTED -> sendSms()
            else -> requestSmsPermission()
        }
    }

    private fun requestSmsPermission() {
        if(shouldShowRequestPermissionRationale(SEND_SMS)) {
            Toast.makeText(this, "Na zaplatenie parkovania potrebuje appka povolenie pre SMS", Toast.LENGTH_LONG)
                    .show()
        }
        // Tuto musim poziadat o permission (povolenie), cize musim vyvolat systemovy dialog
        permissionLauncher.launch(SEND_SMS)
    }

    fun sendSms() {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(appPreferences.phoneNumber,
                null, "Prosim si parkovat", null, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // nafuknime layout pre menu
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.settingsMenuItem) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}