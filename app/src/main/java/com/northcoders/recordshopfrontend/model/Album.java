package com.northcoders.recordshopfrontend.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.northcoders.recordshopfrontend.BR;

import java.lang.reflect.Array;

public class Album extends BaseObservable implements Parcelable {

    private Long id;
    private String name;
    private int releaseYear;
    private int stock;
    private String image;
    private String[] genreSet;
    private Artist albumArtist;


    public Album(long id, String name, int releaseYear, int stock, String image, String[] genreSet, Artist albumArtist) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.stock = stock;
        this.image = image;
        this.genreSet = genreSet;
        this.albumArtist = albumArtist;
    }

    public Album(String name, int releaseYear, int stock, String image, String[] genreSet, Artist albumArtist) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.stock = stock;
        this.image = image;
        this.genreSet = genreSet;
        this.albumArtist = albumArtist;
    }

    public Album() {
    }


    protected Album(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        releaseYear = in.readInt();
        stock = in.readInt();
        image = in.readString();
        genreSet = in.createStringArray();
        albumArtist = in.readParcelable(Artist.class.getClassLoader());
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getReleaseYear() {
        return releaseYear;
    }


    public String getReleaseYearString() {
        return String.format("-%s-", releaseYear);
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        notifyPropertyChanged(BR.releaseYear);
    }

    @Bindable
    public int getStock() {
        return stock;
    }

    public String getStockString() {
        return "In stock: " + stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        notifyPropertyChanged(BR.stock);
    }


    @Bindable
    public String[] getGenreSet() {
        return genreSet;
    }


    public void setGenreSet(String[] genreSet) {
        this.genreSet = genreSet;
        notifyPropertyChanged(BR.genreSet);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    public String getGenres() {
        StringBuilder genres = new StringBuilder().append(genreSet[0]);
        for (int i = 1; i < genreSet.length; i++) {
            genres.append(", ").append(genreSet[i]);
        }
        return genres.toString();
    }

    @Bindable
    public Artist getAlbumArtist() {
        return albumArtist;
    }

    public String getAlbumArtistString() {
        return "by " + albumArtist.getName();
    }

    public void setAlbumArtist(Artist albumArtist) {
        this.albumArtist = albumArtist;
        notifyPropertyChanged(BR.albumArtist);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeInt(releaseYear);
        dest.writeInt(stock);
        dest.writeString(image);
        dest.writeStringArray(genreSet);
        dest.writeParcelable(albumArtist, flags);
    }

}
