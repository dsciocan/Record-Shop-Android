package com.northcoders.recordshopfrontend.ui.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.northcoders.recordshopfrontend.model.Album;
import com.northcoders.recordshopfrontend.ui.addalbum.AddNewAlbumActivity;

public class MainActivityClickHandler {

    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void addNewAlbumButton(View v) {
        Intent intent = new Intent(v.getContext(), AddNewAlbumActivity.class);
        context.startActivity(intent);
    }
}
