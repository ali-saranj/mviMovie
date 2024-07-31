package your.kasra.today.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import your.kasra.today.data.remote.IClientActor
import your.kasra.today.data.remote.IClientMovie
import your.kasra.today.utils.Constant
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("movie")
    fun provideRetrofitMovie(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("actor")
    fun provideRetrofitActor(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL_TMDB)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApiClient(@Named("movie") retrofit: Retrofit): IClientMovie {
        return retrofit.create(IClientMovie::class.java)
    }

    @Provides
    @Singleton
    fun provideActorApiClient(@Named("actor") retrofit: Retrofit): IClientActor {
        return retrofit.create(IClientActor::class.java)
    }
}