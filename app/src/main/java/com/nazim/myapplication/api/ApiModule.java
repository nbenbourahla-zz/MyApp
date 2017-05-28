package com.nazim.myapplication.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nazim.myapplication.repository.PhotosRepository;
import com.nazim.myapplication.storage.DataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Declaration of all Api dependancies
 */
@Module public class ApiModule {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    /**
     * Provide Gson configuration for the app
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    /**
     * Provide Cache for the app
     */
    @Provides
    @Singleton
    Cache provideCache() {
        return new Cache();
    }

    /**
     * Provide Okhttp instance
     */
    @Provides
    @Singleton
    OkHttpClient provideOkhttp() {
        return new OkHttpClient.Builder().build();
    }

    /**
     * Provide DataSource
     */
    @Provides
    @Singleton
    DataSource provideDataSource(final Cache cache, PhotosService photosService) {
        return new DataSource(cache, photosService);
    }

    /**
     * Provide Retrofit configuration for the app
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(final Gson gson, final OkHttpClient client) {
        return new Retrofit.Builder().client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build();
    }

    /**
     * Provide the PhotosService used for the WS calls
     *
     * @see PhotosService
     */
    @Provides
    @Singleton
    PhotosService providePhotosService(Retrofit retrofit) {
        return retrofit.create(PhotosService.class);
    }

    /**
     * Provide PhotosRepository to hold all Photos calls
     *
     * @see PhotosRepository
     */
    @Provides
    @Singleton
    PhotosRepository providePhotosRepository(DataSource dataSource) {
        return new PhotosRepository(dataSource);
    }
}
