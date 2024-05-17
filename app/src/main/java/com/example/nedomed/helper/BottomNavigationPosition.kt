package com.example.nedomed.helper

import androidx.fragment.app.Fragment
import com.example.nedomed.ui.main.AccountFragment
import com.example.nedomed.ui.main.DetailFragment
import com.example.nedomed.ui.main.HomeFragment
import com.example.nedomed.R
import com.example.nedomed.ui.main.SearchFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    HOME(0, R.id.navHome),
    DETAIL(1, R.id.navNote),
    SEARCH(2, R.id.navSearch),
    PROFILE(3, R.id.navAccount);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
    BottomNavigationPosition.DETAIL.id -> BottomNavigationPosition.DETAIL
    BottomNavigationPosition.SEARCH.id -> BottomNavigationPosition.SEARCH
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.HOME
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.newInstance()
    BottomNavigationPosition.DETAIL -> DetailFragment.newInstance()
    BottomNavigationPosition.SEARCH -> SearchFragment.newInstance()
    BottomNavigationPosition.PROFILE -> AccountFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.TAG
    BottomNavigationPosition.DETAIL -> DetailFragment.TAG
    BottomNavigationPosition.SEARCH -> SearchFragment.TAG
    BottomNavigationPosition.PROFILE -> AccountFragment.TAG
}