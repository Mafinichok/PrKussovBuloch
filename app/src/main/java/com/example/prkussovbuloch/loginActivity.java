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
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText edLogin, edPass, edPass2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });







        edLogin = findViewById(R.id.edLogin);
        edPass = findViewById(R.id.edPass);
        mAuth = FirebaseAuth.getInstance();


        Button rega = findViewById(R.id.REGA);
        rega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextActivity2(v);

            }
        });






        Button toNextact = findViewById(R.id.gonext);

        toNextact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPass.getText().toString())){
                    mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "User SingIp Suc", Toast.LENGTH_SHORT).show();
                                onNextActivity(v);
                            } else {
                                Toast.makeText(getApplicationContext(), "User SingIp not", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "Пустые поля", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }


    protected void onStart(){
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser != null){
            //Toast.makeText(getApplicationContext(), "ТЫ ВОШЕЛ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        else{
            //Toast.makeText(getApplicationContext(), "Ты НЕ ВОШЁЛ", Toast.LENGTH_SHORT).show();
        }
    }





    public void onNextActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onNextActivity2(View view){
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
    }



}