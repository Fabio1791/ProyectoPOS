/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package factura;

/**
 *
 * @author fabio
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Factura {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      List<Double> preciosPlatos = new ArrayList<Double>();
      Map<Integer, String> menu = new HashMap<Integer, String>();
      menu.put(1, "Plato del día");
      menu.put(2, "Arroz con pollo");
      menu.put(3, "Arroz con camarones");
      menu.put(4, "Arroz de la casa");
      menu.put(5, "Hamburguesa");
      menu.put(6, "Batido en agua");
      menu.put(7, "Batido en leche");
      Map<Integer, Double> precios = new HashMap<Integer, Double>();
      precios.put(1, 2500.0);
      precios.put(2, 4000.0);
      precios.put(3, 6000.0);
      precios.put(4, 4000.0);
      precios.put(5, 3500.0);
      precios.put(6, 1500.0);
      precios.put(7, 1800.0);
      
      final double IVA = 0.13; // Constante para el porcentaje de IVA
      
      // Solicita el número de la mesa
      System.out.print("Ingrese el número de mesa: ");
      int numeroMesa = sc.nextInt();
      
      // Solicita el nombre del cliente
      System.out.print("Ingrese el nombre del cliente: ");
      String nombreCliente = sc.next();
      
      // Muestra el menú
      System.out.println("\n--- Menú ---");
      for (int i = 1; i <= menu.size(); i++) {
         System.out.println(i + ". " + menu.get(i));
      }
      
      // Solicita la selección del cliente
      boolean seguirSeleccionando = true;
      while (seguirSeleccionando) {
         System.out.print("\nSeleccione una opción del menú (0 para terminar): ");
         int seleccion = sc.nextInt();
         
         if (seleccion == 0) {
            seguirSeleccionando = false;
         } else if (menu.containsKey(seleccion)) {
            preciosPlatos.add(precios.get(seleccion));
         } else {
            System.out.println("Selección inválida");
         }
      }
      
      // Calcula el precio total
      double total = 0;
      for (Double precio : preciosPlatos) {
         total += precio;
      }
      double impuesto = total * IVA;
      double precioFinal= impuesto+total;
      
      
      // Imprime la factura
      System.out.println("\n--- Nombre del Restaurante ---");
      System.out.println("Mesa: " + numeroMesa);
      System.out.println("Cliente: " + nombreCliente);
      System.out.println("Cantidad de platos:  " + preciosPlatos.size());
      for (int i = 0; i < preciosPlatos.size(); i++) {
         System.out.println(  menu.get(i+1) + "   ₡" + preciosPlatos.get(i));
      }
      System.out.println("Monto sin impuesto: ₡" + total);
       System.out.println("IVA:  ₡"+ impuesto);
       System.out.println("Total: ₡"+ precioFinal) ;
      System.out.println("Gracias por su visita!");
   }
}


