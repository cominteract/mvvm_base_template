package com.ainsigne.core.di

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.ainsigne.core.BuildConfig
import com.ainsigne.core.di.components.CoreComponent
import com.ainsigne.core.di.components.DaggerCoreComponent
import timber.log.Timber

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MainApplication).coreComponent
    }

}

fun Activity.coreComponent() = MainApplication.coreComponent(this)
fun Fragment.coreComponent() = MainApplication.coreComponent(requireContext())