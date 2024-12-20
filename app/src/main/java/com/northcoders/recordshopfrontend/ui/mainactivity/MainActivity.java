package com.northcoders.recordshopfrontend.ui.mainactivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.recordshopfrontend.R;
import com.northcoders.recordshopfrontend.databinding.ActivityMainBinding;
import com.northcoders.recordshopfrontend.model.Album;
import com.northcoders.recordshopfrontend.model.AlbumRepository;
import com.northcoders.recordshopfrontend.ui.addalbum.AddAlbumClickHandlers;
import com.northcoders.recordshopfrontend.ui.mainactivity.adapter.AlbumAdapter;
import com.northcoders.recordshopfrontend.ui.updatealbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private ArrayList<Album> albums;
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler handler;
    private static String id = "album";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        Application application = new Application();
        AlbumRepository albumRepository = new AlbumRepository(application);
        albumRepository.getMutableLiveData();

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        getAllAlbums();

        handler = new MainActivityClickHandler(this);
        activityMainBinding.setClickHandler(handler);


    }

    private void getAllAlbums() {
        mainActivityViewModel.getData().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                albums = (ArrayList<Album>) albumsFromLiveData;
                displayInRecyclerView();
            }
        });
    }

    private void displayInRecyclerView() {
        recyclerView = activityMainBinding.recyclerView;
        albumAdapter = new AlbumAdapter(albums, this, this);
        recyclerView.setAdapter(albumAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        albumAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this,  UpdateAlbumActivity.class);
        intent.putExtra(id, albums.get(position));
        startActivity(intent);
    }
}