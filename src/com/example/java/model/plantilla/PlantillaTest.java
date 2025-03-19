package com.example.java.model.plantilla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlantillaTest {

    private String nombre;
    private String curso;
    private List<PlantillaPregunta> preguntas;

    public PlantillaTest() {
        this.preguntas = new ArrayList<>();
    }

    public PlantillaTest(String nombre, String curso) {
        this();
        this.nombre = nombre;
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public List<PlantillaPregunta> getPreguntas() {
        return Collections.unmodifiableList(preguntas);
    }

    public void addPregunta(PlantillaPregunta pregunta) {
        this.preguntas.add(pregunta);
    }

    public void addPreguntas(List<PlantillaPregunta> preguntas) {
        this.preguntas.addAll(preguntas);
    }

    public boolean removePregunta(PlantillaPregunta pregunta) {
        return this.preguntas.remove(pregunta);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (curso != null ? curso.hashCode() : 0);
        result = 31 * result + (preguntas != null ? preguntas.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PlantillaTest that = (PlantillaTest) obj;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (curso != null ? !curso.equals(that.curso) : that.curso != null) return false;
        return preguntas != null ? preguntas.equals(that.preguntas) : that.preguntas == null;
    }

    @Override
    public String toString() {
        return "PlantillaTest{" +
                "nombre='" + nombre + '\'' +
                ", curso='" + curso + '\'' +
                ", preguntas=" + preguntas +
                '}';
    }
}
