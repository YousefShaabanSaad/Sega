package com.yousef.sega.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yousef.sega.R;
import com.yousef.sega.adapter.ChatsAdapter;
import com.yousef.sega.adapter.ParticipantsAdapter;
import com.yousef.sega.database.Repository;
import com.yousef.sega.databinding.ActivityPlayWithFriendOnlineBinding;
import com.yousef.sega.listener.GameInterface;
import com.yousef.sega.model.Chat;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.Game;
import com.yousef.sega.model.User;

import java.util.List;

public class PlayWithFriendOnlineActivity extends AppCompatActivity implements GameInterface {

    private ActivityPlayWithFriendOnlineBinding binding;
    private String link;
    private Repository repository;
    private Game game;
    private String[] players;
    private boolean isClick;
    private int[] move;
    private int scoreY, scoreN;
    private Dialog dialog;
    private String delete;
    private List<Chat> chats;
    private ChatsAdapter chatsAdapter;
    private List<User> users;
    private ParticipantsAdapter participantsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayWithFriendOnlineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        binding.num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPlayer(1);
            }
        });

        binding.num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPlayer(2);
            }
        });

        binding.num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPlayer(3);
            }
        });

        binding.num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPlayer(4);
            }
        });

        binding.num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPlayer(5);
            }
        });

        binding.num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPlayer(6);
            }
        });

        binding.num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPlayer(7);
            }
        });

        binding.num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPlayer(8);
            }
        });

        binding.num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPlayer(9);
            }
        });


        binding.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.updateGame(game.getId(), Constants.REACT, Constants.LOVE);
                repository.updateGame(game.getId(), Constants.ID_REACT, repository.getUser().getUid());
            }
        });

        binding.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.updateGame(game.getId(), Constants.REACT, Constants.LIKE);
                repository.updateGame(game.getId(), Constants.ID_REACT, repository.getUser().getUid());
            }
        });

        binding.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.updateGame(game.getId(), Constants.REACT, Constants.DISLIKE);
                repository.updateGame(game.getId(), Constants.ID_REACT, repository.getUser().getUid());
            }
        });

        binding.chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChat();
            }
        });

        binding.participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showParticipants();
            }
        });

    }

    private void init(){
        scoreY=0;
        scoreN=0;
        delete = "";
        game = new Game();

        link = "https://yousef.sega.com/";
        Uri uri = getIntent().getData();
        repository = new Repository();
        String id;

        if(uri != null){
            String path = uri.getPath();
            id = path.substring(1);
            game = repository.getGame(id, this);
            if(!game.getPlayer2().equals("")) {
                repository.updateGame(id, Constants.PLAYER2, repository.getUser().getUid());
                repository.updateGame(id, Constants.STATUS, Constants.PLAY);
                Toast.makeText(this, "يمكنك اللعب الآن", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Game game1 = new Game();
            game1.setIdPlayer(repository.getUser().getUid());
            game1.setIdOwner(repository.getUser().getUid());
            game1.setIdReact("");
            game1.setPlayer1(repository.getUser().getUid());
            game1.setPlayer2("");
            game1.setStatus(Constants.WAITING);
            game1.setNumber(-1);
            game1.setReact("");
            id = repository.createNewGame(game1, this);

            Toast.makeText(this, "لا يمكنك اللعب الآن", Toast.LENGTH_SHORT).show();
            createChat(id);
        }

        createUser(id);
        link+= id;
    }

    private void createChat(String id){
        Chat chat = new Chat();
        chat.setProfile(Constants.LOGO);
        chat.setName(Constants.LOGO);
        chat.setMessage(Constants.LOGO);
        repository.createNewChat(id,chat);
    }

    private void createUser(String id){
        User user = new User();
        user.setId(repository.getUser().getUid());
        user.setName(repository.getUser().getDisplayName());
        //user.setProfile(repository.getUser().getPhotoUrl().toString());
        user.setProfile("https://images.unsplash.com/photo-1453728013993-6d66e9c9123a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dmlld3xlbnwwfHwwfHw%3D&w=1000&q=80");
        repository.createNewParticipants(id,user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.copyLink){
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = android.content.ClipData.newPlainText(getString(R.string.app_name), link);
            clipboardManager.setPrimaryClip(clipData);
        }
        if (item.getItemId() == R.id.shareLink){
            Intent intent =new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, link);
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, getString(R.string.shareLink)));
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkPlayer(int numOfButton){
        if(game.getIdPlayer().equals(repository.getUser().getUid())){
            if(!isClick){
                if (game.getIdPlayer().equals(game.getPlayer1()) && players[numOfButton - 1].equals(Constants.Y)) {
                    repository.updatePlay(game.getId(), numOfButton);
                }
                else if (game.getIdPlayer().equals(game.getPlayer2()) && players[numOfButton - 1].equals(Constants.N)) {
                    repository.updatePlay(game.getId(), numOfButton);
                }
            }
            else{
                if (game.getIdPlayer().equals(game.getPlayer1()) && players[numOfButton - 1].equals("")) {
                    repository.updatePlay(game.getId(), numOfButton);
                }
                else if (game.getIdPlayer().equals(game.getPlayer2()) && players[numOfButton - 1].equals("")) {
                    repository.updatePlay(game.getId(), numOfButton);
                }
            }
        }
    }

    @Override
    public void getGame(Game thisGame) {
        game = thisGame;
        if(game.getNumber()==0 || game.getNumber()==-1){
            setImage(1, R.drawable.y);
            setImage(2, R.drawable.y);
            setImage(3, R.drawable.y);
            setImage(4, 0);
            setImage(5, 0);
            setImage(6, 0);
            setImage(7, R.drawable.n);
            setImage(8, R.drawable.n);
            setImage(9, R.drawable.n);

            setBackground(1,  R.drawable.item_bg);
            setBackground(2,  R.drawable.item_bg);
            setBackground(3,  R.drawable.item_bg);
            setBackground(4,  R.drawable.item_bg);
            setBackground(5,  R.drawable.item_bg);
            setBackground(6,  R.drawable.item_bg);
            setBackground(7,  R.drawable.item_bg);
            setBackground(8,  R.drawable.item_bg);
            setBackground(9, R.drawable.item_bg);

            players = new String[]{Constants.Y, Constants.Y, Constants.Y, "", "", "", Constants.N, Constants.N, Constants.N};
            isClick = false;
            move= new int[10];
            if(game.getNumber()==0)
                dialog.dismiss();
        }
        else{
            if(!isClick){
                setImage(game.getNumber(), 0);
                players[game.getNumber() - 1]="";
                if(game.getNumber()==1)
                    move[1]=1;
                else if(game.getNumber()==2)
                    move[2]=2;
                else if(game.getNumber()==3)
                    move[3]=3;
                else if(game.getNumber()==7)
                    move[7]=7;
                else if(game.getNumber()==8)
                    move[8]=8;
                else if(game.getNumber()==9)
                    move[9]=9;
                isClick = true;
            }
            else {
              if (game.getIdPlayer().equals(game.getPlayer1())) {
                    setImage(game.getNumber(), R.drawable.y);
                    players[game.getNumber() - 1] = Constants.Y;
                    repository.updateGame(game.getId(), Constants.ID_PLAYER, game.getPlayer2());
                    binding.n.setBackgroundResource(R.drawable.item_bg2);
                }
              else if (game.getIdPlayer().equals(game.getPlayer2())) {
                    setImage(game.getNumber(), R.drawable.n);
                    players[game.getNumber() - 1] = Constants.N;
                    repository.updateGame(game.getId(), Constants.ID_PLAYER, game.getPlayer1());
                  binding.y.setBackgroundResource(R.drawable.item_bg2);
              }
                isClick = false;
                checkWin();
            }
        }

        showReact();
        checkFinish();
    }

    private void showReact(){
        //Show React
        if(!game.getIdReact().equals(repository.getUser().getUid())){
            switch (game.getReact()) {
                case Constants.LOVE:
                    Toast.makeText(this, "Love", Toast.LENGTH_SHORT).show();
                    repository.updateGame(game.getId(), Constants.REACT,"");
                    break;
                case Constants.LIKE:
                    Toast.makeText(this, "Like", Toast.LENGTH_SHORT).show();
                    repository.updateGame(game.getId(), Constants.REACT,"");
                    break;
                case Constants.DISLIKE:
                    Toast.makeText(this, "Dislike", Toast.LENGTH_SHORT).show();
                    repository.updateGame(game.getId(), Constants.REACT,"");
                    break;
            }
        }
    }

    private void checkFinish(){
        //Check exit or no
        switch (game.getStatus()) {
            case Constants.PLAY:
                checkPlay(true);
                break;

            case Constants.WAITING:
                checkPlay(false);
                break;

            case Constants.FINISH:
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.item_win);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.item_bg));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false); //Optional
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

                ImageView win = dialog.findViewById(R.id.win);
                win.setImageResource(R.drawable.logo);
                TextView textView = dialog.findViewById(R.id.congratulations);
                textView.setText(getString(R.string.finish));

                Button game = dialog.findViewById(R.id.newGame);
                Button back = dialog.findViewById(R.id.back);
                game.setVisibility(View.GONE);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PlayWithFriendOnlineActivity.super.onBackPressed();
                        delete = Constants.DELETE;
                    }
                });
                break;
        }
    }

    private void checkPlay(boolean check){
        binding.num1.setClickable(check);
        binding.num2.setClickable(check);
        binding.num3.setClickable(check);
        binding.num4.setClickable(check);
        binding.num5.setClickable(check);
        binding.num6.setClickable(check);
        binding.num7.setClickable(check);
        binding.num8.setClickable(check);
        binding.num9.setClickable(check);
    }

    private void setImage(int numOfButton, int drawable){
        if(numOfButton == 1)
            binding.num1.setImageResource(drawable);
        else if(numOfButton == 2)
            binding.num2.setImageResource(drawable);
        else if(numOfButton == 3)
            binding.num3.setImageResource(drawable);
        else if(numOfButton == 4)
            binding.num4.setImageResource(drawable);
        else if(numOfButton == 5)
            binding.num5.setImageResource(drawable);
        else if(numOfButton == 6)
            binding.num6.setImageResource(drawable);
        else if(numOfButton == 7)
            binding.num7.setImageResource(drawable);
        else if(numOfButton == 8)
            binding.num8.setImageResource(drawable);
        else if(numOfButton == 9)
            binding.num9.setImageResource(drawable);
    }

    private void setBackground(int numOfButton, int drawable){
        if(numOfButton == 1)
            binding.num1.setBackgroundResource(drawable);
        else if(numOfButton == 2)
            binding.num2.setBackgroundResource(drawable);
        else if(numOfButton == 3)
            binding.num3.setBackgroundResource(drawable);
        else if(numOfButton == 4)
            binding.num4.setBackgroundResource(drawable);
        else if(numOfButton == 5)
            binding.num5.setBackgroundResource(drawable);
        else if(numOfButton == 6)
            binding.num6.setBackgroundResource(drawable);
        else if(numOfButton == 7)
            binding.num7.setBackgroundResource(drawable);
        else if(numOfButton == 8)
            binding.num8.setBackgroundResource(drawable);
        else if(numOfButton == 9)
            binding.num9.setBackgroundResource(drawable);
    }

    private void checkWin(){
        if(players[0].equals(Constants.Y) && players[1].equals(Constants.Y) && players[2].equals(Constants.Y) && move[1]==1 && move[2]==2 & move[3]==3)
            win(Constants.Y, 0, 1, 2);
        else if(players[3].equals(Constants.Y) && players[4].equals(Constants.Y) && players[5].equals(Constants.Y) )
            win(Constants.Y, 3, 4, 5);
        else if(players[6].equals(Constants.Y) && players[7].equals(Constants.Y) && players[8].equals(Constants.Y) )
            win(Constants.Y, 6, 7, 8);
        else if(players[0].equals(Constants.Y) && players[3].equals(Constants.Y) && players[6].equals(Constants.Y) )
            win(Constants.Y, 0, 3, 6);
        else if(players[1].equals(Constants.Y) && players[4].equals(Constants.Y) && players[7].equals(Constants.Y) )
            win(Constants.Y, 1, 4, 7);
        else if(players[2].equals(Constants.Y) && players[5].equals(Constants.Y) && players[8].equals(Constants.Y) )
            win(Constants.Y, 2, 5, 8);
        else if(players[0].equals(Constants.Y) && players[4].equals(Constants.Y) && players[8].equals(Constants.Y) )
            win(Constants.Y, 0, 4, 8);
        else if(players[2].equals(Constants.Y) && players[4].equals(Constants.Y) && players[6].equals(Constants.Y) )
            win(Constants.Y, 2, 4, 6);


        else if(players[0].equals(Constants.N) && players[1].equals(Constants.N) && players[2].equals(Constants.N))
            win(Constants.N, 0, 1, 2);
        else if(players[3].equals(Constants.N) && players[4].equals(Constants.N) && players[5].equals(Constants.N) )
            win(Constants.N, 3, 4, 5);
        else if(players[6].equals(Constants.N) && players[7].equals(Constants.N) && players[8].equals(Constants.N) && move[7]==7 && move[8]==8 && move[9]==9)
            win(Constants.N, 6, 7, 8);
        else if(players[0].equals(Constants.N) && players[3].equals(Constants.N) && players[6].equals(Constants.N) )
            win(Constants.N, 0, 3, 6);
        else if(players[1].equals(Constants.N) && players[4].equals(Constants.N) && players[7].equals(Constants.N) )
            win(Constants.N, 1, 4, 7);
        else if(players[2].equals(Constants.N) && players[5].equals(Constants.N) && players[8].equals(Constants.N) )
            win(Constants.N, 2, 5, 8);
        else if(players[0].equals(Constants.N) && players[4].equals(Constants.N) && players[8].equals(Constants.N) )
            win(Constants.N, 0, 4, 8);
        else if(players[2].equals(Constants.N) && players[4].equals(Constants.N) && players[6].equals(Constants.N) )
            win(Constants.N, 2, 4, 6);

    }

    private void win(String winPlayer, int position1, int position2, int position3) {
        if(winPlayer.equals(Constants.Y))
            scoreY++;
        else if(winPlayer.equals(Constants.N))
            scoreN++;
        String score=scoreY+ " - "+scoreN;
        binding.score.setText(score);

        setBackground(position1+1, R.drawable.item_bg2);
        setBackground(position2+1, R.drawable.item_bg2);
        setBackground(position3+1, R.drawable.item_bg2);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_win);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.item_bg));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        TextView textView =dialog.findViewById(R.id.congratulations);
        String text = getString(R.string.congratulations ) +" "+ winPlayer;
        textView.setText(text);

        Button game= dialog.findViewById(R.id.newGame);
        Button back= dialog.findViewById(R.id.back);

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWithFriendOnlineActivity.super.onBackPressed();
            }
        });

        dialog.show();
    }

    private void newGame() {
        repository.updatePlay(game.getId(), 0);
    }


    private void showChat() {
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog( this,R.style.bottomTheme );
        View bottom= LayoutInflater.from( this ).inflate( R.layout.bottom_about_chats,findViewById( R.id.containerParticipants ) );
        bottomSheetDialog.setContentView( bottom );
        RecyclerView recyclerView =bottom.findViewById(R.id.chatsRecyclerView);
        chats = repository.getChats(game.getId(), this);
        chatsAdapter =new ChatsAdapter(getApplicationContext(), chats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(chatsAdapter);

        EditText message= bottom.findViewById(R.id.writeMessage);
        FloatingActionButton send= bottom.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(message.getText().toString().equals(""))
                    message.requestFocus();
                else {
                    Chat chat = new Chat();
                    chat.setName(repository.getUser().getDisplayName());
                    //chat.setProfile(repository.getUser().getPhotoUrl().toString());
                    chat.setProfile("hh");
                    chat.setMessage(message.getText().toString());
                    repository.createNewChat(game.getId(), chat);
                    message.setText("");
                }
            }
        });
        bottomSheetDialog.show();
    }

    private void showParticipants() {
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog( this,R.style.bottomTheme );
        View bottom= LayoutInflater.from( this ).inflate( R.layout.bottom_about_participants,findViewById( R.id.containerParticipants ) );
        bottomSheetDialog.setContentView( bottom );
        RecyclerView recyclerView =bottom.findViewById(R.id.participantsRecyclerView);
        users = repository.getUsers(game.getId(),this);
        participantsAdapter = new ParticipantsAdapter(getApplicationContext(), users, game);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(participantsAdapter);
        bottomSheetDialog.show();
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void getChats(List<Chat> chats1) {
        Toast.makeText(this, chats.toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, chats1.toString(), Toast.LENGTH_SHORT).show();
        chats = chats1;
        chatsAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void getParticipants(List<User> participants) {
//        users.clear();
        users = participants;
        participantsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(delete.equals("")) {
            if(repository.getUser().getUid().equals(game.getPlayer1()) || repository.getUser().getUid().equals(game.getPlayer1())) {
                repository.updateGame(game.getId(), Constants.STATUS, Constants.FINISH);
                repository.deleteOneParticipant(game.getId(),repository.getUser().getUid());
            }
        }
        else {
            if(users.size()==1) {
                repository.deleteGame(game.getId());
                repository.deleteChat(game.getId());
                repository.deleteParticipants(game.getId());
            }
        }
    }
}
