package com.chichi289.cleanarchitecture.hilt

import com.chichi289.cleanarchitecture.common.Constants
import com.chichi289.cleanarchitecture.data.remote.MealSearchAPI
import com.chichi289.cleanarchitecture.data.repository.MealDetailsRepositoryImpl
import com.chichi289.cleanarchitecture.data.repository.MealSearchRepositoryImpl
import com.chichi289.cleanarchitecture.domain.repository.MealDetailsRepository
import com.chichi289.cleanarchitecture.domain.repository.MealSearchRepository
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(OkHttpProfilerInterceptor())
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideMealSearchAPI(retrofit: Retrofit): MealSearchAPI =
        retrofit.create(MealSearchAPI::class.java)

    @Provides
    @Singleton
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository =
        MealSearchRepositoryImpl(mealSearchAPI)

    @Provides
    @Singleton
    fun provideMealDetails(searchMealSearchAPI: MealSearchAPI): MealDetailsRepository =
        MealDetailsRepositoryImpl(searchMealSearchAPI)

}