package com.example.java.process;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.java.model.plantilla.PlantillaPregunta;
import com.example.java.model.plantilla.PlantillaTest;
import com.example.java.model.test.Test;

public class GeneradorTestsAleatoriosImpl implements GeneradorTestsAleatorios {

    private static GeneradorTestsAleatoriosImpl INSTANCE ;
    
    private PreguntaMapper preguntaMapper;

    private GeneradorTestsAleatoriosImpl() {
        this.preguntaMapper = new PreguntaMapper();
    }

    public static GeneradorTestsAleatoriosImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GeneradorTestsAleatoriosImpl();
        }
        return INSTANCE;
    }

    @Override
    public Test generarTest(int id, PlantillaTest plantilla, int cantidadPreguntas) {
        if (cantidadPreguntas > plantilla.getPreguntas().size()) {
            throw new IllegalArgumentException("La cantidad de preguntas no puede ser mayor a la cantidad de preguntas de la plantilla");
        }

        Test test = new Test(id, plantilla.getNombre(), plantilla.getCurso());

        List<PlantillaPregunta> preguntas = plantilla.getPreguntas()
            .stream()
            .sorted(Shuffle.suffle())
            .limit(cantidadPreguntas)
            .toList();

        for (int i = 0; i < cantidadPreguntas; i++) {
            test.addPregunta(preguntaMapper.mapPlantillaPreguntaToPregunta(i + 1, preguntas.get(i)));
        }
        
        return test;
    }

    static class Shuffle {
        public static <T> Comparator<T> suffle(){
            final Map<Object, UUID> uniqueIds = new IdentityHashMap<>();
            return Comparator.comparing(
                e -> uniqueIds.computeIfAbsent(e, k -> UUID.randomUUID())
            );
        }
    }

}
