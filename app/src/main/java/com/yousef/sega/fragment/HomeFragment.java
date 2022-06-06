package com.yousef.sega.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.yousef.sega.R;
import com.yousef.sega.activity.PlayWithFriendOnlineActivity;
import com.yousef.sega.activity.PlayWithFriendOfflineActivity;
import com.yousef.sega.activity.PlayWithPCActivity;
import com.yousef.sega.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.newGameWithPC)
            newGameWithPC();
        else  if(view.getId() == R.id.newGameWithFriendOffline)
            newGameWithFriendOffline();
        else  if(view.getId() == R.id.newGameWithFriendOffline)
            newGameWithFriendOnline();
    }

    private void newGameWithPC() {
        Intent intent = new Intent(requireContext(), PlayWithPCActivity.class);
        startActivity(intent);
    }

    private void newGameWithFriendOffline() {
        Intent intent = new Intent(requireContext(), PlayWithFriendOfflineActivity.class);
        startActivity(intent);
    }

    private void newGameWithFriendOnline() {
        Intent intent = new Intent(requireContext(), PlayWithFriendOnlineActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}