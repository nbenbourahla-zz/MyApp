package com.nazim.myapplication.api;

import com.google.gson.Gson;
import com.nazim.myapplication.repository.PhotosRepository;
import dagger.Component;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApiModule.class) public interface ApiComponent {
    Gson provideGson();

    Retrofit provideRetrofit();

    PhotosService providePhotosService();

    PhotosRepository providePhotosRepository();
}
