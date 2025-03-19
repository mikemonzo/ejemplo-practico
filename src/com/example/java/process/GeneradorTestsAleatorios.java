package com.example.java.process;

import java.util.List;

import com.example.java.model.plantilla.PlantillaTest;
import com.example.java.model.test.Test;

public interface GeneradorTestsAleatorios {

    Test generarTest(int id, PlantillaTest plantilla, int cantidadPreguntas);
    
    default Test generarTest(PlantillaTest plantilla, int cantidadPreguntas) {
        return generarTest(1, plantilla, cantidadPreguntas);
    }

    default Test generarTest(int id, PlantillaTest plantilla) {
        return generarTest(id, plantilla, plantilla.getPreguntas().size());
    }

    default Test generarTest(PlantillaTest plantilla) {
        return generarTest(1, plantilla);
    }

    default List<Test> generarTests(PlantillaTest plantilla, int cantidadTests, int cantidadPreguntas) {
        List<Test> tests = new java.util.ArrayList<>();
        
        for (int i = 0; i < cantidadTests; i++) {
            tests.add(generarTest(i + 1, plantilla, cantidadPreguntas));
        }
        
        return tests;
    }

    default List<Test> generarTests(PlantillaTest plantilla, int cantidadTests) {
        List<Test> tests = new java.util.ArrayList<>();
        
        for (int i = 0; i < cantidadTests; i++) {
            tests.add(generarTest(i + 1, plantilla));
        }
        
        return tests;
    }
}
