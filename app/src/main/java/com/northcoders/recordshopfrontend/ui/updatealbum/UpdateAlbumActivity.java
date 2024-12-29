package com.northcoders.recordshopfrontend.ui.updatealbum;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.recordshopfrontend.R;
import com.northcoders.recordshopfrontend.databinding.ActivityUpdateAlbumBinding;
import com.northcoders.recordshopfrontend.model.Album;
import com.northcoders.recordshopfrontend.ui.mainactivity.MainActivity;
import com.northcoders.recordshopfrontend.ui.mainactivity.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateAlbumActivity extends AppCompatActivity {

    private ActivityUpdateAlbumBinding binding;
    private UpdateAlbumClickHandler handler;
    private Album album;
    private static String id = "album";

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_album);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        album = getIntent().getParcelableExtra(id, Album.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_album);
        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        TextView genrePicker = findViewById(R.id.genreSelector);
        TextView selectedPreview = findViewById(R.id.selectedGenres);
        String[] genreChoices = new String[]{ "Pop",  "Rock", "Hiphop", "Rap", "Jazz", "Classical", "Blues", "Metal", "Dance"};
        boolean[] checkedItems = new boolean[genreChoices.length];
        List<String> choiceList = Arrays.asList(genreChoices);


        selectedPreview.setText(Arrays.toString(album.getGenreSet()).substring(1, Arrays.toString(album.getGenreSet()).length() - 1));

        genrePicker.setOnClickListener( view -> {
            selectedPreview.setText(null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            for(String gen : album.getGenreSet()) {
                int selectedIndex = choiceList.indexOf(gen);
                checkedItems[selectedIndex] = true;

            }
            builder.setTitle("Select Genres");
            builder.setMultiChoiceItems(genreChoices, checkedItems, (dialog, which, isChecked) -> {
                checkedItems[which] = isChecked;
                String currentItem = choiceList.get(which);
            });

            builder.setPositiveButton("Done", (dialog, which) -> {
                List<String> picks = new ArrayList<String>();
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        picks.add(choiceList.get(i));
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
        binding.setAlbum(album);
        handler = new UpdateAlbumClickHandler(album, this, viewModel);
        binding.setClickHandlers(handler);


    }

}