package org.example;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ConexionAPI conexionAPI = new ConexionAPI();
        Gson gson = new Gson();
        JsonObject jsonObject;
        JsonObject monedasCompletas;
        HashMap<String, Double> mapaMonedas;

        conexionAPI.consultarDolar();
        jsonObject = JsonParser.parseString(conexionAPI.getResponse().body()).getAsJsonObject();
        monedasCompletas = jsonObject.getAsJsonObject("conversion_rates");
        mapaMonedas = gson.fromJson(monedasCompletas, HashMap.class);

        boolean seguir = true;
        int opcion;
        float cantidad;


        while (seguir) {
            mostrarMenu();
            opcion = validarOpcion();

            switch (opcion) {
                case 1:
                    pedirCantidad();
                    cantidad = validarCantidad();
                    System.out.println("El resultado es " + cantidad * mapaMonedas.get("ARS") + " pesos Argentinos");
                    break;
                case 2:
                    pedirCantidad();
                    cantidad = validarCantidad();
                    System.out.println("El resultado es " + cantidad / mapaMonedas.get("ARS") + " Dolares");
                    break;
                case 3:
                    pedirCantidad();
                    cantidad = validarCantidad();
                    System.out.println("El resultado es " + cantidad * mapaMonedas.get("BRL") + " reales Brasileños");
                    break;
                case 4:
                    pedirCantidad();
                    cantidad = validarCantidad();
                    System.out.println("El resultado es " + cantidad / mapaMonedas.get("BRL") + " Dolares");
                    break;
                case 5:
                    pedirCantidad();
                    cantidad = validarCantidad();
                    System.out.println("El resultado es " + cantidad * mapaMonedas.get("COP") + " pesos Colombianos");
                    break;
                case 6:
                    pedirCantidad();
                    cantidad = validarCantidad();
                    System.out.println("El resultado es " + cantidad / mapaMonedas.get("COP") + " Dolares");
                    break;
                case 7:
                    System.out.println("Nos vemos!");
                    seguir = false;
                    break;
                default:
                    System.out.println("Escribe una opcion valida por favor.");
                    break;
            }
        }
    }
    public static void mostrarMenu() {
        System.out.println("===========================================");
        System.out.println("Conversor de Monedas de Marco");
        System.out.println("===========================================");
        System.out.println("1) Dolar a Peso Argentino");
        System.out.println("2) Peso Argentino a Dolar");
        System.out.println("3) Dolar a Real Brasileño");
        System.out.println("4) Real Brasileño a Dolar");
        System.out.println("5) Dolar a Peso Colombiano");
        System.out.println("6) Peso Colombiano a Dolar");
        System.out.println("7) Salir");
        System.out.println("Elije una opcion valida");
        System.out.println("===========================================");
    }

    public static void pedirCantidad(){
        System.out.println("Introduce la cantidad que quieres convertir");
    }

    public static int validarOpcion() {
        int num = 0;
        while (true){
            try {
                num = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Por favor introduce un numero entero.");
                scanner.nextLine();
            }
        }
        return num;
    }

    public static float validarCantidad() {
        float cant = 0;
        while (true){
            try {
                cant = scanner.nextFloat();

                if (cant >= 0) {
                    break;
                } else {
                    System.out.println("Por favor introduce una cantidad no negativa.");
                }
            } catch (Exception e) {
                System.out.println("Por favor introduce una cantidad correcta.");
                scanner.nextLine();
            }
        }
        return cant;
    }
}
