package com.nazim.myapplication.listrepos;

import com.nazim.myapplication.api.ApiModule;
import com.nazim.myapplication.common.AppModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { ApiModule.class, AppModule.class }) public interface PhotosListComponent {
    void inject(PhotosListActivity main);
}

