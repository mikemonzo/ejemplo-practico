package com.example.java.process;

import com.example.java.model.plantilla.PlantillaPregunta;
import com.example.java.model.test.Pregunta;
import com.example.java.model.test.Respuesta;

public class PreguntaMapper {

    public Pregunta mapPlantillaPreguntaToPregunta(PlantillaPregunta plantillaPregunta) {
        return mapPlantillaPreguntaToPregunta(1, plantillaPregunta);
    }

    public Pregunta mapPlantillaPreguntaToPregunta(int id, PlantillaPregunta plantillaPregunta) {
        Pregunta pregunta = new Pregunta(id, plantillaPregunta.getTexto());

        plantillaPregunta.getRespuestasCorrectas()
        .stream()
        .map(pc -> Respuesta.of(pc, true))
        .forEach(pregunta::addRespuesta);

        plantillaPregunta.getRespuestasIncorrectas()
        .stream()
        .map(pi -> Respuesta.of(pi, false))
        .forEach(pregunta::addRespuesta);

        pregunta.desordenarRespuestas();

        return pregunta;
    }
}
