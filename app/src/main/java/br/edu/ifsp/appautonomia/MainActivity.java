package br.edu.ifsp.appautonomia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText gasEditText;
    private EditText etaEditText;
    private Button calcularButton;
    private TextView respostaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();
        setClickListener();
    }

    @Override
    public void onClick(View v){
        if(v.getId()==R.id.button_calcular){
            calcular();
        }
    }

    private void findById(){
        gasEditText = findViewById(R.id.edittext_gasolina);
        etaEditText = findViewById(R.id.edittext_etanol);
        calcularButton = findViewById(R.id.button_calcular);
        respostaTextView = findViewById(R.id.textview_resposta);
    }

    private void setClickListener(){
        calcularButton.setOnClickListener(this);
    }

    private double recuperarValor(EditText input){
        double value = 0;
        try {
            value = Double.valueOf(input.getText().toString());
        }catch (NumberFormatException nfe){
            Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
            value =0;
        }

        return value;
    }

    private void calcular() {

        double value = 0;

        if (etaEditText.getText().toString().isEmpty() ||
                gasEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
            value = 0;
        } else {
            double gas = recuperarValor(gasEditText);
            double eta = recuperarValor(etaEditText);

            double resultado = eta / gas;

            if (resultado < 0.7) {
                respostaTextView.setText(R.string.answer_etanol);
                respostaTextView.setTextColor(getResources().getColor(R.color.etanol, getTheme()));
            } else {
                respostaTextView.setText(R.string.answer_gasolina);
                respostaTextView.setTextColor(getResources().getColor(R.color.gasolina, getTheme()));
            }
        }
    }
}