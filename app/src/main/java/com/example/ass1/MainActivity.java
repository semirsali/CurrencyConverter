package com.example.ass1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;

import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    EditText eInput;
    Button changes;
    Spinner s1;
    Spinner s2;
    String vs1,vs2;
    int i1,i2,i3,i4;
    int counter;
    Double changeToSEK=0.0, changeFromSEK=0.0,inputValue, outputValue;
    TextView tOutput;
    TableLayout tableCurrency;
    Layer c;
    TextView changeCurrency1,changeCurrency2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter=0;
        c= (Layer) findViewById(R.id.c);
        changes = (Button) findViewById(R.id.cambiaschermata);
        eInput = (EditText) findViewById(R.id.in);
        tOutput = (TextView) findViewById(R.id.out);
        tableCurrency = (TableLayout) findViewById(R.id.tabcurr);
        s1 = (Spinner) findViewById(R.id.spinner);
        s2 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currencies, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        s1.setAdapter(adapter);
        s2.setAdapter(adapter);
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
        //working on the button
        changes.setOnClickListener(this);
        eInput.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
          public void afterTextChanged(Editable s) {
              if(s.toString().trim().length()>0) {
                  vs1 = s1.getSelectedItem().toString();
                  vs2 = s2.getSelectedItem().toString();

                  for (int i = 1; i < tableCurrency.getChildCount(); i++) {
                      //qui definisco una riga per ogni I
                      TableRow row = (TableRow) tableCurrency.getChildAt(i);
                      //per ogni riga leggo tutti gli elementi della prima colonna
                      TextView tv = (TextView) row.getChildAt(0);
                      Log.d("myTag", tv.getText()+"i:"+i);

                      if (vs1.contentEquals(tv.getText())) {
                          i1 = i;

                      }
                      if (vs2.contentEquals(tv.getText())) {
                          i2 = i;

                      }

                      TableRow rigas1 = (TableRow) tableCurrency.getChildAt(i1);
                      TableRow rigas2 = (TableRow) tableCurrency.getChildAt(i2);
                      changeCurrency1 = (TextView) rigas1.getChildAt(1);
                      changeCurrency2 = (TextView) rigas2.getChildAt(1);

                  }
                 inputValue = Double.parseDouble(eInput.getText().toString());
                  changeToSEK = inputValue / (Double.parseDouble((String) changeCurrency1.getText()));
                  changeFromSEK = Double.parseDouble((String)changeCurrency2.getText()) * changeToSEK;
                  tOutput.setText(changeFromSEK+"");
                  //tOutput.setText(inputValue+"/"+(Double.parseDouble((String) changeCurrency1.getText()))+"*"+Double.parseDouble((String)changeCurrency2.getText()));
              }
                }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            vs1 = s1.getSelectedItem().toString();
            vs2 = s2.getSelectedItem().toString();
        for (int h = 1; h < tableCurrency.getChildCount(); h++) {
                //qui definisco una riga per ogni I
                TableRow r = (TableRow) tableCurrency.getChildAt(h);
                //per ogni riga leggo tutti gli elementi della prima colonna
                TextView t = (TextView) r.getChildAt(0);
                Log.d("myTag", t.getText()+"i:"+h);

                if (vs1.contentEquals(t.getText())) {
                    i3 = h;

                }
                if (vs2.contentEquals(t.getText())) {
                    i4 = h;

                }

                TableRow riga1 = (TableRow) tableCurrency.getChildAt(i3);
                TableRow riga2 = (TableRow) tableCurrency.getChildAt(i4);
                changeCurrency1 = (TextView) riga1.getChildAt(1);
                changeCurrency2 = (TextView) riga2.getChildAt(1);

            }
            float tot = (float) (changeFromSEK/(Double.parseDouble((String) changeCurrency1.getText()))*Double.parseDouble((String)changeCurrency2.getText()));
            //changeToSEK = inputValue / (
          //  changeFromSEK = Double.parseDouble((String)changeCurrency2.getText()) * changeToSEK;

        tOutput.setText(tot+"");
        }

    @Override
    public void onClick(View v) {
        if(counter==0){
            tableCurrency.setVisibility(View.VISIBLE);
            c.setVisibility(View.GONE);
        counter=1;
        }else{
            tableCurrency.setVisibility(View.GONE);
            c.setVisibility(View.VISIBLE);
            counter=0;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}

