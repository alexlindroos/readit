package com.example.alexlindroos.readit.ui.main

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.example.alexlindroos.readit.R
import com.example.alexlindroos.readit.R.id.*
import com.example.alexlindroos.readit.network.session.UserSession
import com.example.alexlindroos.readit.ui.home.HomeFragment
import com.example.alexlindroos.readit.ui.list.SubListFragment
import com.example.alexlindroos.readit.ui.login.LoginActivity
import com.example.alexlindroos.readit.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


/**
 * Created by Alex Lindroos on 13/02/2018.
 */

class MainActivity: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val QUERY = "query"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)
        displayHomeFragmentIfNeeded()
    }

    override fun onResume() {
        super.onResume()

        if(!UserSession.isOpen) {
            showLoginScreen()
            return
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {

                } else {
                    showSearch(query!!)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when (item.itemId) {
            action_home -> HomeFragment()
            action_list -> SubListFragment()
            action_profile -> ProfileFragment()
            else -> throw IllegalArgumentException("Unknown menu item type")
        }
        displayFragment(fragment)
        return true
    }

    override fun onBackPressed() {

    }

    private fun displayHomeFragmentIfNeeded() {
        if (UserSession.isOpen && bottom_navigation_view.selectedItemId == R.id.action_home) {
            displayFragment(HomeFragment())
        }
    }

    private fun showLoginScreen() {
        startActivity<LoginActivity>()
    }

    private fun showSearch(query: String) {
        val manager: FragmentManager = this.fragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        val fragment = HomeFragment()
        val args = Bundle()
        args.putString(QUERY,query)
        fragment.arguments = args
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
        
        bottom_navigation_view.setOnNavigationItemSelectedListener(null)
        bottom_navigation_view.selectedItemId = R.id.action_home
        bottom_navigation_view.setOnNavigationItemSelectedListener(this)

    }

    private fun displayFragment(fragment: Fragment) {
        val manager: FragmentManager = this.fragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }
}