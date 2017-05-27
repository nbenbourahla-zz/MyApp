package com.nazim.myapplication.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class ImageLoader {

    public ImageLoader() {
    }

    public void loadImageInto(@NonNull final Context context, @NonNull final String imageUrl,
        @NonNull final ImageView destination, final int backupImage) {
        Glide.with(context).load(imageUrl).
            error(backupImage).into(destination);
    }
}
