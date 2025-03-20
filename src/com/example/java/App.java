package com.example.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.example.java.error.TestFormatException;
import com.example.java.in.LectorPreguntas;
import com.example.java.in.LectorPreguntasCsv;
import com.example.java.model.plantilla.PlantillaTest;
import com.example.java.model.test.Test;
import com.example.java.out.OutputTest;
import com.example.java.out.OutputTestConsole;
import com.example.java.out.OutputTestHtml;
import com.example.java.out.OutputTestMarkdown;
import com.example.java.process.GeneradorTestsAleatorios;
import com.example.java.process.GeneradorTestsAleatoriosImpl;

public class App {

    static Opciones opciones;
    public static void main(String[] args) throws Exception {
        procesarArgumentos(args);

        PlantillaTest plantillaTest = new PlantillaTest(opciones.getNombreTest(), opciones.getCurso());

        LectorPreguntas lector;
        try{
            lector = new LectorPreguntasCsv(opciones.getPathFicheroPreguntas());
            plantillaTest.addPreguntas(lector.leerTodasLasPreguntas());
        } catch (IOException e){
            e.printStackTrace();;
            System.err.println("Error al leer el fichero de preguntas");
            System.exit(-1);
        } catch (TestFormatException e){
            e.printStackTrace();
            System.err.println("Error en el formato del fichero de preguntas");
            System.exit(-1);
        }

        GeneradorTestsAleatorios generador = GeneradorTestsAleatoriosImpl.getInstance();

        int cantidadPreguntas = opciones.getCantidadPreguntas() == -1 ? 
            plantillaTest.getPreguntas().size() : opciones.getCantidadPreguntas();

        List<Test> tests = generador.generarTests(
            plantillaTest, opciones.getCantidadTest(), cantidadPreguntas);

        OutputTest out = switch(opciones.getFormato().toLowerCase()){
            case "html" -> new OutputTestHtml(opciones.isUnicoFichero());
            case "md" -> new OutputTestMarkdown(opciones.isUnicoFichero());
            default -> new OutputTestConsole();
        };

        try {
            out.outputTests(tests);
            out.outputCorrecciones(tests);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al escribir los tests");
            System.exit(-1);
        }
    }

    private static void procesarArgumentos(String args[]){

        String ayuda = """
        Uso: java -jar generate-test <nombre> <curso> <fichero preguntas> <cantidad test> [opciones]
        Opciones:
        -q <cantidad>   Cantidad de preguntas por test. Por defecto, todas las preguntas.
        -d <destino>    Directorio de destino de los tests generados. Por defecto, el directorio actual.
        -f <formato>    Formato de salida de los tests generados, a elegir entre cli, html, md. Por defecto, 'cli'.
        -s              si aparece esta opción, salida en un mismo fichero, si no, un fichero por test.
                """;
        if(args.length < 4){
            System.err.println("Error en el número de argumentos");
            System.err.println(ayuda);
            System.exit(-1);
        }

        int cantidadTest = 1;
        
        try{
            cantidadTest = Integer.parseInt(args[3]);
        }catch(NumberFormatException e){
            System.err.println("Error en el número de tests");
            System.err.println(ayuda);
            System.exit(-1);
        }

        opciones = new Opciones(args[0], args[1], args[2], cantidadTest);

        List<String> restoOpciones = new ArrayList<>(
            Arrays.asList(Arrays.copyOfRange(args, 4, args.length))
        );

        while(!restoOpciones.isEmpty()){
            Iterator<String> it = restoOpciones.iterator();
            boolean printAyuda = false;
            while(it.hasNext()){
                String opcion = it.next();
                switch(opcion.toLowerCase()){
                    case "-q":
                        it.remove();
                        String cantidadPreguntas= it.next();
                        try{
                            opciones.setCantidadPreguntas(Integer.parseInt(cantidadPreguntas));
                            it.remove();
                        } catch (NumberFormatException e) {
                            System.err.println("Error en la cantidad de preguntas");
                            printAyuda = true;
                        }
                        break;
                    case "-d":
                        it.remove();
                        String destino = it.next();
                        opciones.setPathDestino(destino);
                        it.remove();
                        break;
                    case "-f":
                        it.remove();
                        String formato = it.next();
                        if (!List.of("cli", "html", "md").contains(formato.toLowerCase())){
                            System.err.println("Error en el formato");
                            printAyuda = true;
                        } else {
                            opciones.setFormato(formato);
                        }
                        it.remove();
                        break;
                    case "-s":
                        opciones.setUnicoFichero(true);
                        it.remove();
                        break;
                    default:
                        it.remove();
                        printAyuda = true;
                        System.err.println("%s opción no soportada".formatted(opcion));
                }
            }
            if(printAyuda){
                System.err.println(ayuda);
            }
        }
    }
}
