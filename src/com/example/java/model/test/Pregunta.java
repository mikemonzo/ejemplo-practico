package com.example.java.model.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pregunta {

    private static final String IDS = "abcdefghijklmnopqrstuvwxyz";

    private int id;
    private String texto;
    private List<Respuesta> respuestas;
    private List<String> idsRespuestasCorrectas;

    public Pregunta() {
        respuestas = new ArrayList<>();
        idsRespuestasCorrectas = new ArrayList<>();
    }

    public Pregunta(int id, String texto) {
        this();
        this.id = id;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto){
        this.texto = texto;
    }

    public List<Respuesta> getRespuestas(){
        return Collections.unmodifiableList(respuestas);
    }

    public void addRespuesta(Respuesta respuesta){
        this.respuestas.add(respuesta);
        if (respuesta.isEsCorrecta()) {
            this.idsRespuestasCorrectas.add(respuesta.getId());
        }
    }

    public void addVariasRespuestas(List<Respuesta> lista){
        for (Respuesta respuesta : lista) {
            addRespuesta(respuesta);
        }
    }

    public List<String> getIdsRespuestasCorrectas() {
        return Collections.unmodifiableList(idsRespuestasCorrectas);
    }

    public void actualizarIds(){
        String[] ids = IDS.split("");
        for (int i = 0; i < respuestas.size(); i++) {
            respuestas.get(i).setId(ids[i]);
        }
    }

    public void desordenarRespuestas(){
        Random random = new Random(Double.doubleToLongBits(Math.random()));
        Collections.shuffle(respuestas, random);
        actualizarIds();
        idsRespuestasCorrectas = respuestas.stream()
                .filter(Respuesta::isEsCorrecta)
                .map(Respuesta::getId)
                .toList();
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        result = 31 * result + (respuestas != null ? respuestas.hashCode() : 0);
        result = 31 * result + (idsRespuestasCorrectas != null ? idsRespuestasCorrectas.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pregunta pregunta = (Pregunta) obj;

        if (id != pregunta.id) return false;
        if (texto != null ? !texto.equals(pregunta.texto) : pregunta.texto != null) return false;
        if (respuestas != null ? !respuestas.equals(pregunta.respuestas) : pregunta.respuestas != null) return false;
        return idsRespuestasCorrectas != null ? idsRespuestasCorrectas.equals(pregunta.idsRespuestasCorrectas) : pregunta.idsRespuestasCorrectas == null;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", respuestas=" + respuestas +
                ", idsRespuestasCorrectas=" + idsRespuestasCorrectas +
                '}';
    }

}
