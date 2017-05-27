package com.nazim.myapplication.common;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class AppModule {
    @Provides
    @Singleton
    ImageLoader provideImageLoader() {
        return new ImageLoader();
    }
}
