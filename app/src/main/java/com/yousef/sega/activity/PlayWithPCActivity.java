package com.yousef.sega.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.yousef.sega.R;
import com.yousef.sega.databinding.ActivityPlayWithPcBinding;
import com.yousef.sega.model.Constants;
import java.util.Random;

public class PlayWithPCActivity extends AppCompatActivity {

    private ActivityPlayWithPcBinding binding;
    private String player;
    private String[] players;
    private boolean isClick;
    private int  moveMe, movePc, scoreMe, scorePc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayWithPcBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        player = Constants.ME;
        players = new String[]{Constants.ME, Constants.ME, Constants.ME, "", "", "", Constants.PC, Constants.PC, Constants.PC};
        isClick = false;
        moveMe=0;
        movePc=0;
        scoreMe=0;
        scorePc=0;

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


    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            autoPlay();
        }
    };

    private Handler handler=new Handler();

    private void checkPlayer(int numOfButton){
        if(!isClick) {
            if (player.equals(Constants.ME) && players[numOfButton - 1].equals(Constants.ME)) {
                setImage(numOfButton, 0);
                isClick = true;
                players[numOfButton - 1]="";
            }
        }
        else {
            if (player.equals(Constants.ME) && players[numOfButton - 1].equals("")) {
                setImage(numOfButton, R.drawable.me);
                isClick = false;
                players[numOfButton - 1]=Constants.ME;
                player = Constants.PC;
                binding.me.setBackgroundResource(R.drawable.item_bg);
                binding.pc.setBackgroundResource(R.drawable.item_bg2);
                checkWin();
            }
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

    private void autoPlay(){
        int pc[]=new int[3];
        int empty[]=new int[3];
        int j=0, k=0;
        for(int i=0; i<players.length; i++){
            if(players[i].equals(Constants.PC)) {
                pc[j] = i;
                j++;
            }
            else if(players[i].equals("")) {
                empty[k] = i;
                k++;
            }
        }
        j=0;k=0;

        Random random=new Random();
        int val=random.nextInt(3);
        setImage(pc[val]+1, 0);
        players[pc[val]]="";

        Random random2=new Random();
        int val2=random2.nextInt(3);
        setImage(empty[val2]+1, R.drawable.pc);
        players[empty[val2]]=Constants.PC;

        player = Constants.ME;
        binding.me.setBackgroundResource(R.drawable.item_bg2);
        binding.pc.setBackgroundResource(R.drawable.item_bg);
        checkWin();
    }


    private void checkWin(){
        if(players[0].equals(Constants.ME) && players[1].equals(Constants.ME) && players[2].equals(Constants.ME) && moveMe==3)
            win(Constants.ME, 0, 1, 2);
        else if(players[3].equals(Constants.ME) && players[4].equals(Constants.ME) && players[5].equals(Constants.ME) )
            win(Constants.ME, 3, 4, 5);
        else if(players[6].equals(Constants.ME) && players[7].equals(Constants.ME) && players[8].equals(Constants.ME) )
            win(Constants.ME, 6, 7, 8);
        else if(players[0].equals(Constants.ME) && players[3].equals(Constants.ME) && players[6].equals(Constants.ME) )
            win(Constants.ME, 0, 3, 6);
        else if(players[1].equals(Constants.ME) && players[4].equals(Constants.ME) && players[7].equals(Constants.ME) )
            win(Constants.ME, 1, 4, 7);
        else if(players[2].equals(Constants.ME) && players[5].equals(Constants.ME) && players[8].equals(Constants.ME) )
            win(Constants.ME, 2, 5, 8);
        else if(players[0].equals(Constants.ME) && players[4].equals(Constants.ME) && players[8].equals(Constants.ME) )
            win(Constants.ME, 0, 4, 8);
        else if(players[2].equals(Constants.ME) && players[4].equals(Constants.ME) && players[6].equals(Constants.ME) )
            win(Constants.ME, 2, 4, 6);


        else if(players[0].equals(Constants.PC) && players[1].equals(Constants.PC) && players[2].equals(Constants.PC))
            win(Constants.PC, 0, 1, 2);
        else if(players[3].equals(Constants.PC) && players[4].equals(Constants.PC) && players[5].equals(Constants.PC) )
            win(Constants.PC, 3, 4, 5);
        else if(players[6].equals(Constants.PC) && players[7].equals(Constants.PC) && players[8].equals(Constants.PC) && movePc==3)
            win(Constants.PC, 6, 7, 8);
        else if(players[0].equals(Constants.PC) && players[3].equals(Constants.PC) && players[6].equals(Constants.PC) )
            win(Constants.PC, 0, 3, 6);
        else if(players[1].equals(Constants.PC) && players[4].equals(Constants.PC) && players[7].equals(Constants.PC) )
            win(Constants.PC, 1, 4, 7);
        else if(players[2].equals(Constants.PC) && players[5].equals(Constants.PC) && players[8].equals(Constants.PC) )
            win(Constants.PC, 2, 5, 8);
        else if(players[0].equals(Constants.PC) && players[4].equals(Constants.PC) && players[8].equals(Constants.PC) )
            win(Constants.PC, 0, 4, 8);
        else if(players[2].equals(Constants.PC) && players[4].equals(Constants.PC) && players[6].equals(Constants.PC) )
            win(Constants.PC, 2, 4, 6);
        else {
            if (player.equals(Constants.PC))
                handler.postDelayed(runnable, 1000);
        }
    }

    private void win(String winPlayer, int position1, int position2, int position3) {
        if(winPlayer.equals(Constants.ME))
            scoreMe++;
        else if(winPlayer.equals(Constants.PC))
            scorePc++;
        String score=scoreMe+ " - "+scorePc;
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
                PlayWithPCActivity.super.onBackPressed();
            }
        });

        dialog.show();
    }

    private void newGame() {
        setImage(1, R.drawable.me);
        setImage(2, R.drawable.me);
        setImage(3, R.drawable.me);
        setImage(4, 0);
        setImage(5, 0);
        setImage(6, 0);
        setImage(7, R.drawable.pc);
        setImage(8, R.drawable.pc);
        setImage(9, R.drawable.pc);

        setBackground(1,  R.drawable.item_bg);
        setBackground(2,  R.drawable.item_bg);
        setBackground(3,  R.drawable.item_bg);
        setBackground(4,  R.drawable.item_bg);
        setBackground(5,  R.drawable.item_bg);
        setBackground(6,  R.drawable.item_bg);
        setBackground(7,  R.drawable.item_bg);
        setBackground(8,  R.drawable.item_bg);
        setBackground(9, R.drawable.item_bg);

        players = new String[]{Constants.ME, Constants.ME, Constants.ME, "", "", "", Constants.PC, Constants.PC, Constants.PC};
        isClick = false;
        if (player.equals(Constants.PC))
            handler.postDelayed(runnable, 1000);
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
}