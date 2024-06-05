package com.example.prkussovbuloch.ui.transform;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prkussovbuloch.ActivityQs;
import com.example.prkussovbuloch.R;
import com.example.prkussovbuloch.databinding.FragmentTransformBinding;
import com.example.prkussovbuloch.ui.settings.thadachi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TransformFragment extends Fragment {

    private FragmentTransformBinding binding;

    private FloatingActionButton dobavit;
    private ArrayAdapter<String> adapter;
    private ListView list1;
    private List<String> listQs;
    private  List <thadachi> listTemp;
    private List<String> keys;
    private DatabaseReference mDataBase1;
    private String Qs = "qs";
    private DatabaseReference mDataBase2;
    private String comp = "Complite";
    private TextView emptyView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TransformViewModel transformViewModel =
                new ViewModelProvider(this).get(TransformViewModel.class);

        binding = FragmentTransformBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        dobavit = binding.Dobavit;
        Activity activity = requireActivity();

        dobavit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, ActivityQs.class);
                activity.startActivity(intent);
            }
        });



        emptyView = binding.emptyView;
        list1 = binding.list1;
        listQs = new ArrayList<>();
        listTemp = new ArrayList<>();
        keys = new ArrayList<>();
        adapter = new ArrayAdapter<>(activity,android.R.layout.simple_list_item_1, listQs);
        list1.setAdapter(adapter);
        mDataBase1 = FirebaseDatabase.getInstance().getReference(Qs);
        mDataBase2 = FirebaseDatabase.getInstance().getReference(comp);
        getDataBD();

        String keys;

        setOnClickItem();




        return root;
    }



     // Список ключей элементов


    private void getDataBD(){
        ValueEventListener vListener = new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(listQs.size() > 0 ) listQs.clear();
                    if(listTemp.size() > 0 ) listTemp.clear();



                // Здесь найди ProgressBar или другой виджет для анимации загрузки
                ProgressBar progressBar = binding.loadingIndicator;

                // Покажи или скрой анимацию в зависимости от условий



                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    thadachi quest = ds.getValue(thadachi.class);

                    assert quest != null;
                    listQs.add(quest.naz);
                    listTemp.add(quest);
                    keys.add(ds.getKey());
                }
                adapter.notifyDataSetChanged();
                if (list1.getAdapter().getCount() == 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE) ;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        mDataBase1.addValueEventListener(vListener);

    }

    private void setOnClickItem(){
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                thadachi QS = listTemp.get(position);

                String key = keys.get(position);


                showAlertDialog(QS, key);
            }
        });
    }



    private void showAlertDialog(thadachi QS, String key) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Закрытие задачи");
        builder.setMessage("Вы точно испекли " + QS.kol + " " + QS.naz + "ов");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String ist = (QS.naz + " +" + QS.kol);
                mDataBase2.push().setValue(ist);
                // Удаление элемента
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("qs/" + key);
                ref.removeValue();


                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public class Item {

        private String text;
        private boolean loading;

        public Item(String text, boolean loading) {
            this.text = text;
            this.loading = loading;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isLoading() {
            return loading;
        }

        public void setLoading(boolean loading) {
            this.loading = loading;
        }
    }



}