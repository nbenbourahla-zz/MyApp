package com.nazim.myapplication.api;

import com.google.gson.Gson;
import com.nazim.myapplication.repository.PhotosRepository;
import com.nazim.myapplication.storage.DataSource;
import dagger.Component;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Component that hold all Api dependancies
 */
@Singleton
@Component(modules = ApiModule.class) public interface ApiComponent {
    Gson provideGson();

    Cache provideCache();

    DataSource provideDataSource();

    OkHttpClient provideOkhttp();

    Retrofit provideRetrofit();

    PhotosService providePhotosService();

    PhotosRepository providePhotosRepository();
}
