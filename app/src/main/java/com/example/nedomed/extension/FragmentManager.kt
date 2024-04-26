package com.example.nedomed.extension

import androidx.fragment.app.commit
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.nedomed.R


fun FragmentManager.switchFragment(fragment: Fragment, tag: String): Boolean {
    if (fragment.isAdded) return false
    commit {
        // Detach a fragment
        findFragmentById(R.id.frame_layout)?.also {
            detach(it)
        }
        // Attach or add a fragment
        if (fragment.isDetached) {
            attach(fragment)
        } else {
            add(R.id.frame_layout, fragment, tag)
        }
        // Set the animation for this transaction
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    }
    // Immediately execute transactions
    return executePendingTransactions()
}