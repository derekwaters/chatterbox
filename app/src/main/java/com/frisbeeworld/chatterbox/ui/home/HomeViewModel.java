package com.frisbeeworld.chatterbox.ui.home;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HomeViewModel extends ViewModel {

    private String TAG = HomeViewModel.class.getSimpleName();

    private MutableLiveData<String> mMyName;
    private MutableLiveData<String> mMessageText;
    private MutableLiveData<List<String>> chatList;

    public HomeViewModel() {
        mMessageText = new MutableLiveData<>();
        mMessageText.setValue("");
        mMyName = new MutableLiveData<>();
        mMyName.setValue("Goose");
    }

    public LiveData<String> getMessageText() {
        return mMessageText;
    }
    public LiveData<String> getMyName() {
        return mMyName;
    }
    public LiveData<List<String>> getChatPosts() {
        if (chatList == null) {
            chatList = new MutableLiveData<>();
            loadDummyChats();
        }
        return chatList;
    }

    public void addChatPost (String user, String msg) {
        List<String> currentChats = chatList.getValue();
        currentChats.add(user + ": " + msg);
        chatList.setValue(currentChats);
        mMessageText.setValue("");
    }

    private void loadDummyChats () {
        // do async operation to fetch users
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            List<String> chatStringList = new ArrayList<>();
            chatStringList.add("Chatterbox: Welcome to the chat.");
            chatList.setValue(chatStringList);
        }, 3000);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }
}