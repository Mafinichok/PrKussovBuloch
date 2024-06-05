package com.example.prkussovbuloch;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private EditText edLogin, edPass, edPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reg);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edLogin = findViewById(R.id.edLogin3);
        edPass = findViewById(R.id.edPass2);
        edPass2 = findViewById(R.id.edPass3);
        mAuth = FirebaseAuth.getInstance();

        Button toNextact = findViewById(R.id.gonext1);

        toNextact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPass.getText().toString()))
                {
                    if(edPass.getText().toString().equals(edPass2.getText().toString())) {
                        mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(), edPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "User SingUp Suc", Toast.LENGTH_SHORT).show();
                                    onNextActivity(v);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "User SingUp not", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "ОНИ ДОЛЖНЫ СОВПАДАТЬ", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "НАПИШИ ЧТО-НИБУДЬ ЖИВОТНОЕ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button nazad = findViewById(R.id.goback);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextActivity2(v);
            }
        });


    }


    public void onNextActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onNextActivity2(View view){
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }
}