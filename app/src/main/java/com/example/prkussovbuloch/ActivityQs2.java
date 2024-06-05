package com.example.prkussovbuloch;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prkussovbuloch.ui.settings.thadachi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class ActivityQs2 extends AppCompatActivity {

    private Drawable[] pictures;

    private DatabaseReference mDataBase1;
    private String Qs = "qs";


    private int Counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qs2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView prinimay = findViewById(R.id.nazvan);

        ImageView FOTO = findViewById(R.id.FOTO);

        TypedArray ta = getResources().obtainTypedArray(R.array.random_images);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }

        Bundle arg = getIntent().getExtras();
        if(arg != null){

          String name = arg.get("item").toString();
          prinimay.setText(name);

          int position = arg.getInt("pictures");

            Drawable id = null;

            if(position < icons.length) {
                id = icons[position];
            }

            if(id != null) {

                FOTO.setImageDrawable(id);
            }

        }
        List<String> recept = Arrays.asList(getResources().getStringArray(R.array.recepts));
        int position1 = arg.getInt("pictures");
        String item = recept.get(position1);
        TextView recpt = findViewById(R.id.textRep);
        recpt.setText(item);
        Counter = 0;


        TextView koll = findViewById(R.id.schetchik);
        Button pluss = findViewById(R.id.pluss);
        Button mins = findViewById(R.id.minus);



        pluss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Counter = Counter + 1;
                koll.setText(String.valueOf(Counter));
            }
        });

        mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Counter != 0) {
                    Counter = Counter - 1;
                    koll.setText(String.valueOf(Counter));
                }
            }
        });


        Button dobavit = findViewById(R.id.dobQs);

        mDataBase1 = FirebaseDatabase.getInstance().getReference(Qs);

        dobavit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mDataBase1.getKey();
                String kol = koll.getText().toString();
                String name = arg.get("item").toString();
                thadachi newQs = new thadachi(name, kol);
                mDataBase1.push().setValue(newQs);
                onNextActivity(v);

            }
        });

    }

    public void onNextActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}