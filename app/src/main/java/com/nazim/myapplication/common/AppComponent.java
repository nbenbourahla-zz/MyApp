package com.nazim.myapplication.common;

import com.nazim.myapplication.navigation.NavigationHelper;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = AppModule.class) public interface AppComponent {
    ImageLoader provideImageLoader();
}
