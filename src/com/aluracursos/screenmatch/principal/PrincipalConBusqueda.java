package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.excepcion.ErrorEnConversionDeDuracionExcepcion;
import com.aluracursos.screenmatch.modelos.Pelicula;
import com.aluracursos.screenmatch.modelos.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting() //con esta opcion podremos ver bien la lista luego de ejecutar.
                .create();
        /*Con esto conseguimos guardar nuestras películas favoritas en un archivo json, que podrá ser
        leído por otras aplicaciones y, futuramente, persistidos en una base de datos. ¡Espero que les haya gustado esta actividad!*/
        while(true) {
            System.out.println("Ingrese el nombre de una película: ");
            var busqueda = lectura.nextLine();
            if(busqueda.equalsIgnoreCase("exit")) { //Si la persona marca salir sin importar si es mayusvula o minuscula
                break;
            }

            System.out.println("Coloque aquí su clave");
            String clave= lectura.nextLine();
            String busquedaCodificada = URLEncoder.encode(busqueda, StandardCharsets.UTF_8);
            String direccion = "https://www.omdbapi.com/?t=" + busquedaCodificada + "&apikey=" + clave;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder() /*Es un patron builder. Sirve para construir algo que puede tener muchas formas*/
                        .uri(URI.create(direccion))
                        .build();

                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString()); /*Envía datos, BodyHandler interpreta lo que estamos enviando*/

                String json = response.body();
                System.out.println(json);
//        Titulo miTitulo =gson.fromJson(json, Titulo.class);
                TituloOMDb miTituloOmdb = gson.fromJson(json, TituloOMDb.class);
                System.out.println("Titulo: " + miTituloOmdb);


                Titulo miTitulo = new Titulo(miTituloOmdb); //Creamos nuevo constructor
                System.out.println("Titulo ya convertido: " + miTitulo);

                titulos.add(miTitulo);
                //Como guardamnos un Json


            } catch (NumberFormatException e) {
                System.out.println("Ocurrio un error: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Ocurrio un error en la URI, verifique la excepcion");
                System.out.println(e.getMessage());
            }catch (ErrorEnConversionDeDuracionExcepcion e) { //No es bueno tener un catch con una excepción tan generica como excepcion
                System.out.println("Ocurrio un error inesperado: ");
                System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos);

        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();
        System.out.println("Finalizó la ejecución del programa");
//        System.out.println(response.body()); /*Respuesta, queremos mostrqar solo el Body, porque da otras csas.*/
    }
}
