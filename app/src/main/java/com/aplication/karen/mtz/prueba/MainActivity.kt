package com.aplication.karen.mtz.prueba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.aplication.karen.mtz.prueba.databinding.ActivityMainBinding
import com.aplication.karen.mtz.prueba.ui.view.FavoriteFragment
import com.aplication.karen.mtz.prueba.ui.view.LoginFragment
import com.aplication.karen.mtz.prueba.ui.view.PrincipalFragment
import com.aplication.karen.mtz.prueba.ui.view.SplashFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        if (savedInstanceState == null) {
            splashFragment()
        }
        bottomNav()

        setContentView(binding.root)
    }
    private fun bottomNav(){
        val homefragment = PrincipalFragment()
        val favoritefragment = FavoriteFragment()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_inicio -> {
                    setCurrentFragment(homefragment)
                    true
                }
                R.id.nav_favorite-> {
                    setCurrentFragment(favoritefragment)
                    true
                }
                else -> false
            }
        }
    }
    private fun setCurrentFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView, fragment)
            commit()
        }
    }

    private fun splashFragment() {
        supportFragmentManager.commit {
            replace<SplashFragment>(R.id.containerView)
        }
    }

    fun loginFragment() {
        supportFragmentManager.commit {
            replace<LoginFragment>(R.id.containerView)
        }
    }

    fun principalFragment() {
        supportFragmentManager.commit {
            replace<PrincipalFragment>(R.id.containerView)
        }
        bottomNavigation()
    }

    private fun bottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigation.visibility = View.VISIBLE
        bottomNavigation.selectedItemId = R.id.principalFragment
    }

    fun favoritosFragment() {
        supportFragmentManager.commit {
            replace<FavoriteFragment>(R.id.containerView)
        }
        bottomNavigation()
    }

    override fun onBackPressed() {
        exitDialog()
    }

    private fun exitDialog() {
        val view = View.inflate(this, R.layout.dialog_view, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        view.findViewById<Button>(R.id.botonCancel).setOnClickListener {
            dialog.dismiss()
        }
        view.findViewById<Button>(R.id.botonAcept).setOnClickListener{
            dialog.dismiss()
            moveTaskToBack(true);
            finish()
        }
    }
}