package com.example.nedomed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nedomed.databinding.ActivityMainBinding
import com.example.nedomed.extension.active
import com.example.nedomed.extension.switchFragment
import com.example.nedomed.helper.BottomNavigationPosition
import com.example.nedomed.helper.createFragment
import com.example.nedomed.helper.findNavigationPositionById
import com.example.nedomed.helper.getTag


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreSavedInstanceState(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.bottomNavigation.apply {
            // Set a default position
            active(navPosition.position) // Extension function
            // Set a listener for handling selection events on bottom navigation items
            setOnNavigationItemSelectedListener { item ->
                navPosition = findNavigationPositionById(item.itemId)
                switchFragment(navPosition)
            }
        }
        // Add the home fragment
        savedInstanceState ?: switchFragment(BottomNavigationPosition.HOME)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Store the current navigation position.
        outState.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        // Restore the current navigation position.
        savedInstanceState?.getInt(KEY_POSITION, BottomNavigationPosition.HOME.id)?.also {
            navPosition = findNavigationPositionById(it)
        }
    }

    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
        return findFragment(navPosition).let {
            supportFragmentManager.switchFragment(it, navPosition.getTag()) // Extension function
        }
    }

    private fun findFragment(position: BottomNavigationPosition): Fragment {
        return supportFragmentManager.findFragmentByTag(position.getTag())
            ?: position.createFragment()
    }

    companion object {
        const val KEY_POSITION = "keyPosition"
        const val TAG = "MainActivity"
    }
}