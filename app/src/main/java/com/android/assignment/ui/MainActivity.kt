package com.android.assignment.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.assignment.R
import com.android.assignment.databinding.MainActivityBinding
import com.android.assignment.ui.main.MainFragment
import com.android.assignment.util.Constants
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private var mainFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        mainFragment = if(savedInstanceState!=null){
            supportFragmentManager.getFragment(savedInstanceState, Constants.MAIN_FRAGMENT)
        } else
            MainFragment()
        supportFragmentManager.beginTransaction()
                    .replace(R.id.container, mainFragment!!)
                    .commitNow()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        supportFragmentManager.putFragment(outState,Constants.MAIN_FRAGMENT,mainFragment!!)
        super.onSaveInstanceState(outState)
    }
}