package com.example.dyetaliacak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private String selectedOgun, selectedYemek;
    private TextView tvOgunSpinner, tvYemekSpinner;
    private Spinner OgunSpinner, YemekSpinner;
    private ArrayAdapter<CharSequence> OgunAdapter, YemekAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Öğün BARI
        OgunSpinner = findViewById(R.id.spinner_ogun_sec);

        //Tanımlamak için arrayadapter kullanacağız
        OgunAdapter = ArrayAdapter.createFromResource(this, R.array.array_ogun_sec, R.layout.spinner_layout);


        //Layout u özelliştirme seçenek listesi çıktığında

        OgunAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Ogunspinnerı bağlama

        OgunSpinner.setAdapter(OgunAdapter);

        //Herhangi bir şey seçildiğinde işleme geçilmesi için OgunSpinner

        OgunSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Öğün spinnerını tanımlıma ama seçilen öğünü
                YemekSpinner = findViewById(R.id.spinner_yemek_sec);

                //Seçilen seçeneği aktif etmek
                selectedOgun = OgunSpinner.getSelectedItem().toString();



                int parentID = parent.getId();
                if (parentID == R.id.spinner_ogun_sec){
                    switch (selectedOgun){
                        case "Öğün Seçiniz": YemekAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_default_yemek, R.layout.spinner_layout);
                        break;

                        case "Kahvaltı": YemekAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_kahvaltı_yemek, R.layout.spinner_layout);
                        break;

                        case "Öğle Yemeği": YemekAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_ogle_yemek, R.layout.spinner_layout);
                        break;

                        case "Akşam Yemeği": YemekAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_aksam_yemek, R.layout.spinner_layout);
                        default: break;
                    }


                    //Seçilen Öğüne göre yemek ayarlama
                    YemekSpinner.setAdapter(YemekAdapter);

                    //Seçilen yemeğe göre uyarlama
                    YemekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedYemek = YemekSpinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    Button submitButton;
                    submitButton = findViewById(R.id.button_submit);
                    tvOgunSpinner = findViewById(R.id.textView_ogun_sec );
                    tvYemekSpinner = findViewById(R.id.textView_yemek_sec);

                    submitButton.setOnClickListener(v -> {
                        if (selectedOgun.equals("Öğününüzü Seçiniz")) {
                            Toast.makeText(MainActivity.this, "Lütfen Öğününüzü Listeden Seçiniz", Toast.LENGTH_LONG).show();
                            tvOgunSpinner.setError("Öğün Seçmeniz Gerekli");
                            tvOgunSpinner.requestFocus();
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }
}