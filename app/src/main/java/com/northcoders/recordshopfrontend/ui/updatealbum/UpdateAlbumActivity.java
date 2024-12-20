package com.northcoders.recordshopfrontend.ui.updatealbum;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
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

public class UpdateAlbumActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        handler = new UpdateAlbumClickHandler(album, this, viewModel);
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

        binding.setClickHandlers(handler);

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