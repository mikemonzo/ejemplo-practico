package com.example.java.model.plantilla;

public class PlantillaRespuesta {

        private String texto;

        public PlantillaRespuesta() {
        }

        public PlantillaRespuesta(String texto) {
            this.texto = texto;
        }

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        @Override
        public int hashCode() {
            return texto != null ? texto.hashCode() : 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PlantillaRespuesta that = (PlantillaRespuesta) obj;
            return texto != null ? texto.equals(that.texto) : that.texto == null;
        }

        @Override
        public String toString() {
            return "PlantillaRespuesta{" +
                "texto='" + texto + '\'' +
                '}';
        }
}
