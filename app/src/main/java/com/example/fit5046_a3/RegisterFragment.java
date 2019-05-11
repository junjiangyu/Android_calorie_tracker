package com.example.fit5046_a3;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterFragment extends android.support.v4.app.Fragment {
   private EditText dob;
   private RadioGroup gender;
   private RadioButton genderSelected;
   private Spinner spinner;

    String fnameinput;
    String snameinput;
    String emailinput;
    String weightinput;
    String heightinput;
    String genderinput;
    String loa;
    String addressinput;
    String postcodeinput;
    String spminput;
    String usernameinput;
    String pwdinput;

   Date dobinput;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view=inflater.inflate(R.layout.fragment_register,null);

        final EditText fname = (EditText) view.findViewById(R.id.input_fn);
        final EditText sname = (EditText) view.findViewById(R.id.input_sn);
        final EditText email = (EditText) view.findViewById(R.id.input_email);
        dob = (EditText) view.findViewById(R.id.input_dob);
        final EditText weight = (EditText) view.findViewById(R.id.weight);
        final EditText height = (EditText) view.findViewById(R.id.height);
         gender = (RadioGroup)view.findViewById(R.id.radiogroup);
         spinner = (Spinner) view.findViewById(R.id.input_levelofact);
         final EditText address = (EditText) view.findViewById(R.id.address);
        final EditText postcode = (EditText) view.findViewById(R.id.postcode);
        final EditText spm = (EditText) view.findViewById(R.id.stepspermile);
        final EditText username = (EditText) view.findViewById(R.id.Username);
        final EditText pwd = (EditText) view.findViewById(R.id.Password);

        Button regBtn = (Button) view.findViewById(R.id.btn_register);

        // Creating adapter for spinner
        ArrayAdapter<CharSequence> dataAdapter =ArrayAdapter.createFromResource(getActivity(), R.array.levelofactivity,android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);




        dob.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });




        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fnameinput = fname.getText().toString();
                snameinput = sname.getText().toString();
                emailinput = email.getText().toString();
                weightinput = weight.getText().toString();
                heightinput = height.getText().toString();
                genderinput = "";
                loa = spinner.getSelectedItem().toString();
                addressinput = address.getText().toString();
                postcodeinput = postcode.getText().toString();
                spminput = spm.getText().toString();
                usernameinput = username.getText().toString();
                pwdinput = pwd.getText().toString();


               //get the value from radio box
                int selectedId = gender.getCheckedRadioButtonId();
                if (selectedId == -1){
                    Toast.makeText(getActivity(),
                            "Radio Box unchecked, Try again", Toast.LENGTH_SHORT).show();
                }
                else {
                genderSelected = (RadioButton) view.findViewById(selectedId);
                 genderinput = genderSelected.getText().toString();
                }

                if(!fnameinput.matches("^[A-Za-z]+$")){
                    fname.setError("Firstname input incorrect");

                }
                if(!snameinput.matches("^[A-Za-z]+$")){
                    sname.setError("Lastname input incorrect");
                }
                if(checkEmail(emailinput)== false){
                    email.setError("Email input incorrect");
                }
                if (dobinput == null){
                    Toast.makeText(getActivity(),
                            "Date of birth not selected, try again", Toast.LENGTH_SHORT).show();
                }
                if(!weightinput.matches("^[0-9]*$") || weightinput.equals("")){
                    weight.setError("Weight input incorrect");
                }
                if(!heightinput.matches("^[0-9]*$") || heightinput.equals("")){
                    height.setError("Height input incorrect");
                }




                PostAsyncTask postAsyncTask=new PostAsyncTask();
                postAsyncTask.execute(fnameinput,snameinput);


            }


        });



        return view;
    }

    public boolean checkEmail(CharSequence email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    protected void showDatePickDlg(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Becasue the month start at 0, so we need to add 1 into the right
                String datestr = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
                dob.setText(datestr);
                //transform string date into date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dobinput = format.parse(datestr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH +1), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    private class PostAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params) {
            User user = new User(fnameinput,snameinput,emailinput,dobinput,heightinput,weightinput,genderinput,addressinput,postcodeinput,loa,spminput);
            RestClient.createAccount(user);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSSXXX").create();
            String userJson = gson.toJson(user);
            return userJson;
        }
        @Override
        protected void onPostExecute(String response) {

            Toast.makeText(getActivity(),
                    response, Toast.LENGTH_SHORT).show();

        }
    }






}
