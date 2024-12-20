package com.northcoders.recordshopfrontend.model;

import android.app.Application;
import android.content.Context;
import android.media.MediaDrm;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.northcoders.recordshopfrontend.service.AlbumApiService;
import com.northcoders.recordshopfrontend.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepository {
  private MutableLiveData<List<Album>> mutableLiveData = new MutableLiveData<>();
  private Application application;

    public AlbumRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Album>> getMutableLiveData() {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        var call = albumApiService.getAllAlbums();

        call.enqueue(new Callback<List<Album>>(){

            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                List<Album> albums = response.body();
                mutableLiveData.setValue(albums);

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.e("exception", t.getMessage(), t);
            }
        });

        return mutableLiveData;
    }


    public void addNewAlbum(Album album) {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        var call = albumApiService.addAlbum(album);
        call.enqueue(new Callback<>() {
                                  @Override
                                  public void onResponse(Call<Album> call, Response<Album> response) {

                                        Toast.makeText(application.getBaseContext(), "Album added successfully", Toast.LENGTH_SHORT).show();
                                  }

                                  @Override
                                  public void onFailure(Call<Album> call, Throwable t) {
                                      Toast.makeText(application.getBaseContext(), "Could not add album", Toast.LENGTH_SHORT).show();;
                                      Log.e("POST error", t.getMessage());
                                  }
                              }
        );
    }

    public void updateAlbum(Long id, Album album) {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        var call = albumApiService.updateAlbum(id, album);
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(application.getBaseContext(), "Album updated successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(application.getBaseContext(), "Could not update album", Toast.LENGTH_SHORT).show();;
                Log.e("PUT_error", t.getMessage());
            }
        });
    }


    public void deleteAlbum(Long id) {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        var call = albumApiService.deleteAlbum(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(application.getBaseContext(), "Album deleted successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //Toast.makeText(application.getBaseContext(), "Could not delete album", Toast.LENGTH_SHORT).show();;
                Log.e("DELETE_error", t.getMessage());
            }
        });
    }
}
