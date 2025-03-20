package com.example.java;

public class Opciones {

    private String pathFicheroPreguntas;
    private String pathDestino;
    private String formato;
    private String nombreTest;
    private String curso;
    private int cantidadPreguntas;
    private int cantidadTest;
    private boolean unicoFichero;

    public Opciones(String nombreTest, String curso, String pathFicheroPreguntas, int cantidadTest, int cantidadPreguntas, String pathDestino, String formato, boolean unicoFichero) {
        this.pathFicheroPreguntas = pathFicheroPreguntas;
        this.pathDestino = pathDestino;
        this.formato = formato;
        this.nombreTest = nombreTest;
        this.curso = curso;
        this.cantidadPreguntas = cantidadPreguntas;
        this.cantidadTest = cantidadTest;
        this.unicoFichero = unicoFichero;
    }

    public Opciones(String nombreTest, String curso, String pathFicheroPreguntas, int cantidadTest){
        this(nombreTest, curso, pathFicheroPreguntas, cantidadTest, -1, "./", "cli", false);
    }

    public String getPathFicheroPreguntas() {
        return pathFicheroPreguntas;
    }

    public String getPathDestino() {
        return pathDestino;
    }

    public String getFormato() {
        return formato;
    }

    public String getNombreTest() {
        return nombreTest;
    }

    public String getCurso() {
        return curso;
    }

    public int getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    public int getCantidadTest() {
        return cantidadTest;
    }

    public boolean isUnicoFichero() {
        return unicoFichero;
    }
    public void setPathFicheroPreguntas(String pathFicheroPreguntas) {
        this.pathFicheroPreguntas = pathFicheroPreguntas;
    }

    public void setPathDestino(String pathDestino) {
        this.pathDestino = pathDestino;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setNombreTest(String nombreTest) {
        this.nombreTest = nombreTest;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setCantidadPreguntas(int cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
    }

    public void setCantidadTest(int cantidadTest) {
        this.cantidadTest = cantidadTest;
    }

    public void setUnicoFichero(boolean unicoFichero) {
        this.unicoFichero = unicoFichero;
    }
}
