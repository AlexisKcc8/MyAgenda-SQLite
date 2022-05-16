package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.agenda.Open_Helper.OpenHelper;
import com.example.agenda.helper_methods.Helper_methods;
import com.example.agenda.models.Model_user;
import com.example.agenda.pickers.MyPickers;

import java.util.ArrayList;


public class ActivityAdd extends AppCompatActivity implements View.OnClickListener{
    private Context cont=this;
    Spinner categoria;
    EditText txtName, txtLast, txtPhone, etFecha, etHora;
    TextView txtCategoria;
    ImageButton ibObtenerFecha, ibObtenerHora;
    Button btnAdd;
    private ArrayList<String> lista = new ArrayList<String>();

    Model_user user = null;
    OpenHelper myDb = new OpenHelper(ActivityAdd.this);
    Helper_methods myMethods = new Helper_methods();
    MyPickers myPickers = new MyPickers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initXML();

    }
    private void initXML() {
        ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
        ibObtenerFecha.setOnClickListener(this);
        ibObtenerHora = (ImageButton) findViewById(R.id.ib_obtener_hora);
        ibObtenerHora.setOnClickListener(this);

        txtName = (EditText) findViewById(R.id.inpName);
        txtLast = (EditText) findViewById(R.id.inpLast);
        txtPhone = (EditText) findViewById(R.id.inpPhone);
        txtCategoria = (TextView) findViewById(R.id.inpCategoria);
        etFecha = (EditText) findViewById(R.id.inpDate);
        etHora = (EditText) findViewById(R.id.inpHour);

        categoria = (Spinner)findViewById(R.id.categoria);
        myMethods.fillSpinner(ActivityAdd.this, categoria);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    public void clearInpust(){
        txtName.setText("");
        txtLast.setText("");
        txtPhone.setText("");
        etHora.setText("");
        etFecha.setText("");
    }
    @Override
    public void onClick(View view) {
        if(view == ibObtenerFecha){
            myPickers.showDatePickerDialog(ActivityAdd.this, etFecha);
        }
        if(view == ibObtenerHora){
            myPickers.showTimePickerDialog(ActivityAdd.this, etHora);
        }

        if(view == btnAdd){
            user = new Model_user();
            user.setName(txtName.getText().toString().trim());
            user.setLast(txtLast.getText().toString().trim());
            user.setPhone(txtPhone.getText().toString().trim());
            user.setCategoria(categoria.getSelectedItem().toString().trim());
            user.setDate(etFecha.getText().toString().trim());
            user.setTime(etHora.getText().toString().trim());
            myDb.addUser(user);
            clearInpust();
        }
    }

}