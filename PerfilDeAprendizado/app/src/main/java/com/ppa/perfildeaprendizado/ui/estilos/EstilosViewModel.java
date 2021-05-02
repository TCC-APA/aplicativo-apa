package com.ppa.perfildeaprendizado.ui.estilos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstilosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EstilosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}