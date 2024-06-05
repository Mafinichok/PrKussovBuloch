package com.example.prkussovbuloch.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prkussovbuloch.MainActivity;

public class SettingsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is settings fragment");



    }



    public LiveData<String> getText() {
        return mText;
    }
}