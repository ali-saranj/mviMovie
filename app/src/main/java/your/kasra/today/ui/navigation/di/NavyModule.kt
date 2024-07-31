package your.kasra.today.ui.navigation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import your.kasra.today.ui.navigation.NavAction
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavyModule {

    @Singleton
    @Provides
    fun provideNavAction() = NavAction()
}