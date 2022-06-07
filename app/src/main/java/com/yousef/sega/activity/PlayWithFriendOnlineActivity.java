package com.yousef.sega.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yousef.sega.R;
import com.yousef.sega.database.Repository;
import com.yousef.sega.databinding.ActivityPlayWithFriendOnlineBinding;
import com.yousef.sega.model.Constants;
import com.yousef.sega.model.Game;

public class PlayWithFriendOnlineActivity extends AppCompatActivity {

    private ActivityPlayWithFriendOnlineBinding binding;
    private String link;
    private  String id;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayWithFriendOnlineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        link = "https://sega.com/";
        Uri uri = getIntent().getData();
        if(uri != null){
            //get id
            String path = uri.getPath();
            id = path.substring(17, path.length()-1);
        }else {
            Game game = new Game();
            game.setIdPlayer(repository.getUser().getUid());
            game.setPlayer1(repository.getUser().getUid());
            game.setPlayer2("");
            game.setStatus(Constants.PLAY);
            game.setNumber(0);
            game.setReact("");
            id = repository.createNewGame(game);
        }
        link+=id;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.copyLink)
            copyLink();
        if (item.getItemId() == R.id.shareLink)
            shareLink();
        return super.onOptionsItemSelected(item);
    }

    private void copyLink() {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = android.content.ClipData.newPlainText(getString(R.string.app_name), link);
        clipboardManager.setPrimaryClip(clipData);
    }

    private void shareLink() {
        Intent intent =new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, link);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getString(R.string.shareLink)));
    }
}






//URI



//        binding.love.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
