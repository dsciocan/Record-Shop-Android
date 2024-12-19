package com.northcoders.recordshopfrontend.service;

import com.northcoders.recordshopfrontend.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlbumApiService {

    @GET("album")
    public Call<List<Album>> getAllAlbums();

    @POST("album")
    public Call<Album> addAlbum(@Body Album album);
}
