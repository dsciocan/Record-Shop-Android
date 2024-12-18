package com.northcoders.recordshopfrontend.ui.mainactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.northcoders.recordshopfrontend.BR;
import com.northcoders.recordshopfrontend.R;
import com.northcoders.recordshopfrontend.databinding.AlbumLayoutBinding;
import com.northcoders.recordshopfrontend.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    List<Album> albums;
    Context context;

    public AlbumAdapter(List<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.album_layout,
                parent, 
                false);
        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.albumLayoutBinding.setAlbum(album);
        Glide.with(context).load(album.getImage()).into(holder.albumLayoutBinding.albumCover);
    }
    

    @Override
    public int getItemCount() {
        return albums.size();
    }


    public static class AlbumViewHolder extends RecyclerView.ViewHolder {

        AlbumLayoutBinding albumLayoutBinding;
        public AlbumViewHolder(@NonNull AlbumLayoutBinding albumLayoutBinding) {
            super(albumLayoutBinding.getRoot());
            this.albumLayoutBinding = albumLayoutBinding;
        }
    }
}
