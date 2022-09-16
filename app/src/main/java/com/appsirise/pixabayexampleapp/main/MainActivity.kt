package com.appsirise.pixabayexampleapp.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.appsirise.pixabayexampleapp.AppViewFactory
import com.appsirise.pixabayexampleapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView.Listener {

    @Inject
    lateinit var viewFactory: AppViewFactory
    private var mainView: MainView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Pixabay)
        mainView = viewFactory.newMainView()
        setContentView(mainView?.rootView)
    }

    override fun onStart() {
        super.onStart()
        mainView?.registerListener(this)
    }

    override fun onStop() {
        mainView?.unregisterListener(this)
        super.onStop()
    }

    override fun onDestroy() {
        mainView = null
        super.onDestroy()
    }

    override fun onClickSearch() {
        Toast.makeText(applicationContext, "Search clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onClickDashboard() {
        Toast.makeText(applicationContext, "Dashboard clicked", Toast.LENGTH_SHORT).show()
    }
}
