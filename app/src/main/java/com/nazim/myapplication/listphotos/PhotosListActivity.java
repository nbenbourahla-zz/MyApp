package com.nazim.myapplication.listphotos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nazim.myapplication.R;
import com.nazim.myapplication.common.ImageLoader;
import com.nazim.myapplication.repository.PhotosRepository;
import javax.inject.Inject;

public class PhotosListActivity extends AppCompatActivity {

    @Inject PhotosRepository githubRepository;
    private PhotosListAdapter repoListAdapter;
    @Inject PhotosListPresenter repoListPresenter;
    private PhotosListComponent component;
    @Inject ImageLoader imageLoader;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);

        ButterKnife.bind(this);

        component = DaggerPhotosListComponent.builder().build();
        component.inject(this);

        initView();

        repoListPresenter.loadData(repoListAdapter);
    }

    private void initView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        repoListAdapter = new PhotosListAdapter(this, imageLoader);
        recyclerView.setAdapter(repoListAdapter);
    }

    @Override
    protected void onDestroy() {
        repoListPresenter.unbind();
        component = null;
        super.onDestroy();
    }
}
