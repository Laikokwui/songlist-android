package com.example.songlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.MyViewHolder> {
    private ArrayList<Song> SongList;
    private Context context;

    public PopAdapter(ArrayList<Song> songList) {
        this.SongList = songList;
    }

    public ArrayList<Song> getSongList() {
        return SongList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_layout, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Song song = SongList.get(position);

        if(song.getCategory().equals("Pop")) {
            holder.imageView_cover.setImageResource(context.getResources().getIdentifier(song.getImage(),null, context.getPackageName()));
            holder.textView_title.setText(song.getTitle());
            holder.textView_artist.setText("Artist: " + song.getArtist());
            holder.textView_date.setText("Released date: " + song.getDate());
        }
        else {
            holder.imageView_cover.setVisibility(View.GONE);
            holder.textView_title.setVisibility(View.GONE);
            holder.textView_artist.setVisibility(View.GONE);
            holder.textView_date.setVisibility(View.GONE);
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return SongList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView_cover;
        public TextView textView_title, textView_artist, textView_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_cover = itemView.findViewById(R.id.imageView_cover);
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_artist = itemView.findViewById(R.id.textView_artist);
            textView_date = itemView.findViewById(R.id.textView_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            FragmentThree fragmentThree = FragmentThree.newInstance(SongList.get(pos).getWebsite(),"pop");
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentOne_layout, fragmentThree)
                    .commit();
        }
    }
}
