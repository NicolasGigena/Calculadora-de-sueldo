package servicio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import modelos.Empleado;
import modelos.Venta;
import presentadores.PresentadorPrincipal;
import repositorio.RepositorioEmpleado;
import repositorio.RepositorioVentas;

public class ServicioEmpleado {

    private Empleado empleado;

    private RepositorioEmpleado repositorioEmpleado;
    private RepositorioVentas repositorioVentas;

    public ServicioEmpleado() {
        this.repositorioEmpleado = new RepositorioEmpleado();
        this.repositorioVentas = new RepositorioVentas();
    }

    public void guardarDatosEmpleado(String nombre, String apellido, String dni, String sueldoBase) {

        Pattern pat = Pattern.compile("[a-zA-Z]+");
        Matcher mat = pat.matcher(nombre);

        Pattern pat1 = Pattern.compile("[a-zA-Z]+");
        Matcher mat1 = pat1.matcher(nombre);

        double doubleSueldoBase = 0;
        String mensaje = "";

        if (nombre.length() == 0) {
            mensaje += "\n No se a ingesado ningun nombre";
        } else if (!mat.matches()) {
            mensaje += "\n Nombre ingresado incorrecto";
        }

        if (apellido.length() == 0) {
            mensaje += "\n No se a ingresado ningun apellido";
        } else if (!mat1.matches()) {
            mensaje += "\n Apellido ingresado incorrecto";
        }

        if (dni.length() == 0) {
            mensaje += "\n No se a ingresado ningun dni";
        } else if (dni.length() <= 6 && dni.length() >= 8) {
            mensaje += "\n Dni ingreado incorrecto";
        } else {
            try {
                Integer.parseInt(dni);
            } catch (NumberFormatException exception) {
                mensaje += "\n Dni ingreado incorrecto";
            }

        }

        if (sueldoBase.length() == 0) {
            mensaje += "\n No se a ingresado ningun sueldo base";
        } else {
            try {
                doubleSueldoBase = Double.parseDouble(sueldoBase);
            } catch (NumberFormatException exception) {
                mensaje += "\n Sueldo base ingresado incorrecto";
            }
        }

        if (mensaje == "") {

            Empleado empleado = new Empleado(nombre, apellido, dni, doubleSueldoBase);

            repositorioEmpleado.guardarEmpleado(empleado);

            this.empleado = empleado;

        } else {

            throw new IllegalArgumentException(mensaje);

        }

    }

    public Empleado optenerEmpleado() {
        return this.empleado;
    }

    public void calcularSueldo(int idEmpleado) {

        String nombre = null;
        String apellido = null;
        String dni = null;
        int cantidadDeVentas = 0;
        double sueldoBase = 0;

        double suma = 0;

        for (Venta venta : repositorioVentas.getVentas()) {

            if (venta.getIdEmpleado() == idEmpleado) {
                suma += venta.getPrecioArticulos() * venta.getCantidadArticulos();
                cantidadDeVentas += 1 * venta.getCantidadArticulos();

            }

            if (cantidadDeVentas == 0) {
                throw new IllegalArgumentException("El empleado ingresado no posee ninguna venta");
            }

        }

        String idEmpleadoSTR = Integer.toString(idEmpleado);

        for (Empleado empleado : repositorioEmpleado.getEmpleados()) {

            if (empleado.getDni().equals(idEmpleadoSTR)) {
                nombre = empleado.getNombre();
                apellido = empleado.getApellido();
                dni = empleado.getDni();
                sueldoBase = empleado.getSueldoBase();
            }

        }

        double total = 5 * suma / 100;

        double sueldoTotal = sueldoBase + total;

        JOptionPane.showMessageDialog(null, "Nombre: " + nombre + "\n"
                + "Apellido: " + apellido + "\n"
                + "Dni: " + dni + "\n"
                + "Cantidad de ventas: " + cantidadDeVentas + "\n"
                + "sueldo: " + sueldoTotal, "INFORME DE SUELDO DE:" + nombre, JOptionPane.INFORMATION_MESSAGE);
    }

}
