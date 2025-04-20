package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.modelos.Pelicula;
import com.aluracursos.screenmatch.modelos.Serie;
import com.aluracursos.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        Serie casaDragon = new Serie("Lost", 2022);
        Pelicula otraPelicula = new Pelicula("Avatar", 1998);
        var peliculaDeBruno= new Pelicula("El señor de los anillos", 2001);

        List<Titulo> lista = new ArrayList<>();
        lista.add(miPelicula);
        miPelicula.evalua(9);
        lista.add(otraPelicula);
        otraPelicula.evalua(6);
        lista.add(peliculaDeBruno);
        peliculaDeBruno.evalua(10);
        lista.add(casaDragon);

        for (Titulo item : lista) {
            System.out.println(item.getNombre());
            if(item instanceof Pelicula pelicula && pelicula.getClasificacion()>2) {
                System.out.println("Clasificación: "+ pelicula.getClasificacion());
            }
        }
        ArrayList<String> listaDeArtistas = new ArrayList<>(); //Queremos ordenar
        listaDeArtistas.add("Penelope Cruz");
        listaDeArtistas.add("Antonio Banderas");
        listaDeArtistas.add("Ricardo darin");
        System.out.println("Lista de artistas no ordenadas: "+listaDeArtistas);
        Collections.sort(listaDeArtistas);
        System.out.println("Lista de artistas  ordenadas: "+listaDeArtistas);
        Collections.sort(lista);

        System.out.println("Lista ordenada de titulos: "+lista); //Aunque serie se imprime mal, igual se logro ordenar
        //quiero ordenar por fecha de lanzamiento, las dos posivilidades
        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
        System.out.println("Lista ordenada también por fecha: "+lista);


    }

}
