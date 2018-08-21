/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentadores;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicio.ServicioEmpleado;
import vistas.VistaPrincipal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelos.Empleado;
import repositorio.RepositorioEmpleado;
import servicio.ServicioVentas;

public class PresentadorPrincipal {

    private VistaPrincipal vistaPrincipal;

    private ServicioEmpleado servicioEmpleado;

    private ServicioVentas servicioVentas;

    private boolean empleadoGuardado = false;

    private boolean ventaGuardada = false;

    public PresentadorPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        this.servicioEmpleado = new ServicioEmpleado();
        this.servicioVentas = new ServicioVentas();
    }

    public void guardarDatosEmpleado() {

        boolean valido = true;

        String nombre = vistaPrincipal.getNombrejTextField().getText();

        String apellido = vistaPrincipal.getApellidojTextField().getText();

        String dni = vistaPrincipal.getDnijTextField().getText();

        String sueldoBase = vistaPrincipal.getSueldoBasejTextField().getText();

        try {
            servicioEmpleado.guardarDatosEmpleado(nombre, apellido, dni, sueldoBase);
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "ATENCION", JOptionPane.ERROR_MESSAGE);
            valido = false;
        }

        if (valido == true) {
            JOptionPane.showMessageDialog(null, "Guardado con exito", "ATENCION", JOptionPane.INFORMATION_MESSAGE);

            empleadoGuardado = true;

            vistaPrincipal.getNombrejTextField().setText("");
            vistaPrincipal.getApellidojTextField().setText("");
            vistaPrincipal.getDnijTextField().setText("");
            vistaPrincipal.getSueldoBasejTextField().setText("");
            Empleado empleado = servicioEmpleado.optenerEmpleado();
            escribirComboBox(empleado);

        }

    }

    public void escribirComboBox(Empleado empleados) {
        this.vistaPrincipal.getEmpleadojComboBox().addItem(empleados.toString());
        this.vistaPrincipal.getCalcularSueldojComboBox().addItem(empleados.toString());
    }

    public void guardarDatosVentas() {
        boolean valido = true;

        String idEmpleado = null;

        String precioArticulo = vistaPrincipal.getPrecioArticulojTextField().getText();

        String cantidadDeVendidos = vistaPrincipal.getCantidadjTextField().getText();

        String articulo = vistaPrincipal.getArticulojTextField().getText();
        try {
            idEmpleado = vistaPrincipal.getEmpleadojComboBox().getSelectedItem().toString();
        } catch (Exception e) {
            idEmpleado = "No a se ha registrado ningun empleado con anterioridad, por favor reguistre uno";
        }

        try {
            servicioVentas.guardarDatosVentas(precioArticulo, cantidadDeVendidos, articulo, idEmpleado);
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "ATENCION", JOptionPane.ERROR_MESSAGE);
            valido = false;
        }

        if (valido == true) {
            JOptionPane.showMessageDialog(null, "Guardado con exito", "ATENCION", JOptionPane.INFORMATION_MESSAGE);

            ventaGuardada = true;

            vistaPrincipal.getPrecioArticulojTextField().setText("");
            vistaPrincipal.getCantidadjTextField().setText("");
            vistaPrincipal.getArticulojTextField().setText("");

        }

    }

    public void calcularSueldo() {

        if (empleadoGuardado == true && ventaGuardada == true) {

            String idEmpleado = this.vistaPrincipal.getCalcularSueldojComboBox().getSelectedItem().toString();
            idEmpleado = idEmpleado.replaceAll("\\D+", "");
            int idEmpleadoINT = Integer.valueOf(idEmpleado);
            try {
                servicioEmpleado.calcularSueldo(idEmpleadoINT);
            } catch (IllegalArgumentException error) {
                JOptionPane.showMessageDialog(null, error.getMessage(), "ATENCION", JOptionPane.ERROR_MESSAGE);
            }
        } else if (empleadoGuardado == false && ventaGuardada == true) {
            JOptionPane.showMessageDialog(null, "Flata completar los datos el empleado", "ATENCION", JOptionPane.ERROR_MESSAGE);
        } else if (empleadoGuardado == true && ventaGuardada == false) {
            JOptionPane.showMessageDialog(null, "Flata completar los datos de las ventas", "ATENCION", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Flata ingresar los datos de el empleado y sus ventas", "ATENCION", JOptionPane.ERROR_MESSAGE);
        }

    }

}
