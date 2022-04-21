package com.rick.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.rick.notes.activites.MainActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText usernmae,password,repassword;
    Button login,register;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        usernmae=findViewById(R.id.usernameregister);
        password=findViewById(R.id.passwordregister);
        repassword=findViewById(R.id.confirmpassword);
        login=findViewById(R.id.loginregister);
        register=findViewById(R.id.registerregister);
        DB=new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=usernmae.getText().toString();
                String pass=usernmae.getText().toString();
                String repass=usernmae.getText().toString();
                if (user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(RegisterActivity.this, "Fields cant be null", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (pass.equals(repass)){
                        Boolean checkuser=DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if (insert==true){
                                Toast.makeText(RegisterActivity.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "failed to register,try again", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "user is already exist, please login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "password isn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    }
