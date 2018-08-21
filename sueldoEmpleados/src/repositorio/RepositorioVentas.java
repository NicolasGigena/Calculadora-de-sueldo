/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import modelos.Venta;

public class RepositorioVentas {

    private static ArrayList<Venta> ventas = new ArrayList<Venta>();

    public void guardarVenta(Venta venta) {
        this.ventas.add(venta);
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

}
