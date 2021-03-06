package com.example.linda.movieku;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardViewMovieAdapter extends RecyclerView.Adapter<CardViewMovieAdapter.CardViewHolder> {
    private ArrayList<Movie> movies;
    private Context context;

    public CardViewMovieAdapter(Context context){
        this.context = context;
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewMovieAdapter.CardViewHolder cardViewHolder, int i) {
        final Movie m = getMovies().get(i);

        Glide.with(context)
                .load(m.getImgPoster())
                .override(350, 350)
                .into(cardViewHolder.imgPoster);

        cardViewHolder.txtTitle.setText(m.getTxtTitle());
        String overview = m.getTxtOverview();
        if(overview.length() >= 100){
            overview = m.getTxtOverview().substring(0, 100) + "... ";
        }
        cardViewHolder.txtOverview.setText(overview);

        cardViewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Detail" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra("F", m);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPoster;
        TextView txtTitle, txtOverview;
        Button btnDetail;

        public CardViewHolder(View view) {
            super(view);
            imgPoster = (ImageView)itemView.findViewById(R.id.imgPoster);
            txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
            txtOverview = (TextView)itemView.findViewById(R.id.txtOverview);
            btnDetail = (Button)itemView.findViewById(R.id.btnDetail);
        }


    }
}
