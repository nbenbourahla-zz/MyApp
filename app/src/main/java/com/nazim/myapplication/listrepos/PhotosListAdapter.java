package com.nazim.myapplication.listrepos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.nazim.myapplication.R;
import com.nazim.myapplication.model.Photo;
import java.util.List;

class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.ViewHolder> {

    private List<Photo> photoList;
    private final Context context;

    PhotosListAdapter(@NonNull final Context context) {
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView photoTitle;
        ImageView photoImg;

        ViewHolder(View view) {
            super(view);
            photoTitle = (TextView) view.findViewById(R.id.photo_title);
            photoImg = (ImageView) view.findViewById(R.id.photo_img);
        }
    }

    public void setData(List<Photo> photos) {
        this.photoList = photos;
    }

    @Override
    public PhotosListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_line, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotosListAdapter.ViewHolder holder, int position) {
        final Photo photo = photoList.get(position);
        holder.photoTitle.setText(photo.getTitle());
        Glide.with(context).load(photo.getThumbnailUrl()).
            error(R.mipmap.ic_launcher).into(holder.photoImg);
    }

    @Override
    public int getItemCount() {
        if (photoList != null) return photoList.size();
        return 0;
    }
}
