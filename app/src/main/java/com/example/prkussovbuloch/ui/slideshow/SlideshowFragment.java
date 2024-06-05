package com.example.prkussovbuloch.ui.slideshow;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prkussovbuloch.R;
import com.example.prkussovbuloch.adapterQs;
import com.example.prkussovbuloch.adapterUb;
import com.example.prkussovbuloch.databinding.FragmentSlideshowBinding;

import java.util.Arrays;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Activity activity = requireActivity();

        RecyclerView recyclerView = binding.listdb;
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        List<String> items = Arrays.asList(getResources().getStringArray(R.array.izdelia));

        TypedArray ta = getResources().obtainTypedArray(R.array.random_images);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(activity, id);
            }
        }
        ta.recycle();


        adapterUb adapter1 = new adapterUb(items, icons, getContext());
        recyclerView.setAdapter(adapter1);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}