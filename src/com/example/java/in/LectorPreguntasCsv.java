package com.example.java.in;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.example.java.error.CsvTestFormatException;
import com.example.java.model.plantilla.PlantillaPregunta;
import com.example.java.model.plantilla.PlantillaRespuesta;

public class LectorPreguntasCsv implements LectorPreguntas {

    private static final int NUM_TOTAL_RESPUESTAS = 4;
    private static final int NUM_TOTAL_COLS = NUM_TOTAL_RESPUESTAS + 2;

    private static final int COL_TEXTO = 0;
    private static final int COL_NUM_RESPUESTAS = 1;

    private static final int OFFSET_COL_RESPUESTA = 2;
    
    private String path;
    private List<String> content;
    private int preguntaActual;
    private final String separador;

    public LectorPreguntasCsv(String path, String separador) throws IOException {
        this.path = path;
        this.content = Files.readAllLines(Paths.get(path));
        this.preguntaActual = -1;
        this.separador = separador;
    }

    public LectorPreguntasCsv(String path) throws IOException {
        this(path, ";");
    }

    public String getPath() {
        return path;
    }

    @Override
    public PlantillaPregunta leerSiguientePregunta() {
        preguntaActual++;

        if (hayMasPreguntas())
            return procesarPregunta(content.get(preguntaActual), preguntaActual);
        
        return null;
    }

    @Override
    public boolean hayMasPreguntas() {
        return preguntaActual < content.size();
    }

    @Override
    public List<PlantillaPregunta> leerTodasLasPreguntas() {
        List<PlantillaPregunta> result = new ArrayList<>();

        for (int i = 0; i < content.size(); i++) {
            result.add(procesarPregunta(content.get(i), i + 1));
        }

        return null;
    }

    private PlantillaPregunta procesarPregunta(String linea, int id) {
        String[] datos = linea.split(separador);

        if (datos.length != NUM_TOTAL_COLS) {
            throw new CsvTestFormatException("El formato del fichero CSV no es correcto");
        }

        PlantillaPregunta result;
        String texto = datos[COL_TEXTO];
        int numRespuestasCorrectas;

        try {
            numRespuestasCorrectas = Integer.valueOf(datos[COL_NUM_RESPUESTAS]);
        } catch(NumberFormatException e) {
            throw new CsvTestFormatException("El formato del fichero CSV no es correcto");
        }

        result = new PlantillaPregunta(id, texto);

        for (int i = 0; i < NUM_TOTAL_RESPUESTAS; i++) {

            PlantillaRespuesta respuesta = new PlantillaRespuesta(datos[OFFSET_COL_RESPUESTA + i]);

            if (i < numRespuestasCorrectas) {
                result.addRespuestaCorrecta(respuesta);
            } else {
                result.addRespuestaIncorrecta(respuesta);
            }
        }

        return result;
    }
}
