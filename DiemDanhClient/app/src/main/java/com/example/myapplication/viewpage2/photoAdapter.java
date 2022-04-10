package com.example.myapplication.viewpage2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class photoAdapter extends FragmentStateAdapter {

    private List<photo> mListPhoto;

    public photoAdapter(@NonNull FragmentActivity fragmentActivity, List<photo> list) {
        super(fragmentActivity);
        this.mListPhoto = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        photo pt = mListPhoto.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_photo", pt);

        photo_fragment photo_fragment1 = new photo_fragment();
        photo_fragment1.setArguments(bundle);

        return photo_fragment1;
    }

    @Override
    public int getItemCount() {
        if(mListPhoto != null){
            return mListPhoto.size();
        }
        return 0;
    }
}
