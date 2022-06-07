package com.yousef.sega.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.yousef.sega.R;
import com.yousef.sega.databinding.ActivityPlayWithFriendOfflineBinding;
import com.yousef.sega.model.Constants;

public class PlayWithFriendOfflineActivity extends AppCompatActivity {

    private ActivityPlayWithFriendOfflineBinding binding;
    private String player;
    private String[] players;
    private boolean isClick;
    private int[] move;
    private int scoreY, scoreN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayWithFriendOfflineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        player = Constants.Y;
        players = new String[]{Constants.Y, Constants.Y, Constants.Y, "", "", "", Constants.N, Constants.N, Constants.N};
        isClick = false;
        move= new int[10];
        scoreY=0;
        scoreN=0;

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

    }


    private void checkPlayer(int numOfButton){
        if(!isClick) {
            if (player.equals(Constants.Y) && players[numOfButton - 1].equals(Constants.Y)) {
                setImage(numOfButton, 0);
                isClick = true;
                players[numOfButton - 1]="";
                if(numOfButton==1)
                    move[1]=1;
                else if(numOfButton==2)
                    move[2]=2;
                else if(numOfButton==3)
                    move[3]=3;
            }
            else if (player.equals(Constants.N) && players[numOfButton - 1].equals(Constants.N)) {
                setImage(numOfButton, 0);
                isClick = true;
                players[numOfButton - 1]="";
                if(numOfButton==7)
                    move[7]=7;
                else if(numOfButton==8)
                    move[8]=8;
                else if(numOfButton==9)
                    move[9]=9;
            }
        }
        else {
            if (player.equals(Constants.Y) && players[numOfButton - 1].equals("")) {
                setImage(numOfButton, R.drawable.y);
                isClick = false;
                players[numOfButton - 1]=Constants.Y;
                player = Constants.N;
                binding.y.setBackgroundResource(R.drawable.item_bg);
                binding.n.setBackgroundResource(R.drawable.item_bg2);
            }
            else if (player.equals(Constants.N) && players[numOfButton - 1].equals("")) {
                setImage(numOfButton, R.drawable.n);
                isClick = false;
                players[numOfButton - 1]=Constants.N;
                player = Constants.Y;
                binding.y.setBackgroundResource(R.drawable.item_bg2);
                binding.n.setBackgroundResource(R.drawable.item_bg);
            }
            checkWin();
        }
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

        Dialog dialog = new Dialog(this);
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
                dialog.dismiss();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWithFriendOfflineActivity.super.onBackPressed();
            }
        });

        dialog.show();
    }

    private void newGame() {
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

    }
}