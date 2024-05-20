package com.alura.screenmatch.principal;

import com.alura.screenmatch.excepcion.ErorEnconvercionDeDuracionException;
import com.alura.screenmatch.modelos.Tituliomb;
import com.alura.screenmatch.modelos.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class PrincipalConBusqueda {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura =new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        while (true){
        System.out.println("Escribe el nombre de una pelicula ");
        var busqueda  =   lectura.nextLine();

            if(busqueda.equalsIgnoreCase("salir")){
                break;

            }
            String direccion = "https://www.omdbapi.com/?t="
                + busqueda.replace(" ", "+") + "&?i=tt3896198&apikey=5d959ef5";


            try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();


                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);


                Tituliomb miTituliomb = gson.fromJson(json,Tituliomb.class);
                System.out.println(miTituliomb);

                Titulo miTitulo = new Titulo(miTituliomb);
                System.out.println("Titulo ya convertido "+miTitulo);
                titulos.add(miTitulo);
            }catch (NumberFormatException e){
                System.out.println("Ocurrio un error:");
                System.out.println(e.getMessage());

            }catch (IllegalArgumentException e){

                System.out.println("Error en la URI, verifique");
            }catch (ErorEnconvercionDeDuracionException e){
                System.out.println(e.getMessage());
            }

        }

        System.out.println(titulos);
        FileWriter escritura = new FileWriter("titulos");
        escritura.write(gson.toJson(titulos));
        escritura.close();
        System.out.println("finalizo ls ejecucion del programa ");
    }
}
