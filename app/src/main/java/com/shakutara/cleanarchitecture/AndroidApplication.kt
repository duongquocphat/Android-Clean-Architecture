package com.shakutara.cleanarchitecture

import android.app.Application
import com.shakutara.cleanarchitecture.core.di.ApplicationComponent
import com.shakutara.cleanarchitecture.core.di.ApplicationModule
import com.shakutara.cleanarchitecture.core.di.DaggerApplicationComponent

class AndroidApplication : Application() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}