package com.nazim.myapplication.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Component that abstract how we load images in the app.
 */
public class ImageLoader {

    public ImageLoader() {
    }

    /***
     * Load a given image (from url) to a given ImageView.
     * You must provide a backup image if the load failed
     * @param context The context
     * @param imageUrl The image url.
     * @param destination the ImageView used to display the image.
     * @param backupImage A backup image used if the load failed.
     */
    public void loadImageInto(@NonNull final Context context, @NonNull final String imageUrl,
        @NonNull final ImageView destination, final int backupImage) {
        Glide.with(context).load(imageUrl).
            error(backupImage).into(destination);
    }
}
