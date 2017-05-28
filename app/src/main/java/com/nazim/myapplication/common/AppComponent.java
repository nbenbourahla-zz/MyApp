package com.nazim.myapplication.common;

import dagger.Component;
import javax.inject.Singleton;

/**
 * Component that declare all app general dependancies
 */
@Singleton @Component(modules = AppModule.class) public interface AppComponent {
    ImageLoader provideImageLoader();
}
