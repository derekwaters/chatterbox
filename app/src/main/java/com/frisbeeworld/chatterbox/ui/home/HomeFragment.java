package com.frisbeeworld.chatterbox.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.frisbeeworld.chatterbox.R;
import com.frisbeeworld.chatterbox.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView nameView = binding.editName;
        homeViewModel.getMyName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                nameView.setText(s);
            }
        });
        final TextView messageView = binding.editMessage;
        homeViewModel.getMessageText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                messageView.setText(s);
            }
        });

        final ListView listView = binding.listChats;
        homeViewModel.getChatPosts().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            // update UI
            @Override
            public void onChanged(@Nullable List<String> l) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, l);
                listView.setAdapter(adapter);
            }
        });


        final Button postButton = binding.btnPost;
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myName = nameView.getText().toString();
                String message = messageView.getText().toString();

                homeViewModel.addChatPost(myName, message);
            }
        });





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}