/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import inventario.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author a
 */
public class Tabla_Inventario extends AbstractTableModel {

    private String[] columna = {"Codigo", "Nombre", "Precio", "Existencia"};
    private List<Producto> producto = new ArrayList<>();
    
    public Tabla_Inventario(List<Producto> producto_n){
        this.producto = producto_n;
    }
    @Override
    public int getRowCount() {
        return this.producto.size();
        }

    @Override
    public int getColumnCount() {
        return this.columna.length;
        }

    @Override
    public Object getValueAt(int fila, int columna) {
        Object respuesta;
        
        switch(columna){
            case 0:
                respuesta = this.producto.get(fila).getCodigo();
                break;
            case 1:
                respuesta = this.producto.get(fila).getNombre();
                break;
            case 2:
                respuesta = this.producto.get(fila).getPrecio();
                break;
            default:
                respuesta = this.producto.get(fila).getExistencia();
        }
        return respuesta;
    }
    
    @Override
    public String getColumnName(int columna1){
        return this.columna[columna1];
    }
    
    public void ActualizarTabla(){
        fireTableDataChanged();
    }
    
    public Producto detalle(int fila){
        return this.producto.get(fila);
    }
}
