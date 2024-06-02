package view;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL; 
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Writer;

import model.Torneo;
import model.BinaryTree;
import model.OpenAddressingHashTable; 
import model.DataAnalysis;

public class index
{
    public static void main(String[] args)
    {
        try
        {
            URI uri = new URI("http://localhost:8286/api/torneo");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode != 200)
            {
                throw new RuntimeException("Error: " + responseCode);
            }
            else
            {
                String result = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                 String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();

                System.out.println(result.toString());

                Type collectionType = new TypeToken<List<Torneo>>(){}.getType();
                List<Torneo> data =  new Gson().fromJson( result , collectionType);

                // Se instancia el árbol binario
                BinaryTree equipoBinaryTree = new BinaryTree();
                BinaryTree jugadorBinaryTree = new BinaryTree();

                // SE instancian las tablas hash
                OpenAddressingHashTable equipoHashTable = new OpenAddressingHashTable();
                OpenAddressingHashTable jugadorHashTable = new OpenAddressingHashTable();


                  // Insertar datos en el árbol binario y la tabla hash
                for (Torneo item : data) {
                    equipoBinaryTree.insert(item);
                    jugadorBinaryTree.insert(item);
                    

                    int keyEquipoL = generateAsciiKey(item.getNameEquipoL().trim());
                    int keyEquipoV = generateAsciiKey(item.getNameEquipoV().trim());
                    int keyJugador = generateAsciiKey(item.getMejorJugador().trim());

                    equipoHashTable.add(item.getNameEquipoL().trim(), keyEquipoL);
                    equipoHashTable.add(item.getNameEquipoV().trim(), keyEquipoV);
                    jugadorHashTable.add(item.getMejorJugador().trim(), keyJugador);

                    System.out.println("Equipo Local: " + item.getNameEquipoL().trim() + " -> Key ASCII: " + keyEquipoL);
                    System.out.println("Equipo Visitante: " + item.getNameEquipoV().trim() + " -> Key ASCII: " + keyEquipoV);
                    System.out.println("Mejor Jugador: " + item.getMejorJugador().trim() + " -> Key ASCII: " + keyJugador);
                }

                   // Se realiza el análisis de datos requeridos 
                   String equipoMasPuntos = DataAnalysis.equipoConMasPuntos(data);
                   System.out.println("Equipo con más puntos: " + equipoMasPuntos);
   
                   String[] top3EquiposGoles = DataAnalysis.top3EquiposConMasGoles(data);
                   System.out.println("Top 3 equipos con más goles:");
                   for (String equipo : top3EquiposGoles) {
                       System.out.println(equipo);
                   }
   
                   String[] top3MinutosGoles = DataAnalysis.top3MinutoConMasGoles(data);
                   System.out.println("Top 3 minutos con más goles:");
                   for (String minuto : top3MinutosGoles) {
                       System.out.println(minuto);
                   }
                  
                // Interacción con el usuario para insertar en la tabla hash
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("Ingrese la operación que desea Realizar:");
                    System.out.println("***PARA BUSCAR ES NECESARIO TENER INGRESADO DATOS A LAS TABLAS HASH***");
                    System.out.println("1. Buscar equipo");
                    System.out.println("2. Buscar jugador");
                    System.out.println("***INSERTE DATOS PARA PODER UTILIZAR EL MODULO DE BUSQUEDA***");
                    System.out.println("3. Insertar equipo en la tabla hash");
                    System.out.println("4. Insertar jugador en la tabla hash");
                    System.out.println("5. Guardar árbol en archivo");
                    System.out.println("6. Salir");

                    int option = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    if (option == 1) {
                        System.out.print("Ingrese el nombre del equipo a buscar: ");
                        String equipo = scanner.nextLine().trim();
                        int keyEquipo = generateAsciiKey(equipo);
                        boolean found = equipoHashTable.search(equipo, keyEquipo);
                        System.out.println("Equipo " + equipo + (found ? " encontrado en el Arbol Binario" : " no encontrado"));
                    } else if (option == 2) {
                        System.out.print("Ingrese el nombre del jugador a buscar: ");
                        String jugador = scanner.nextLine().trim();
                        int keyJugador = generateAsciiKey(jugador);
                        boolean found = jugadorHashTable.search(jugador, keyJugador);
                        System.out.println("Jugador " + jugador + (found ? " encontrado en el Arbol Binario" : " no encontrado"));
                    } else if (option == 3) {
                        System.out.print("Ingrese el nombre del equipo a insertar: ");
                        String equipo = scanner.nextLine().trim();
                        int keyEquipo = generateAsciiKey(equipo);
                        equipoHashTable.add(equipo, keyEquipo);
                        System.out.println("Equipo " + equipo + " insertado con clave " + keyEquipo);
                    } else if (option == 4) {
                        System.out.print("Ingrese el nombre del jugador a insertar: ");
                        String jugador = scanner.nextLine().trim();
                        int keyJugador = generateAsciiKey(jugador);
                        jugadorHashTable.add(jugador, keyJugador);
                        System.out.println("Jugador " + jugador + " insertado con clave " + keyJugador);
                    } else if (option == 5) {
                        System.out.println("Seleccione el tipo de recorrido:");
                        System.out.println("1. In-Order");
                        System.out.println("2. Pre-Order");
                        System.out.println("3. Post-Order");

                        int tipoRecorrido = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer

                        System.out.print("Ingrese el nombre del archivo: ");
                        String nombreArchivo = scanner.nextLine().trim();

                        try (Writer writer = new FileWriter(nombreArchivo)) {
                            if (tipoRecorrido == 1) {
                                equipoBinaryTree.inOrder(writer);
                            } else if (tipoRecorrido == 2) {
                                equipoBinaryTree.preOrder(writer);
                            } else if (tipoRecorrido == 3) {
                                equipoBinaryTree.postOrder(writer);
                            }
                            System.out.println("Árbol guardado en " + nombreArchivo);
                        } catch (IOException e) {
                            System.err.println("Error al guardar el archivo: " + e.getMessage());
                        }
                    } else if (option == 6) {
                        break;
                    } else {
                        System.out.println("Opción no válida. Intente de nuevo.");
                    }
                }
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int generateAsciiKey(String value) {
        int suma = 0;
        for (int i = 0; i < value.length(); i++) {
            suma += (int) value.charAt(i);
        }
        return suma;
    }
}