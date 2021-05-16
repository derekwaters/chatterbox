package com.frisbeeworld.chatterbox.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mMyName;
    private MutableLiveData<String> mMessageText;

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
}