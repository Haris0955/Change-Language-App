package com.example.changelanguageapp.ui.login


import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity

import com.example.changelanguageapp.databinding.ActivityLoginBinding
import java.util.*


class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadLocale()
        binding.btnChangeLang?.setOnClickListener {
            changeLanguage()
        }

    }

    private fun loadLocale() {
        // Get a reference to the SharedPreferences
        val preferences = getSharedPreferences("Settings", MODE_PRIVATE)

// Retrieve the language preference, and provide a default value if not found
        val language = preferences.getString("app_lang", "")

// Set the app's locale based on the retrieved language
        if (language != null) {
            setLocale(language)
        }
    }

    private fun changeLanguage() {

        val languages = arrayOf("English", "Urdu", "French", "Hindi")
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(languages, -1) { dialog, which ->
            if (which == 0) {
                setLocale("en")
                recreate()
            } else if (which == 1) {
                setLocale("ur")
                recreate()
            } else if (which == 2) {
                setLocale("fr")
                recreate()
            } else if (which == 3) {
                setLocale("hi")
                recreate()
            }

        }
        mBuilder.create()
        mBuilder.show()
    }

    private fun setLocale(language: String) {
        val locale = Locale(language) // Create a Locale with just the language code
        Locale.setDefault(locale)

        val configuration = Configuration()
        configuration.locale = locale // Set the desired locale

        val resources = baseContext.resources
        val displayMetrics = resources.displayMetrics

        resources.updateConfiguration(configuration, displayMetrics)

        // Get a reference to the SharedPreferences
        val sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)

// Get an editor to make changes
        val editor = sharedPreferences.edit()

// Now, you can use the editor to make changes to the preferences
        editor.putString("app_lang", language) // Example: Store a string

// Commit the changes to the preferences file
        editor.apply() // or editor.commit() if you want to force immediate save

    }
}


