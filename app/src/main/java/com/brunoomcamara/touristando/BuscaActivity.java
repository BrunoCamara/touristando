package com.brunoomcamara.touristando;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;

public class BuscaActivity extends AppCompatActivity {

    Spinner estados;
    Spinner cidades;
    Spinner categoria;
    ArrayList <String> cids;
    int IdEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        // cria a lista(Spnner) no layout
        estados = (Spinner) findViewById(R.id.spinEstados);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.estados, simple_spinner_item);//adapta a lista
        estados.setAdapter(adapter);//adciona a lista a view

        // cria a lista(Spnner) no layout
        categoria = (Spinner) findViewById(R.id.spinCategoria);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this,R.array.categorias, simple_spinner_item);//adapta a lista
        categoria.setAdapter(adapter3);//adciona a lista a view

        estados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pupularSpnnerCidade(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {//cria automatico
            }
        });
    }

    public void pupularSpnnerCidade(int id) {

        cids = obterCidadesPelo(id+1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cids);

        cidades = (Spinner) findViewById(R.id.spinCidades);
        cidades.setAdapter(adapter2);

    }

    public ArrayList<String> obterCidadesPelo(int id) {

        ArrayList <String> cidade = new ArrayList<>();

        switch (id) {
            case 1:
                cidade.add("Rio Branco");
                break;
            case 2:
                cidade.add("Maceió");
                break;
            case 3:
                cidade.add("Macapá");
                break;
            case 4:
                cidade.add("Manaus");
                break;
            case 5:
                cidade.add("Salvador");
                break;
            case 6:
                cidade.add("Fortaleza");
                break;
            case 7:
                cidade.add("Brasília");
                break;
            case 8:
                cidade.add("Vitória");
                break;
            case 9:
                cidade.add("Goiânia");
                break;
            case 10:
                cidade.add("São Luís");
                break;
            case 11:
                cidade.add("Cuiabá");
                break;
            case 12:
                cidade.add("Campo Grande");
                break;
            case 13:
                cidade.add("Belo Horizonte");
                break;
            case 14:
                cidade.add("Belém");
                break;
            case 15:
                cidade.add("João Pessoa");
                cidade.add("Campina Grande");
                cidade.add("Santa Rita");
                break;
            case 16:
                cidade.add("Curitiba");
                break;
            case 17:
                cidade.add("Recife");
                break;
            case 18:
                cidade.add("Teresina");
                break;
            case 19:
                cidade.add("Rio de Janeiro");
                break;
            case 20:
                cidade.add("Natal");
                break;
            case 21:
                cidade.add("Porto Alegre");
                break;
            case 22:
                cidade.add("Porto Velho");
                break;
            case 23:
                cidade.add("Boa Vista");
                break;
            case 24:
                cidade.add("Florianópolis");
                break;
            case 25:
                cidade.add("São Paulo");
                break;
            case 26:
                cidade.add("Aracaju");
                break;
            case 27:
                cidade.add("Palmas");
                break;
        }
        return cidade;
    }
}


