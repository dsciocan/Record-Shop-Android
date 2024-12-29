package com.northcoders.recordshopfrontend.ui.updatealbum;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.northcoders.recordshopfrontend.R;
import com.northcoders.recordshopfrontend.model.Album;
import com.northcoders.recordshopfrontend.ui.mainactivity.MainActivity;
import com.northcoders.recordshopfrontend.ui.mainactivity.MainActivityViewModel;

import java.util.Arrays;
import java.util.Objects;

public class UpdateAlbumClickHandler {

    Album album;
    MainActivityViewModel viewModel;
    Long albumId;
    Context context;


    public UpdateAlbumClickHandler(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void updateAlbum(View view) {
        Album newAlbum = new Album(album.getId(), album.getName(), album.getReleaseYear(), album.getStock(), album.getImage(), album.getGenreSet(), album.getAlbumArtist());

        if(Objects.equals(newAlbum.getName(), "") || Objects.equals(newAlbum.getAlbumArtist().getName(), "") || Arrays.equals(newAlbum.getGenreSet(), new String[0])) {
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            albumId = album.getId();
            viewModel.updateAlbum(albumId, newAlbum);
            context.startActivity(intent);
        }
    }

    public void deleteAlbum(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to permanently delete this album?")
                .setTitle("Delete");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(context, MainActivity.class);
                albumId = album.getId();
                viewModel.deleteAlbum(albumId);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    public void backButton(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
