package com.miramicodigo.restful.pokeservice;

import com.miramicodigo.restful.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PokeInterface {

    @GET("pokemon")
    Call<PokemonResponse> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

}
