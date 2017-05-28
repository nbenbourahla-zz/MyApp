package com.nazim.myapplication.common;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class AppModule {
    /**
     * Provide the dependencie used for loading images
     */
    @Provides
    @Singleton
    ImageLoader provideImageLoader() {
        return new ImageLoader();
    }
}
