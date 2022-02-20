package com.engin.pokedex.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.engin.pokedex.adapter.NearRecyclerAdapter
import javax.inject.Inject

class PokedexFragmentFactory @Inject constructor(
    private val nearRecyclerAdapter: NearRecyclerAdapter
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            HomeFragment::class.java.name -> HomeFragment(nearRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }

    }
}