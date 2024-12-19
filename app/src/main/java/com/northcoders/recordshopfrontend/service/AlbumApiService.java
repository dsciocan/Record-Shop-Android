package com.northcoders.recordshopfrontend.service;

import com.northcoders.recordshopfrontend.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlbumApiService {

    @GET("album")
    public Call<List<Album>> getAllAlbums();

    @POST("album")
    public Call<Album> addAlbum(@Body Album album);

    @PUT("album/{id}")
    public Call<Album> updateAlbum(@Path("id") Long id, @Body Album album);

    @DELETE("album/{id}")
    public Call<String> deleteAlbum(@Path("id") Long id);
}
