package com.example.aitor.projectefinal_aitorgallardo;

import java.util.ArrayList;

public enum TipoLugar {
    OTROS("Otros", 6),
    RESTAURANTE("Restaurante", 5),
    BAR("Bar", 2),
    COPAS("Copas", 3),
    ESPECTACULO("Espectáculo", 4),
    HOTEL("Hotel", 1),
    COMPRAS("Compras", 0),
    EDUCACION("Educación", 7),
    DEPORTE("Deporte", 8),
    NATURALEZA("Naturaleza", 9),
    GASOLINERA("Gasolinera", 10);
    private final String texto;
    private final int value;
    TipoLugar(String texto, int value) {
        this.texto = texto;
        this.value = value;
    }

    public String getTexto() { return texto; }
    public int getValue() { return value; }

    public static TipoLugar findLugarbyValue(int value){
        TipoLugar[] listOfTypes =  TipoLugar.values();

        for(TipoLugar eachType: listOfTypes){
            if(value == eachType.getValue()){
                return eachType;
            }
        }
        return null;
    }
}
