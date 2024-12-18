package com.northcoders.recordshopfrontend.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.northcoders.recordshopfrontend.model.Album;
import com.northcoders.recordshopfrontend.model.AlbumRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    AlbumRepository albumRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.albumRepository = new AlbumRepository(application);
    }

    public MutableLiveData<List<Album>> getData() {
        return albumRepository.getMutableLiveData();
    }
}
