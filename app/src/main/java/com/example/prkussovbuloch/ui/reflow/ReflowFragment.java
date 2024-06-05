package com.example.prkussovbuloch.ui.reflow;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prkussovbuloch.databinding.FragmentReflowBinding;
import com.example.prkussovbuloch.ui.settings.thadachi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReflowFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<String> listDataSave;
    private FragmentReflowBinding binding;

    private DatabaseReference mDataBase2;
    private String comp = "Complite";
    private TextView emptyView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReflowViewModel reflowViewModel =
                new ViewModelProvider(this).get(ReflowViewModel.class);

        binding = FragmentReflowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Activity activity = requireActivity();
        EditText searchField = binding.searchField;

        emptyView = binding.emptyView;
        listView = binding.ComList;
        listData = new ArrayList<>();
        listDataSave = new ArrayList<>();

        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        mDataBase2 = FirebaseDatabase.getInstance().getReference(comp);

        getDataBD();
        listDataSave = listData;

        searchField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Фильтруйте список элементов здесь

                if(s.toString().isEmpty()){

                    adapter.addAll(listData);
                }
                else {
                    String searchText = s.toString();
                    Filter userFilter = adapter.getFilter();
                    adapter.getFilter().filter(searchText);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        return root;
    }


    private void getDataBD(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 ) listData.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String quest = ds.getValue(String.class);

                    assert quest != null;
                    listData.add(quest);

                }
                adapter.notifyDataSetChanged();

                if (listView.getAdapter().getCount() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    emptyView.setVisibility(View.GONE) ;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        mDataBase2.addValueEventListener(vListener);

    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}