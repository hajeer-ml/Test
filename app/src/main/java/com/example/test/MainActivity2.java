package com.example.test;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class MainActivity2 extends AppCompatActivity {
        EditText Email , Password;
        Button Register, login ;
        ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        Email =findViewById(R.id.Email);
        Password=findViewById(R.id.Password);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        Register =findViewById(R.id.btnregistre);
        login=findViewById(R.id.btnlogin);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegistrationProccess();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               UserLoginProccese();
                Intent intent= new Intent(MainActivity2.this , recyclerview.class);
                startActivities(new Intent[]{intent});
            }
        });
    }

    private void UserLoginProccese() {
        final String email= Email.getText().toString().trim();
        final String password= Password.getText().toString().trim();

        if( email.isEmpty() || password.isEmpty() ){
            massage("some feilds are empty.. ");
        }else {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.LOGIN_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String result = jsonObject.getString("status");
                        if (result.equals("success")){
                            progressDialog.dismiss();
                            massage("User Login successfully ");
                        }else {
                            progressDialog.dismiss();
                            massage("Login error");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    massage(error.getMessage());
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };
            RequestQueue queue= Volley.newRequestQueue(MainActivity2.this);
            queue.add(stringRequest);
        }
    }

    private void UserRegistrationProccess() {
        LayoutInflater inflater = getLayoutInflater();
        View register_layout = inflater.inflate(R.layout.register_layout, null);
        final EditText Name = register_layout.findViewById(R.id.reg_name);
        final EditText Email = register_layout.findViewById(R.id.reg_email);
        final EditText Phone = register_layout.findViewById(R.id.reg_number);
        final EditText Password = register_layout.findViewById(R.id.reg_password);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        builder.setView(register_layout);
        //

        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressDialog.show();
                final String name = Name.getText().toString().trim();
                final String email = Email.getText().toString().trim();
                final String phone = Phone.getText().toString().trim();
                final String password = Password.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    massage("some feilds are empty.. ");
                    progressDialog.dismiss();
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.REGISTER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            massage(response);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            massage(error.getMessage());
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("name", name);
                            params.put("email", email);
                            params.put("phone", phone);
                            params.put("password", password);
                            return params;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
                    queue.add(stringRequest);
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setTextColor(ContextCompat.getColor(MainActivity2.this, R.color.text));
                positiveButton.setTextSize(18); // تغيير حجم النص
            }
        });
        dialog.show();
    }
    public void massage(String massage){
        Toast.makeText(this,massage ,Toast.LENGTH_LONG).show();
    }
}