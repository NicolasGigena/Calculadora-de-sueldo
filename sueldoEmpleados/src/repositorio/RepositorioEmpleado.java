/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import modelos.Empleado;

public class RepositorioEmpleado {

    private static ArrayList<Empleado> empleados = new ArrayList<Empleado>();

    public void guardarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

}
