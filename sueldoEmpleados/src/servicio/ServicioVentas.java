/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelos.Empleado;
import modelos.Venta;
import repositorio.RepositorioVentas;
import repositorio.RepositorioVentas;

public class ServicioVentas {

    private RepositorioVentas repositorioVentas;

    public ServicioVentas() {
        this.repositorioVentas = new RepositorioVentas();
    }

    public void guardarDatosVentas(String precioArticulosST, String cantidadDeVendidosST, String articulo, String idEmpleado) {

        double precioArticulos = 0;
        double cantidadArticulos = 0;
        String mensaje = "";
        int idEmpleadoINT = 0;

        if (idEmpleado.length() == 79) {
            mensaje = idEmpleado;
        } else {
            idEmpleado = idEmpleado.replaceAll("\\D+", "");
            idEmpleadoINT = Integer.parseInt(idEmpleado);
        }

        if (idEmpleado.length() == 0) {
            mensaje += "\n No a selecionado ningun empleado";
        }
        if (precioArticulosST.length() == 0) {

            mensaje += "\n No se a ingresado el precio del articulo";
        } else {
            try {
                precioArticulos = Double.parseDouble(precioArticulosST);
            } catch (NumberFormatException exception) {

                mensaje += "\n Precio del articulo ingresado incorrecto";
            }
        }

        if (cantidadDeVendidosST.length() == 0) {

            mensaje += "\n No se a ingresado la cantidade articulos";

        } else {
            try {
                cantidadArticulos = Double.parseDouble(cantidadDeVendidosST);
            } catch (NumberFormatException exception) {

                mensaje += "\n Cantidad de articulos incorrecta";
            }
        }

        if (articulo.length() == 0) {

            mensaje += "\n No a ingresado el nombre del articulo";
        }

        if (mensaje == "") {
            Venta venta = new Venta(precioArticulos, cantidadArticulos, idEmpleadoINT);

            repositorioVentas.guardarVenta(venta);
        } else {

            throw new IllegalArgumentException(mensaje);

        }

    }

}
