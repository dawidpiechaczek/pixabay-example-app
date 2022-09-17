package com.appsirise.core.ui.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment

fun FragmentManager.findNavControllerCompat(@IdRes navId: Int) =
    (findFragmentById(navId) as NavHostFragment).navController
