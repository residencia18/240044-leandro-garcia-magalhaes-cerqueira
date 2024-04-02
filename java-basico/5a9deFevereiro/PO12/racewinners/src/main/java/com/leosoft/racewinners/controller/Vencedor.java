package com.leosoft.racewinners.controller;

class Vencedor {
    private String pais;
    private String piloto;
    private int vitorias;

    public Vencedor(String pais, String piloto, int vitorias) {
        this.pais = pais;
        this.piloto = piloto;
        this.vitorias = vitorias;
    }

    public String getPais() {
        return pais;
    }

    public String getPiloto() {
        return piloto;
    }

    public int getVitorias() {
        return vitorias;
    }
}
