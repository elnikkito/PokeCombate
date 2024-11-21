package com.example.pokecombate;

import java.util.Random;

public class Combate {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private String mensajeCombate;
    private boolean turnoPokemon1; // Indica si es el turno de Pokémon 1

    public Combate() {
        this.mensajeCombate = "¡Listos para el combate!";
        this.turnoPokemon1 = true; // Comienza atacando Pokémon 1
    }

    // Inicializar Pokémon
    public void inicializarPokemon1() {
        this.pokemon1 = new Pokemon(
                "Pikachu",
                100,
                50,
                30,
                60,
                40,
                R.drawable.picachu
        );
    }

    public void inicializarPokemon2() {
        this.pokemon2 = new Pokemon(
                "Charmander",
                100,
                55,
                35,
                65,
                45,
                R.drawable.charmander
        );
    }

    // Getters
    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public String getMensajeCombate() {
        return mensajeCombate;
    }

    // Lógica del ataque
    public void atacar() {
        if (pokemon1 == null || pokemon2 == null || !pokemon1.estaVivo() || !pokemon2.estaVivo()) {
            mensajeCombate = "El combate ha terminado.";
            return;
        }

        // Determinar quién ataca
        Pokemon atacante = turnoPokemon1 ? pokemon1 : pokemon2;
        Pokemon defensor = turnoPokemon1 ? pokemon2 : pokemon1;

        // Calcular daño
        int danio = calcularDanio(atacante, defensor);

        // Aplicar daño
        defensor.recibirAtaque(danio);

        // Actualizar mensaje
        if (!defensor.estaVivo()) {
            mensajeCombate = atacante.getNombre() + " ha derrotado a " + defensor.getNombre() + "!";
        } else {
            mensajeCombate = atacante.getNombre() + " atacó a " + defensor.getNombre() + " infligiendo " + danio + " de daño.";
        }

        // Cambiar el turno
        turnoPokemon1 = !turnoPokemon1;
    }

    // Lógica para calcular el daño con probabilidades
    private int calcularDanio(Pokemon atacante, Pokemon defensor) {
        Random random = new Random();
        int danioBase = atacante.getAtaque() - defensor.getDefensa();

        // Aplicar 75% de probabilidad de reducción del daño por defensa
        if (random.nextDouble() <= 0.75) {
            danioBase -= (int) (danioBase * 0.3);  // Reducir el daño en un 30%
        }

        // Aplicar 20% de probabilidad de aplicar ataque especial
        if (random.nextDouble() <= 0.20) {
            danioBase += atacante.getAtaqueEspecial(); // Agregar daño de ataque especial
        }

        // Aplicar 35% de probabilidad de bloquear con defensa especial
        if (random.nextDouble() <= 0.35) {
            danioBase -= defensor.getDefensaEspecial(); // Reducir daño con defensa especial
        }

        // Asegurar que el daño no sea negativo
        return Math.max(1, danioBase);
    }
}
