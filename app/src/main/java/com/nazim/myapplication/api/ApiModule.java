package com.nazim.myapplication.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nazim.myapplication.repository.PhotosRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @Provides
    @Singleton Gson provideGson() {
        return new GsonBuilder()
            .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(final Gson gson) {
        return new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build();
    }

    @Provides
    @Singleton
    PhotosService providePhotosService(Retrofit retrofit) {
        return retrofit.create(PhotosService.class);
    }

    @Provides
    @Singleton
    PhotosRepository providePhotosRepository(PhotosService githubService) {
        return new PhotosRepository(githubService);
    }
}
