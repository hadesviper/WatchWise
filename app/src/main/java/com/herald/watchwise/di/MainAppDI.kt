package com.herald.watchwise.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * this class extends the [Application] class and it's add in the manifest
 * file to be executed every time the app runs to initialize dagger hilt DI library
 */
@HiltAndroidApp
class MainAppDI :Application()