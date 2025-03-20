package com.example.java.out;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java.model.test.Test;

public class OutputTestConsole implements OutputTest {

    @Override
    public void outputTest(Test test) throws Exception {
        formatTest(test)
            .forEach(System.out::println);
    }

    @Override
    public void outputCorreccion(Test test) throws Exception {
        formatCorreccion(test)
            .forEach(System.out::println);
    }

    @Override
    public void outputTests(List<Test> testList) throws Exception {
        for (Test test : testList) {
            outputTest(test);
        }
    }

    @Override
    public void outputCorrecciones(List<Test> testList) throws Exception {
        for (Test test : testList) {
            outputCorreccion(test);
        }
    }

    @Override
    public List<String> formatTest(Test test) {
        List<String> result = new ArrayList<>();

        result.add("Test: %s (nº %d) ".formatted(test.getNombre(), test.getId()));
        result.add("Curso: " + test.getCurso());
        result.add("=====================================");
        result.add("");
        result.add("PREGUNTAS");
        result.add("-------------------------------------");
        result.add("");
        test.getPreguntas()
            .forEach(pregunta -> {
                result.add("%d. %s".formatted(pregunta.getId(), pregunta.getTexto()));
                result.add("");
                pregunta.getRespuestas()
                    .forEach(respuesta -> {
                        result.add("  %s %s".formatted(respuesta.getId(), respuesta.getTexto()));
                    });
                result.add("");
            });
        
        return result;
    }

    @Override
    public List<String> formatCorreccion(Test test) {
        List<String> result = new ArrayList<>();

        result.add("Respuestas correctas");
        result.add("====================");
        test.getPreguntas()
                .forEach(pregunta -> 
                    result.add("%d %s".formatted(pregunta.getId(),
                        pregunta.getIdsRespuestasCorrectas()
                        .stream()
                        .collect(Collectors.joining(", ")))));

        return result;
    }

}
