/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author a
 */
public class Base_Inventario {
    private HashMap<Integer, Producto> lista = new HashMap<>();
    private final String archivo = "inventario.txt";

    public Base_Inventario() {
        cargarProductos();
    }

    public List<Producto> getLista() {
        return new ArrayList<>(this.lista.values());
    }

    public void serLista(HashMap<Integer, Producto> lista) {
        this.lista = lista;
    }

    public boolean verificarExistencia(Producto producto) {
        return this.lista.containsKey(producto.getCodigo());
    }

    public boolean verificarExistencia(String nombre) {
        for (Producto product : this.lista.values()) {
            if (nombre.equalsIgnoreCase(product.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public int ultimoCodigo() {
        int codigo = 0;
        for (Producto product : this.lista.values()) {
            codigo = product.getCodigo();
        }
        return codigo;
    }
    //----------------------------------------------------------------------
    private void guardarProductos() {
    try {
        FileWriter writer = new FileWriter(archivo);
        for (Producto producto : lista.values()) {
            writer.write(producto.getCodigo() + "," + producto.getNombre() + 
                    "," + producto.getPrecio() + "," + producto.getExistencia() + "\n");
        }
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    //*************************************************************************

    public void agregar(Producto product) {
        this.lista.put(product.getCodigo(), product);
        guardarProductos();
    }

    public void actualizar(Producto product) {
        if (this.lista.containsKey(product.getCodigo())) {
            this.lista.replace(product.getCodigo(), product);
            guardarProductos();
        }
    }

    public void eliminar(Producto product) {
        if (this.lista.containsKey(product.getCodigo())) {
            this.lista.remove(product.getCodigo());
            guardarProductos();
        }
    }
    //----------------------------------------------------------------------

    public String generarInforme() {
        List<Producto> listaInforme = obtenerInforme();
        StringBuilder informe = new StringBuilder();
        for (Producto producto : listaInforme) {
            informe.append(producto.getNombre()).append(" ");
        }
        return informe.toString().trim();
    }

    private List<Producto> obtenerInforme() {
        List<Producto> listaNueva = new ArrayList<>(this.lista.values());
        List<Producto> listaInforme = new ArrayList<>();
        for (int i = 0; i < 3 && i < listaNueva.size(); i++) {
            Producto producto = Collections.max(listaNueva, Comparator.comparingDouble(Producto::getPrecio));
            listaInforme.add(producto);
            listaNueva.remove(producto);
        }
        return listaInforme;
    }

    public void verificarExistenciaBaja() {
            for (Producto product : this.lista.values()) {
                if (product.getExistencia() < 10) {
                 String mensaje = "¡Alerta! El producto " + product.getNombre() + " tiene una existencia baja: " + product.getExistencia();
                JOptionPane.showMessageDialog(null, mensaje, "Existencia Baja", JOptionPane.WARNING_MESSAGE);
                }   
            }
    }

    private void cargarProductos() {
        try {
            File file = new File(archivo);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        int codigo = Integer.parseInt(data[0].trim());
                        String nombre = data[1].trim();
                        double precio = Double.parseDouble(data[2].trim());
                        int existencia = Integer.parseInt(data[3].trim());
                        Producto producto = new Producto(codigo, nombre, precio, existencia);
                        lista.put(codigo, producto);
                    }
                }
                reader.close();
            }
        } catch(IOException e) {
        e.printStackTrace();
    }
                
    }
}
    