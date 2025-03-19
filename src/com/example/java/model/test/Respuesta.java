package com.example.java.model.test;

import com.example.java.model.plantilla.PlantillaPregunta;
import com.example.java.model.plantilla.PlantillaRespuesta;

public class Respuesta {

    private String id;
    private String texto;
    private boolean esCorrecta;

    public Respuesta() {
    }

    public Respuesta(String id, String texto, boolean esCorrecta) {
        this.id = id;
        this.texto = texto;
        this.esCorrecta = esCorrecta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta){
        this.esCorrecta = esCorrecta;
    }

    public static Respuesta of(String id, PlantillaPregunta plantillaPregunta, boolean esCorrecta){
        return new Respuesta(id, plantillaPregunta.getTexto(), esCorrecta);
    }

    public static Respuesta of(PlantillaRespuesta plantillaRespuesta, boolean esCorrecta){
        return new Respuesta(null, plantillaRespuesta.getTexto(), esCorrecta);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        result = 31 * result + (esCorrecta ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Respuesta respuesta = (Respuesta) obj;

        if (esCorrecta != respuesta.esCorrecta) return false;
        if (id != null ? !id.equals(respuesta.id) : respuesta.id != null) return false;
        return texto != null ? texto.equals(respuesta.texto) : respuesta.texto == null;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "id='" + id + '\'' +
                ", texto='" + texto + '\'' +
                ", esCorrecta=" + esCorrecta +
                '}';
    }
}
