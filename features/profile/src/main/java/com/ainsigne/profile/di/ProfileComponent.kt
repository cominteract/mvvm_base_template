package com.ainsigne.profile.di


import com.ainsigne.common.scopes.Fragment
import com.ainsigne.core.di.components.CoreComponent
import com.ainsigne.futurama.di.ProfileViewModelModule
import com.ainsigne.profile.presenter.ProfileFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class],
    modules = [ProfileViewModelModule::class]
)
@Fragment
interface ProfileComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): ProfileComponent
    }

    fun inject(profileFragment: ProfileFragment)
}