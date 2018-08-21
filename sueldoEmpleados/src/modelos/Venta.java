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
public class Venta {

    private double precioArticulos;
    private double cantidadArticulos;
    private int idEmpleado;

    public Venta(double precioArticulos, double cantidadArticulos, int idEmpleado) {
        this.precioArticulos = precioArticulos;
        this.cantidadArticulos = cantidadArticulos;
        this.idEmpleado = idEmpleado;
    }

    public double getPrecioArticulos() {
        return precioArticulos;
    }

    public double getCantidadArticulos() {
        return cantidadArticulos;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

}
