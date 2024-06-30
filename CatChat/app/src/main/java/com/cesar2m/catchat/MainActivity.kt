package com.cesar2m.catchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView = findViewById<NavigationView>(R.id.nav_view) //Se agregaron para el Drawer
        NavigationUI.setupWithNavController(navView, navController) //Se agregaron para el Drawer

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)//Se agregaron para el Drawer
        val builder = AppBarConfiguration.Builder(navController.graph)
        builder.setOpenableLayout(drawer)//Se agregaron para el Drawer
        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)

        //bottom toolbar
        //val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav) //Se ELIMINA para el Drawer
       // bottomNavView.setupWithNavController(navController)//Se ELIMINA para el Drawer

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}