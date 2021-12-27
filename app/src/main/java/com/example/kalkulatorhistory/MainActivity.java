package com.example.kalkulatorhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView hasil;
    EditText num1, num2;
    RadioButton tambah, kurang, kali, bagi;
    Button hitung;
    CheckBox check;

    int angka1, angka2;
    float total;

    private ArrayAdapter<String> mHistory;
    private androidx.recyclerview.widget.LinearLayoutManager mLayoutManager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHistory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(mHistory);
        hasil = (TextView) findViewById(R.id.hasil);
        hitung = (Button) findViewById(R.id.hitung);

        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);

        tambah = (RadioButton) findViewById(R.id.tambah);
        kurang = (RadioButton) findViewById(R.id.kurang);
        kali = (RadioButton) findViewById(R.id.kali);
        bagi = (RadioButton) findViewById(R.id.bagi);

        check = (CheckBox) findViewById(R.id.Check);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                angka1 = Integer.parseInt(num1.getText().toString());
                angka2 = Integer.parseInt(num2.getText().toString());

                if (check.isChecked()){
                    if (tambah.isChecked()) {
                        total = angka1 + angka2;
                        hasil.setText(String.valueOf(total));
                        mHistory.add(angka1 + " + " + angka2 +" = "+ total);
                    } else if (kurang.isChecked()) {
                        total = angka1 - angka2;
                        hasil.setText(String.valueOf(total));
                        mHistory.add(angka1 + " - " + angka2 +" = "+ total);
                    } else if (kali.isChecked()) {
                        total = angka1 * angka2;
                        hasil.setText(String.valueOf(total));
                        mHistory.add(angka1 + " * " + angka2 +" = "+ total);
                    } else if (bagi.isChecked()) {
                        total = angka1 / angka2;
                        hasil.setText(String.valueOf(total));
                        mHistory.add(angka1 + " / " + angka2 +" = "+ total);
                    }
                }
                else {
                    if (tambah.isChecked()) {
                        total = angka1 + angka2;
                        hasil.setText(String.valueOf(total));

                    } else if (kurang.isChecked()) {
                        total = angka1 - angka2;
                        hasil.setText(String.valueOf(total));

                    } else if (kali.isChecked()) {
                        total = angka1 * angka2;
                        hasil.setText(String.valueOf(total));

                    } else if (bagi.isChecked()) {
                        total = angka1 / angka2;
                        hasil.setText(String.valueOf(total));
                    }
                }

                    sharedPreferences = getSharedPreferences("angka1", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("angka1", String.valueOf(num1));
                editor.putString("angka2", String.valueOf(num2));
                editor.commit();
                    Toast.makeText(MainActivity.this, "Tersimpan", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }