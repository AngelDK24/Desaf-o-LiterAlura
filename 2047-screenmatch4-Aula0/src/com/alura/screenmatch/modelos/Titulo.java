package com.alura.screenmatch.modelos;

import com.alura.screenmatch.excepcion.ErorEnconvercionDeDuracionException;
import com.google.gson.annotations.SerializedName;


public class Titulo implements Comparable<Titulo>{

    private String nombre;

    private int fechaDeLanzamiento;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeEvaluaciones;
    private int duracionEnMinutos;

    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Titulo(Tituliomb miTituliomb) {
        this.nombre = miTituliomb.title();
        this.fechaDeLanzamiento = Integer.valueOf(miTituliomb.year());
        if(miTituliomb.runtime().contains("N/A")){
            throw new ErorEnconvercionDeDuracionException("No se pudo convertir la Duracion"+
                    "Porque exite una N/A");

        }
        this.duracionEnMinutos = Integer.valueOf(miTituliomb.runtime().substring(0,3).replace(" ",""));

    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public int getTotalDeEvaluaciones() {
        return totalDeEvaluaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setIncluidoEnElPlan(boolean incluidoEnElPlan) {
        this.incluidoEnElPlan = incluidoEnElPlan;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public void muestraFichaTecnica(){
        System.out.println("Nombre de la película: " + nombre);
        System.out.println("Año de lanzamiento: " + fechaDeLanzamiento);
    }

    public void evalua(double nota){
        sumaDeLasEvaluaciones += nota;
        totalDeEvaluaciones++;
    }

    public double calculaMediaEvaluaciones(){
        return sumaDeLasEvaluaciones / totalDeEvaluaciones;
    }

    @Override
    public int compareTo(Titulo otroTitulo) {
        return this.getNombre().compareTo(otroTitulo.getNombre());

    }

    @Override
    public String toString() {
        return "(Titulo de la pelicula:" + nombre +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento+
                "Duracion en minutos ="+duracionEnMinutos+")";
    }
}
