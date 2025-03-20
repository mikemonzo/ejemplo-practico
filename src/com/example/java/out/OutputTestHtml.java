package com.example.java.out;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java.model.test.Test;

public class OutputTestHtml extends AbstractOutputTestFile {

    private static final List<String> INICIO = List.of(
        "<html>",
        "<body>"
    );
    private static final List<String> FIN = List.of(
        "</body>",
        "</html>"
    );

    public OutputTestHtml(boolean sameFile) {
        super(".html");
        this.sameFile = sameFile;
    }

    @Override
    public List<String> formatTest(Test test) {
        List<String> result = new ArrayList<>();

        result.addAll(INICIO);
        result.add("<h1>Rest: %s (nº %d)</h1>".formatted(test.getNombre(), test.getId()));
        result.add("");
        result.add("<h3>Enunciado</h3>");
        result.add("<br/>");
        result.add("<ol>");
        test.getPreguntas()
            .forEach(pregunta -> {
                result.add("<li>%s".formatted(pregunta.getTexto()));
                result.add("<ul>");
                pregunta.getRespuestas()
                    .forEach(respuesta -> 
                        result.add("<li>%s) %s</li>".formatted(respuesta.getId(), respuesta.getTexto()))
                    );
                result.add("</ul>");
                result.add("</li>");
                result.add("<br/><br/>");
            });
            result.add("</ol>");
            result.add("<br/>");
            result.addAll(FIN);

            return result;
    }

    @Override
    public List<String> formatCorreccion(Test test) {
        List<String> result = new ArrayList<>();

        result.addAll(INICIO);
        result.add("<h1>Test: %s (nº %d)</h1>".formatted(test.getNombre(), test.getId()));
        result.add("");
        result.add("<h3>Respuestas Correctas</h3>");
        result.add("<br/>");
        result.add("<ul>");

        test.getPreguntas()
            .forEach(pregunta -> {
                result.add("<li>%d. %s</li>".formatted(pregunta.getId(), 
                pregunta.getIdsRespuestasCorrectas()
                    .stream()
                    .collect(Collectors.joining(", "))));});
            result.add("</ul>");
            result.add("<br/>");
            result.addAll(FIN);

            return result;
    }

}
