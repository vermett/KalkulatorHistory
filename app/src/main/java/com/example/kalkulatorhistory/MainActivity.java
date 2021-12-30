package com.example.kalkulatorhistory;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    TextView hasil;
    RadioButton radioButton;
    RadioGroup radioGroup;
    Button hitung;

    SharedPreferences pref;
    Gson gson;
    RecyclerView rec_history;
    ArrayList<History> mHistory;
    HAdapter HAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rec_history = findViewById(R.id.rec_history);

        rec_history.setLayoutManager(new LinearLayoutManager(this));

        pref = this.getSharedPreferences(getString(R.string.SHARE_KEY), Context.MODE_PRIVATE);
        gson = new GsonBuilder().create();

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        hasil = findViewById(R.id.hasil);
        radioGroup = findViewById(R.id.radioGroup);
        hitung = findViewById(R.id.hitung);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hasil();

                String operasi = new String();
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                if(radioButton.getText().equals("Tambah")){
                    operasi = "+";
                }
                if(radioButton.getText().equals("Kurang")){
                    operasi = "-";
                }
                if(radioButton.getText().equals("Kali")){
                    operasi = "*";
                }
                if(radioButton.getText().equals("Bagi")){
                    operasi = "/";
                }

                String recordList = pref.getString(getString(R.string.RECORD_LIST), "[]");
                mHistory = gson.fromJson(recordList, new TypeToken<ArrayList<History>>(){}.getType());
                if (mHistory == null) mHistory = new ArrayList<>();

                mHistory.add(new History(num1.getText().toString(), num2.getText().toString(), hasil.getText().toString(), operasi));

                recordList = gson.toJson(mHistory);
                pref.edit().putString(getString(R.string.RECORD_LIST), recordList).apply();

                HAdapter = new HAdapter(mHistory);
                rec_history.setAdapter(HAdapter);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // hapus saat resume
        Delete();
    }

    // menghapus data
    private void Delete(){
        SharedPreferences preferences = getSharedPreferences("History", 0);
        preferences.edit().remove("RecordList").commit();
    }

    private void Tambah(){
        float a = Float.parseFloat(num1.getText().toString());
        float b = Float.parseFloat(num2.getText().toString());

        float hasil = a + b;
        Test(hasil);
    }

    private void Kurang(){
        float a = Float.parseFloat(num1.getText().toString());
        float b = Float.parseFloat(num2.getText().toString());

        float hasil = a - b;
        Test(hasil);
    }

    private void Kali(){
        float a = Float.parseFloat(num1.getText().toString());
        float b = Float.parseFloat(num2.getText().toString());

        float hasil = a * b;
        Test(hasil);
    }

    private void Bagi(){
        float a = Float.parseFloat(num1.getText().toString());
        float b = Float.parseFloat(num2.getText().toString());

        float hasil = a / b;
        Test(hasil);
    }

    private void Test(float total){
        int cek = (int) total;

        if(cek == total){
            hasil.setText(String.valueOf((int) total));
        }
        else{
            hasil.setText(String.valueOf(total));
        }
    }

    public void Hasil(){
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.tambah:
                Tambah();
                break;
            case R.id.kurang:
                Kurang();
                break;
            case R.id.kali:
                Kali();
                break;
            case R.id.bagi:
                Bagi();
                break;
        }
    }

    public void CheckButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Pilih: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

}