package com.northcoders.recordshopfrontend.ui.addalbum;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.recordshopfrontend.R;
import com.northcoders.recordshopfrontend.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.recordshopfrontend.databinding.ActivityMainBinding;
import com.northcoders.recordshopfrontend.model.Album;
import com.northcoders.recordshopfrontend.model.Artist;
import com.northcoders.recordshopfrontend.ui.mainactivity.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddNewAlbumActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityAddNewAlbumBinding binding;
    private AddAlbumClickHandlers handlers;
    private Album album;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_album);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        album = new Album(null, 0, 0, null, null, new Artist(null));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_album);
        binding.setAlbum(album);

         TextView genrePicker = findViewById(R.id.genreSelector);
         TextView selectedPreview = findViewById(R.id.selectedGenres);
         String[] genreChoices = new String[]{ "Pop",  "Rock", "Hiphop", "Rap", "Jazz", "Classical", "Blues", "Metal", "Dance"};
         boolean[] checkedItems = new boolean[genreChoices.length];
         List<String> selectedList = Arrays.asList(genreChoices);


        genrePicker.setOnClickListener( view -> {
            selectedPreview.setText(null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Genres");
            builder.setMultiChoiceItems(genreChoices, checkedItems, (dialog, which, isChecked) -> {
                checkedItems[which] = isChecked;
                String currentItem = selectedList.get(which);
             });

            builder.setPositiveButton("Done", (dialog, which) -> {
                List<String> picks = new ArrayList<String>();
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        picks.add(selectedList.get(i));
                    }
                }
                selectedPreview.setText(picks.toString().substring(1, picks.toString().length() - 1));
                album.setGenreSet(picks.toArray(String[]::new));
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> {});
            builder.create();
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });


        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        handlers = new AddAlbumClickHandlers(album, this, mainActivityViewModel);
        binding.setClickHandlers(handlers);




    }


}