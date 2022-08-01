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

public class OthersAdapter extends RecyclerView.Adapter<OthersAdapter.MyViewHolder> {
    private ArrayList<Song> SongList;
    private Context context;

    public OthersAdapter(ArrayList<Song> SongList) {
        this.SongList = SongList;
    }

    public ArrayList<Song> getSongList() {
        return SongList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.others_layout, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Song song = SongList.get(position);
        if(song.getCategory().equals("Others")){

            holder.imageView_cover2.setImageResource(context.getResources().getIdentifier(song.getImage(),null, context.getPackageName()));
            holder.textView_title2.setText(song.getTitle());
            holder.textView_artist2.setText("Artist: " + song.getArtist());
            holder.textView_date2.setText("Released date: " + song.getDate());
            holder.textView_genre2.setText("Genre: " + song.getGenre());
        }
        else
        {
            holder.imageView_cover2.setVisibility(View.GONE);
            holder.textView_title2.setVisibility(View.GONE);
            holder.textView_artist2.setVisibility(View.GONE);
            holder.textView_date2.setVisibility(View.GONE);
            holder.textView_genre2.setVisibility(View.GONE);
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return SongList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView_cover2;
        public TextView textView_title2, textView_artist2, textView_date2, textView_genre2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_cover2 = itemView.findViewById(R.id.imageView_cover2);
            textView_title2 = itemView.findViewById(R.id.textView_title2);
            textView_artist2 = itemView.findViewById(R.id.textView_artist2);
            textView_date2 = itemView.findViewById(R.id.textView_date2);
            textView_genre2 = itemView.findViewById(R.id.textView_genre2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            FragmentThree fragmentThree = FragmentThree.newInstance(SongList.get(pos).getWebsite(),"others");
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentTwo_layout, fragmentThree)
                    .commit();
        }
    }
}
