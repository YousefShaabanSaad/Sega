package com.yousef.sega.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yousef.sega.R;
import com.yousef.sega.database.Repository;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.Game;
import com.yousef.sega.model.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.ViewHolder> {

    private final List<User> users;
    private final Context context;
    private final Game game;
    private final Repository repository;

    public ParticipantsAdapter(Context context,  List<User> users, Game game){
        this.context = context;
        this.users = users;
        this.game = game;
        repository = new Repository();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_participants, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        Glide.with(context).load(Uri.parse(user.getProfile())).centerCrop().placeholder(R.drawable.profile).into(holder.imageParticipants);
        holder.nameParticipants.setText(user.getName());

        if(!game.getIdOwner().equals(repository.getUser().getUid())) {
           holder.play.setVisibility(View.GONE);
        }

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repository repository = new Repository();
                if(game.getIdOwner().equals(repository.getUser().getUid())) {
                    repository.updateGame(game.getId(), Constants.PLAYER2, user.getId());
                    repository.updateGame(game.getId(), Constants.ID_PLAYER, repository.getUser().getUid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final CircleImageView imageParticipants;
        private final TextView nameParticipants;
        private final Button play;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageParticipants = itemView.findViewById(R.id.imageParticipants);
            nameParticipants = itemView.findViewById(R.id.nameParticipants);
            play = itemView.findViewById(R.id.play);
        }


    }
}
