package com.example.java.model.plantilla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlantillaPregunta {

    private int id;
    private String texto;
    private List<PlantillaRespuesta> respuestasCorrectas;
    private List<PlantillaRespuesta> respuestasIncorrectas;

    public PlantillaPregunta() {
        respuestasCorrectas = new ArrayList<>();
        respuestasIncorrectas = new ArrayList<>();
    }

    public PlantillaPregunta(int id, String texto) {
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

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getNumeroRespuestasCorrectas() {
        return respuestasCorrectas.size();
    }

    public int getNumeroRespuestasIncorrectas() {
        return respuestasIncorrectas.size();
    }

    public int getNumeroRespuestas() {
        return getNumeroRespuestasCorrectas() + getNumeroRespuestasIncorrectas();
    }

    public List<PlantillaRespuesta> getRespuestasCorrectas() {
        return Collections.unmodifiableList(respuestasCorrectas);
    }

    public List<PlantillaRespuesta> getRespuestasIncorrectas() {
        return Collections.unmodifiableList(respuestasIncorrectas);
    }

    public List<PlantillaRespuesta> getTodasRespuestas() {
        List<PlantillaRespuesta> respuestas = new ArrayList<>();
        respuestas.addAll(respuestasCorrectas);
        respuestas.addAll(respuestasIncorrectas);
        return Collections.unmodifiableList(respuestas);
    }

    public List<PlantillaRespuesta> getRespuestasAleatorias() {
        List<PlantillaRespuesta> respuestas = new ArrayList<>();
        respuestas.addAll(respuestasCorrectas);
        respuestas.addAll(respuestasIncorrectas);
        Collections.shuffle(respuestas);
        return Collections.unmodifiableList(respuestas);
    }

    public void addRespuestaCorrecta(PlantillaRespuesta respuesta) {
        respuestasCorrectas.add(respuesta);
    }

    public void addRespuestaIncorrecta(PlantillaRespuesta respuesta) {
        respuestasIncorrectas.add(respuesta);
    }

    public boolean removeRespuestaCorrecta(PlantillaRespuesta respuesta) {
        return respuestasCorrectas.remove(respuesta);
    }

    public boolean removeRespuestaIncorrecta(PlantillaRespuesta respuesta) {
        return respuestasIncorrectas.remove(respuesta);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PlantillaPregunta other = (PlantillaPregunta) obj;
        return id == other.id && (texto != null ? texto.equals(other.texto) : other.texto == null);
    }

    @Override
    public String toString() {
        return "PlantillaPregunta{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                '}';
    }
}
