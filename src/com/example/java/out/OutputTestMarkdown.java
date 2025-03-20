package com.example.java.out;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java.model.test.Test;

public class OutputTestMarkdown extends AbstractOutputTestFile {

    public OutputTestMarkdown(boolean sameFile) {
        super(".md");
        this.sameFile = sameFile;
    }

    @Override
    public List<String> formatTest(Test test) {
        List<String> result = new ArrayList<>();

        result.add("# Test: %s (nº %d)".formatted(test.getNombre(), test.getId()));
        result.add("");
        result.add("### Preguntas");
        result.add("");
        test.getPreguntas().forEach(pregunta -> {
            result.add("%d. %s".formatted(pregunta.getId(), pregunta.getTexto()));
            result.add("");
            pregunta.getRespuestas()
                .forEach(respuesta -> {
                    result.add("- [ ] %s) %s".formatted(respuesta.getId(), respuesta.getTexto()));
            });
            result.add("");
        });
        result.add("");
        return result;
    }

    @Override
    public List<String> formatCorreccion(Test test) {
        List<String> result = new ArrayList<>();

        result.add("# Test: %s (nº %d)".formatted(test.getNombre(), test.getId()));
        result.add("");
        result.add("### Respuestas Correctas");
        result.add("");
        test.getPreguntas().forEach(pregunta -> {
            result.add("%d. %s".formatted(pregunta.getId(), pregunta.getIdsRespuestasCorrectas()
                .stream()
                .collect(Collectors.joining(", "))));
        });
        result.add("");
        
        return result;
    }

}
