package com.example.songlist;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Song implements Parcelable {
    private String category;
    private String title;
    private String artist;
    private String genre;
    private String date;
    private String image;
    private String website;

    public Song(String category, String title, String artist, String genre, String date, String image, String website) {
        this.category = category;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.date = date;
        this.image = image;
        this.website = website;
    }

    protected Song(Parcel in) {
        category = in.readString();
        title = in.readString();
        artist = in.readString();
        genre = in.readString();
        date = in.readString();
        image = in.readString();
        website = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getWebsite() {
        return website;
    }

    public static ArrayList<Song> createSongList(Context context) {
        ArrayList<Song> SongList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        try {
            AssetManager am = context.getAssets();
            InputStream inputStream = am.open("song_list.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] var = receiveString.split(":",2);
                    list.add(var[0]);
                    list.add(var[1]);
                }
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        if (list.size() != 0) {
            String category = null;
            String image = null;
            String title = null;
            String website = null;
            String releaseDate = null;
            String artist = null;
            String genre = null;

            for (int i = 0; i < list.size(); i++) {
                if ("Category".equals(list.get(i))) {
                    System.out.println(list.get(i + 1));
                    category = list.get(i + 1);
                }
                if ("Image".equals(list.get(i))) {
                    String[] split_image = list.get(i + 1).trim().split("[.]");
                    image = "drawable/" + split_image[0];
                }
                if ("Title".equals(list.get(i))) {
                    title = list.get(i + 1);
                }
                if ("Website".equals(list.get(i))) {
                    website = list.get(i + 1).trim();
                }
                if ("Release date".equals(list.get(i))) {
                    releaseDate = list.get(i + 1);
                }
                if ("Genre".equals(list.get(i))) {
                    genre = list.get(i + 1).trim();
                }
                if ("Artist".equals(list.get(i))) {
                    artist = list.get(i + 1);
                    Song newSong = new Song(category, title, artist, genre, releaseDate, image, website);
                    category = "";
                    image = "";
                    title = "";
                    website = "";
                    releaseDate = "";
                    artist = "";
                    genre = "";
                    SongList.add(newSong);
                }
            }
        }
        return SongList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(category);
        parcel.writeString(title);
        parcel.writeString(artist);
        parcel.writeString(genre);
        parcel.writeString(date);
        parcel.writeString(image);
        parcel.writeString(website);
    }
}
