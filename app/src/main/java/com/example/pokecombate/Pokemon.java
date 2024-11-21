package com.example.pokecombate;

public class Pokemon {
    private String nombre;
    private int hp;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int gif;

    public Pokemon(String nombre, int hp, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int gif) {
        this.nombre = nombre;
        this.hp = Math.max(0, Math.min(999, hp));
        this.ataque = Math.max(0, Math.min(999, ataque));
        this.defensa = Math.max(0, Math.min(999, defensa));
        this.ataqueEspecial = Math.max(0, Math.min(999, ataqueEspecial));
        this.defensaEspecial = Math.max(0, Math.min(999, defensaEspecial));
        this.gif = gif;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public int getHp() { return hp; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAtaqueEspecial() { return ataqueEspecial; }
    public int getDefensaEspecial() { return defensaEspecial; }
    public int getGif() { return gif; }

    public boolean estaVivo() {
        return hp > 0;
    }

    public void recibirAtaque(int danio) {
        this.hp = Math.max(0, this.hp - danio); // Restar HP asegurando que no sea negativo
    }
}
