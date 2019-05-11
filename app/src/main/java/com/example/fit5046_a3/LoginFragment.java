package com.example.fit5046_a3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.*;

import java.text.ParseException;

public class LoginFragment extends android.support.v4.app.Fragment {

    public String username;
    public String password;
    EditText name;
    EditText pwd;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_login,null);

        Button loginBtn = (Button) view.findViewById(R.id.btn_login);
        name = (EditText) view.findViewById(R.id.input_account);
        pwd = (EditText) view.findViewById(R.id.input_password);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = name.getText().toString();
                password = pwd.getText().toString();
                AccountAsyncTask checkAccount = new AccountAsyncTask();
                checkAccount.execute();
            }
        });



        return view;

    }
    private class AccountAsyncTask extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground (Void...params){
            return RestClient.login(username);
        }
        @Override
        protected void onPostExecute (String account){
        if(account.equals("[]")){
            Toast.makeText(getActivity(), "Error password or account combination, plz try again",
                    Toast.LENGTH_LONG).show();
        }
        else{
            String correctpassword = null;
            try {
                JSONArray jsonArray = new JSONArray(account);
                correctpassword = jsonArray.getJSONObject(0).getString("password");
            }  catch (JSONException e) {
                e.printStackTrace();
            }

            if (password.equals(correctpassword)){
                Toast.makeText(getActivity(), "Login successfully",
                        Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(getActivity(), Mainpage.class);
                startActivity(myIntent);
            }
            else {

                Toast.makeText(getActivity(), "Error password or account combination, plz try again",
                        Toast.LENGTH_LONG).show();
            }


        }


        }
    }
}
