package com.northcoders.recordshopfrontend.ui.addalbum;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
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

public class AddNewAlbumActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

        Spinner spinner = binding.genreSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.genre_array,
                android.R.layout.simple_spinner_item
        );
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        handlers = new AddAlbumClickHandlers(album, this, mainActivityViewModel);
        binding.setClickHandlers(handlers);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] newArray = {parent.getItemAtPosition(position).toString()};
        album.setGenreSet(newArray);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}