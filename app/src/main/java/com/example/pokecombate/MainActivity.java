package com.example.pokecombate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private CombateViewModel combateViewModel;

    private TextView estadoPokemon1, estadoPokemon2, mensajeCombate;
    private ImageView imagenPokemon1, imagenPokemon2;
    private Button botonAtacar;
    private TextView estadisticasPokemon1, estadisticasPokemon2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular vistas
        estadoPokemon1 = findViewById(R.id.estadoPokemon1);
        estadoPokemon2 = findViewById(R.id.estadoPokemon2);
        mensajeCombate = findViewById(R.id.mensajeCombate);
        imagenPokemon1 = findViewById(R.id.imagenPokemon1);
        imagenPokemon2 = findViewById(R.id.imagenPokemon2);
        botonAtacar = findViewById(R.id.botonAtacar);
        estadisticasPokemon1 = findViewById(R.id.estadisticasPokemon1);
        estadisticasPokemon2 = findViewById(R.id.estadisticasPokemon2);

        // Obtener el ViewModel
        combateViewModel = new ViewModelProvider(this).get(CombateViewModel.class);


        // Inicializar los Pokémon
        combateViewModel.inicializarPokemon1();
        combateViewModel.inicializarPokemon2();

        // Observar cambios en los datos de los Pokémon
        combateViewModel.getEstadoPokemon1().observe(this, pokemon -> {
            estadoPokemon1.setText(pokemon.getNombre() + " | HP: " + pokemon.getHp());
            estadisticasPokemon1.setText(
                    "Ataque: " + pokemon.getAtaque() +
                            ", Defensa: " + pokemon.getDefensa() +
                            ", At. Esp: " + pokemon.getAtaqueEspecial() +
                            ", Def. Esp: " + pokemon.getDefensaEspecial()
            );
            Glide.with(this).load(pokemon.getGif()).into(imagenPokemon1);
        });

        combateViewModel.getEstadoPokemon2().observe(this, pokemon -> {
            estadoPokemon2.setText(pokemon.getNombre() + " | HP: " + pokemon.getHp());
            estadisticasPokemon2.setText(
                    "Ataque: " + pokemon.getAtaque() +
                            ", Defensa: " + pokemon.getDefensa() +
                            ", At. Esp: " + pokemon.getAtaqueEspecial() +
                            ", Def. Esp: " + pokemon.getDefensaEspecial()
            );
            Glide.with(this).load(pokemon.getGif()).into(imagenPokemon2);
        });


        // Observar cambios en el mensaje del combate
        combateViewModel.getMensajeCombate().observe(this, mensaje -> {
            mensajeCombate.setText(mensaje);
        });

        // Configurar botón de atacar
        botonAtacar.setOnClickListener(v -> combateViewModel.atacar());
    }


}
