package com.northcoders.recordshopfrontend.ui.addalbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.northcoders.recordshopfrontend.model.Album;
import com.northcoders.recordshopfrontend.ui.mainactivity.MainActivity;
import com.northcoders.recordshopfrontend.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandlers {

    private Album album;
    private Context context;
    private MainActivityViewModel viewModel;

    public AddAlbumClickHandlers(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void saveAlbum(View view) {
        if(album.getName() == null || album.getAlbumArtist().getName() == null) {
            Toast.makeText(context, "Fields cannot be null", Toast.LENGTH_SHORT).show();
            System.out.println(album.getName() + " " + album.getAlbumArtist().getName());
        } else {
            viewModel.addAlbum(new Album(album.getName(), album.getReleaseYear(), album.getStock(), album.getImage(), album.getGenreSet(), album.getAlbumArtist()));
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
        }
    }

    public void backButton(View view) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
    }
}
