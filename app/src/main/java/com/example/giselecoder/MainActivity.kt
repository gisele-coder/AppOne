package com.example.giselecoder

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.giselecoder.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var isDarkTheme = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val posts = PostUtils.getPostsFromStrings(this)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Fab button", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            for (post in posts) {
                Log.d("Post", "Título: ${post.title}")
                Log.d("Post", "Descrição: ${post.description}")
                Log.d("Post", "URL do vídeo: ${post.videoUrl}")
            }
            Firebase.analytics.logEvent("log_fab_click", null)

        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Método chamado quando o botão é clicado
    fun changeTheme(view: View) {
        if (isDarkTheme) {
            // Alterne para o tema claro
            setTheme(R.style.AppTheme_Light)
        } else {
            // Alterne para o tema escuro
            setTheme(R.style.AppTheme_Dark)
        }
        // Recrie a atividade para aplicar o novo tema
        recreate()
        // Inverta o estado do tema
        isDarkTheme = !isDarkTheme
    }
}