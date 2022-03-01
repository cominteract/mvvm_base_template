package com.ainsigne.futurama.di


import com.ainsigne.common.scopes.Fragment
import com.ainsigne.core.di.components.CoreComponent
import com.ainsigne.futurama.presenter.FuturamaCharactersFragment
import dagger.Component

@Component(dependencies = [CoreComponent::class],
    modules = [FuturamaViewModelModule::class]
)
@Fragment
interface FuturamaComponent {

    @Component.Factory
    interface Factory {
        fun create(component: CoreComponent): FuturamaComponent
    }

    fun inject(futuramaCharactersFragment: FuturamaCharactersFragment)
}