package com.bookislife.kandroid365

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.bookislife.kandroid365.category.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        private val TAG_FRAGMENT = "fragment"
    }

    var toggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar()

        toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle!!)
        toggle?.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        // select first item at startup
        onNavigationItemSelected(navigationView.menu.getItem(0))
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        drawer.removeDrawerListener(toggle!!)
    }

    @SuppressWarnings("StatementWithEmptyBody")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = when (item.itemId) {
            R.id.nav_component -> ComponentListFragment()
            R.id.nav_system -> SystemListFragment()
            R.id.nav_net -> NetListFragment()
            R.id.nav_theme -> ThemeListFragment()
            R.id.nav_interaction -> InteractionListFragment()
            R.id.nav_window -> WindowListFragment()
            R.id.nav_media -> MediaListFragment()
            R.id.nav_storage -> StorageListFragment()
            R.id.nav_tool -> ToolListFragment()
            R.id.nav_animation -> AnimationListFragment()
            else -> TextListFragment()
        }
        replace(R.id.container, fragment)
        toolbar?.title = fragment.javaClass.simpleName.replace("ListFragment", "")
        drawer?.closeDrawer(GravityCompat.START)
        return true
    }

    protected fun replace(id: Int, targetFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(TAG_FRAGMENT)
        if (fragment == null) {
            fragmentManager.beginTransaction().add(id, targetFragment, TAG_FRAGMENT).commit()
        } else {
            fragmentManager.beginTransaction()
                    .replace(id, targetFragment, TAG_FRAGMENT)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
        }
    }
}
