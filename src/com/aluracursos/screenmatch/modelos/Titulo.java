package com.aluracursos.screenmatch.modelos;

import com.aluracursos.screenmatch.excepcion.ErrorEnConversionDeDuracionExcepcion;
import com.aluracursos.screenmatch.principal.TituloOMDb;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo> {
//        @SerializedName("Title")
    private String nombre;
    private int fechaDeLanzamiento;
//        @SerializedName("Year")
    private int duracionEnMinutos;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDelasEvaluaciones;


    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Titulo(TituloOMDb miTituloOmdb) {
        this.nombre= miTituloOmdb.title();
        this.fechaDeLanzamiento= Integer.valueOf(miTituloOmdb.year());//Uno estaba con string y otro int
        if (miTituloOmdb.runtime().contains("N/A")) {
            throw new ErrorEnConversionDeDuracionExcepcion("No pude convertir la duración,"+  //Yo le coloco el nombre, la idea es que termine en excepcion
                    "Porque contiene un N/A");
        }
        this.duracionEnMinutos= Integer.valueOf(miTituloOmdb.runtime().substring(0,3).replace(" "," ")); //Dice "60 min" tienestring e int
    }


    public String getNombre() {
        return nombre;
    }

    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public void setIncluidoEnElPlan(boolean incluidoEnElPlan) {
        this.incluidoEnElPlan = incluidoEnElPlan;
    }

    public int getTotalDelasEvaluaciones(){
        return totalDelasEvaluaciones;
    }

    public void muestraFichaTecnica(){
        System.out.println("El titulo que quieres ver es: " + getNombre());
        System.out.println("Su fecha de lanzamiento es: " + getFechaDeLanzamiento());
        System.out.println("Duración en minutos: "+ getDuracionEnMinutos());
    }

    public void evalua(double nota){
        sumaDeLasEvaluaciones += nota;
        totalDelasEvaluaciones++;
    }

    public double calculaMedia(){
        return sumaDeLasEvaluaciones / totalDelasEvaluaciones;
    }

    @Override
    public int compareTo(Titulo otroTitulo) { //REcibiremos el otro titulo con el que vamos a comparar
        return this.getNombre().compareTo(otroTitulo.getNombre());
    }

    @Override
    public String toString() {
        return "(Nombre: " + nombre +"fecha de lanbzamiento: " + fechaDeLanzamiento +
                "duracion en minutos: " + duracionEnMinutos+ ")";
    }
}
