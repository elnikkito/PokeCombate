package com.example.pokecombate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CombateViewModel extends ViewModel {
    private final Combate combate; // Referencia al Modelo

    private final MutableLiveData<Pokemon> estadoPokemon1 = new MutableLiveData<>();
    private final MutableLiveData<Pokemon> estadoPokemon2 = new MutableLiveData<>();
    private final MutableLiveData<String> mensajeCombate = new MutableLiveData<>();

    public CombateViewModel() {
        this.combate = new Combate();
        mensajeCombate.setValue(combate.getMensajeCombate());
    }

    // Exponer los datos como LiveData
    public LiveData<Pokemon> getEstadoPokemon1() {
        return estadoPokemon1;
    }

    public LiveData<Pokemon> getEstadoPokemon2() {
        return estadoPokemon2;
    }

    public LiveData<String> getMensajeCombate() {
        return mensajeCombate;
    }

    // Solo inicializa los Pokémon si aún no están configurados
    public void inicializarPokemon1() {
        if (combate.getPokemon1() == null) {
            combate.inicializarPokemon1();  // Solo se llama si el Pokémon no está inicializado
        }
        estadoPokemon1.setValue(combate.getPokemon1());
    }

    public void inicializarPokemon2() {
        if (combate.getPokemon2() == null) {
            combate.inicializarPokemon2();  // Solo se llama si el Pokémon no está inicializado
        }
        estadoPokemon2.setValue(combate.getPokemon2());
    }

    public void atacar() {
        combate.atacar();
        mensajeCombate.setValue(combate.getMensajeCombate());
        estadoPokemon1.setValue(combate.getPokemon1());
        estadoPokemon2.setValue(combate.getPokemon2());
    }
}

