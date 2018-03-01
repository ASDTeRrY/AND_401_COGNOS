package com.miramicodigo.restful;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.miramicodigo.restful.adapter.PokemonAdapter;
import com.miramicodigo.restful.model.Pokemon;
import com.miramicodigo.restful.model.PokemonResponse;
import com.miramicodigo.restful.pokeservice.PokeInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Poke";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private PokemonAdapter listaPokemonAdapter;

    private int offset;

    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaPokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);





        aptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset) {

    }
}
