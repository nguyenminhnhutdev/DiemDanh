package com.example.myapplication.viewpage2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class photo_fragment extends Fragment {

    private View mView;
    ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_photo, container, false);

        img = (ImageView) mView.findViewById(R.id.img_photo);

        Bundle bundle = getArguments();
        photo photo1 = (photo) bundle.get("object_photo");
        //==========================================
        img.setImageResource(photo1.getRsuorceId());
        return mView;
    }
}
