package com.northcoders.recordshopfrontend.ui.mainactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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
import com.northcoders.recordshopfrontend.ui.mainactivity.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    List<Album> albums;
    Context context;
    RecyclerViewInterface recyclerViewInterface;

    public AlbumAdapter(List<Album> albums, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.albums = albums;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public void setFilteredList(List<Album> filteredLit) {
        albums = filteredLit;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.album_layout,
                parent, 
                false);
        return new AlbumViewHolder(binding, recyclerViewInterface);
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
        RecyclerViewInterface recyclerViewInterface;


        public AlbumViewHolder(@NonNull AlbumLayoutBinding albumLayoutBinding, RecyclerViewInterface recyclerViewInterface) {
            super(albumLayoutBinding.getRoot());
            this.albumLayoutBinding = albumLayoutBinding;
            this.recyclerViewInterface = recyclerViewInterface;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
