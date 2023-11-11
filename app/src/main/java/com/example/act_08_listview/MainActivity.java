package com.example.act_08_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText numero1, numero2;
    private Button calcular;
    private ListView listView;
    private ArrayList<String> resultList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero2 = findViewById(R.id.tfNum1);
        numero1 = findViewById(R.id.tfNum2);
        calcular = findViewById(R.id.btnCalcular);
        listView = findViewById(R.id.listView);

        resultList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = adapter.getItem(position);
                showToast(selectedItem);
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void calculate() {
        String num1Str = numero1.getText().toString();
        String num2Str = numero2.getText().toString();

        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = num1 + num2;

            String calculation = num1Str + " + " + num2Str + " = " + result;
            resultList.add(calculation);
            adapter.notifyDataSetChanged();

            numero1.setText("");
            numero2.setText("");

            hideKeyboard();
            showToast("Suma realizada Correctamente.");
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}