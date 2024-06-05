package com.example.prkussovbuloch.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prkussovbuloch.MainActivity;
import com.example.prkussovbuloch.R;
import com.example.prkussovbuloch.databinding.FragmentSettingsBinding;
import com.example.prkussovbuloch.loginActivity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {
    private Button gonext;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);


        View view = inflater.inflate(R.layout.fragment_settings, container, false);



        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();





        TextView textView = binding.textSettings;

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        textView.setText(currentUser.getEmail().toString());

        Button vuhodi = binding.Vuhod;

        vuhodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "Успешный выход", Toast.LENGTH_SHORT).show();
                Activity activity = requireActivity();
                Intent intent = new Intent(activity, loginActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });


        return root;
    }

    public void onNextActivity(View view){
        Activity activity = requireActivity();
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("key", "value");
        activity.startActivity(intent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}