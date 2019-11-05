package com.erik.android.androidlean.bean;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class UserModel extends ViewModel {
    private final MutableLiveData<Student> studentMutableLiveData = new MutableLiveData<>();

    public LiveData<Student> getStudent() {
        return studentMutableLiveData;
    }

    public UserModel() {
        //trigger user load.
    }

    void doAction() {
        //depending on the action, do necessary business logic calls and update the userLiveData.
    }
}
