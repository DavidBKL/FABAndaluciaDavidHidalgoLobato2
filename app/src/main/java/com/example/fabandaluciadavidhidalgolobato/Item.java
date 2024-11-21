package com.example.fabandaluciadavidhidalgolobato;

public class Item {
    private String texto;
    private int imagenResId;
    private String resumen;

    public Item(String texto, int imagenResId, String resumen) {
        this.texto = texto;
        this.imagenResId = imagenResId;
        this.resumen = resumen;
    }

    public String getTexto() {
        return texto;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public String getResumen() {
        return resumen;
    }
}
