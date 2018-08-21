/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author nicol
 */
public class Empleado {

    private String nombre;
    private String apellido;
    private String dni;
    private double sueldoBase;

    public Empleado(String nombre, String apellido, String dni, double sueldoBase) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.sueldoBase = sueldoBase;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    @Override
    public String toString() {
        return this.nombre + ": " + this.dni;
    }

}
