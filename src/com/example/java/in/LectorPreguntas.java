package com.example.java.in;

import java.util.List;

import com.example.java.model.plantilla.PlantillaPregunta;

public interface LectorPreguntas {

    PlantillaPregunta leerSiguientePregunta();

    boolean hayMasPreguntas();

    List<PlantillaPregunta> leerTodasLasPreguntas();
    
}
