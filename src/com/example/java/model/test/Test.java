package com.example.java.model.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

    public int id;
    public String nombre;
    public String curso;
    public List<Pregunta> preguntas;

    public Test() {
        this.preguntas = new ArrayList<>();
    }

    public Test(int id, String nombre, String curso) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void addPregunta(Pregunta pregunta) {
        this.preguntas.add(pregunta);
    }

    public void addPreguntas(List<Pregunta> preguntas) {
        this.preguntas.addAll(preguntas);
    }

    public List<Pregunta> getPreguntas() {
        return Collections.unmodifiableList(preguntas);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (curso != null ? curso.hashCode() : 0);
        result = 31 * result + (preguntas != null ? preguntas.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Test test = (Test) obj;
        return id == test.id &&
               (nombre != null ? nombre.equals(test.nombre) : test.nombre == null) &&
               (curso != null ? curso.equals(test.curso) : test.curso == null) &&
               (preguntas != null ? preguntas.equals(test.preguntas) : test.preguntas == null);
    }

    @Override
    public String toString() {
        return "Test{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", curso='" + curso + '\'' +
               ", preguntas=" + preguntas +
               '}';
    }
}
