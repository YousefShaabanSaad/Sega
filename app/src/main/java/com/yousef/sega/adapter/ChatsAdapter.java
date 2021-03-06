package com.yousef.sega.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.yousef.sega.R;
import com.yousef.sega.model.Chat;
import com.yousef.sega.model.Constants;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    private final List<Chat> chats;
    private final Context context;
    public ChatsAdapter(Context context, List<Chat> chats){
        this.context = context;
        this.chats = chats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = chats.get(position);
        if(chat.getProfile().equals(Constants.LOGO))
            holder.imageChat.setImageResource(R.drawable.logo);
        else
            Glide.with(context).load(Uri.parse(chat.getProfile())).centerCrop().placeholder(R.drawable.profile).into(holder.imageChat);

        if(chat.getName().equals(Constants.LOGO))
            holder.nameChat.setText(context.getString(R.string.segaOwner));
        else
            holder.nameChat.setText(chat.getName());

        if(chat.getMessage().equals(Constants.LOGO))
            holder.message.setText(context.getString(R.string.welcome));
        else
            holder.message.setText(chat.getMessage());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final CircleImageView imageChat;
        private final TextView nameChat, message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageChat = itemView.findViewById(R.id.profileChat);
            nameChat = itemView.findViewById(R.id.nameChat);
            message = itemView.findViewById(R.id.message);
        }
    }
}
