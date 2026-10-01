/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package detectives;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Daniel Hesse
 */
public class Detectives 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de detectives!!\n");

        Caso casoActual = null;

        while (casoActual == null)
        {
            try
            {
                System.out.print("Ingresa el nombre del caso actual: ");
                String nombreCaso = scan.nextLine();

                System.out.print("Ingresa el codigo de identificacion del caso: ");
                String codigoCaso = scan.nextLine();

                System.out.print("Ingresa el nombre del detective responsable del caso: ");
                String nombreDetective = scan.nextLine();

                casoActual = new Caso(
                    nombreCaso,
                    codigoCaso,
                    nombreDetective
                );
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Ingresa nuevamente los datos del caso.\n");
            }
        }

        int opcion = 0;

    try
    {
        do
        {
            System.out.println("\nMenu de opciones: ");
            System.out.println("1. Registrar ubicacion.");
            System.out.println("2. Consultar ubicaciones registradas.");
            System.out.println("3. Consultar ubicacion especifica.");
            System.out.println("4. Modificar nivel de riesgo de una ubicacion.");
            System.out.println("5. Modificar el estado de una ubicacion.");
            System.out.println("6. Descartar ubicacion.");
            System.out.println("7. Registrar pista.");
            System.out.println("8. Consultar pistas registradas.");
            System.out.println("9. Consultar pista especifica.");
            System.out.println("10. Modificar una pista.");
            System.out.println("11. Descartar pista.");
            System.out.println("12. Mostrar estadisticas del caso.");
            System.out.println("13. Salir del sistema.");

            try
            {
                System.out.print(
                    "\nIngresa el numero de la opcion que deseas realizar: "
                );

                opcion = scan.nextInt();
                scan.nextLine();

                if (opcion < 1 || opcion > 13)
                {
                    throw new IllegalArgumentException(
                        "Opcion invalida. Ingresa un numero entre 1 y 13."
                    );
                }

                if (opcion == 1)
                {
                    System.out.print("Ingresa la posicion donde deseas registrar la ubicacion (1 a 5): ");

                    int posicion = scan.nextInt();
                    scan.nextLine();

                    if (posicion < 1
                        || posicion >= casoActual.getUbicaciones().length)
                    {
                        throw new IllegalArgumentException(
                            "La posicion debe estar entre 1 y 5."
                        );
                    }

                    if (casoActual.getUbicaciones()[posicion] != null)
                    {
                        throw new IllegalArgumentException(
                            "La posicion seleccionada ya esta ocupada."
                        );
                    }

                    System.out.print("Ingresa el codigo de la ubicacion: ");
                    String codigoUbicacion = scan.nextLine();

                    System.out.print("Ingresa el nombre de la ubicacion: ");
                    String nombreUbicacion = scan.nextLine();

                    System.out.print("Ingresa la direccion de la ubicacion: ");
                    String direccionUbicacion = scan.nextLine();

                    System.out.print( "Ingresa el nivel de riesgo de la ubicacion (1-10): ");

                    int nivelRiesgo = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Ingresa el estado de la ubicacion (true para activa/false para inactiva): ");

                    boolean estadoUbicacion = scan.nextBoolean();
                    scan.nextLine();

                    Ubicacion nuevaUbicacion = new Ubicacion(
                        codigoUbicacion,
                        nombreUbicacion,
                        direccionUbicacion,
                        nivelRiesgo,
                        estadoUbicacion
                    );

                    casoActual.registrarUbicacion(nuevaUbicacion, posicion);

                    System.out.println(
                        "La ubicacion fue registrada correctamente."
                    );
                }
                else if(opcion == 2)
                {
                     Ubicacion.mostrarUbicacionesRegistradas(casoActual.getUbicaciones());
                }
                else if(opcion == 3)
                {
                    System.out.print("Ingresa la posicion de la ubicacion (0 a 4): ");
                    int posicion = scan.nextInt();
                    scan.nextLine();

                    Ubicacion.mostrarUbicacionesEspecificas(casoActual.getUbicaciones(), posicion);
                }
                else if (opcion == 4)
                {
                    System.out.print("Ingresa el codigo de la ubicacion que deseas modificar: ");
                    String codigoUbicacion = scan.nextLine();

                    if (codigoUbicacion.isEmpty())
                    {
                        throw new IllegalArgumentException(
                            "El codigo de la ubicacion no puede estar vacio."
                        );
                    }

                    Ubicacion[] ubicaciones = casoActual.getUbicaciones();
                    int posicion = -1;

                    for (int i = 0; i < ubicaciones.length; i++)
                    {
                        if (ubicaciones[i] != null)
                        {
                            if (ubicaciones[i].getCodigo().equals(codigoUbicacion))
                            {
                                posicion = i;
                                break;
                            }
                        }
                    }

                    if (posicion == -1)
                    {
                        System.out.println("No se encontro una ubicacion con ese codigo.");
                    }
                    else
                    {
                        System.out.println("\nDatos actuales de la ubicacion:");
                        ubicaciones[posicion].mostrarInformacion();

                        System.out.print("\nIngresa el nuevo nivel de riesgo (1 a 10): ");
                        int nivelRiesgo = scan.nextInt();
                        scan.nextLine();

                        Ubicacion.modificarUbicacion(
                            ubicaciones,
                            posicion,
                            nivelRiesgo,
                            ubicaciones[posicion].getEstado()
                        );

                        System.out.println("\nDatos actualizados:");
                        ubicaciones[posicion].mostrarInformacion();
                    }
                }
                else if (opcion == 5)
                {
                    System.out.print("Ingresa el codigo de la ubicacion que deseas modificar: ");
                    String codigoUbicacion = scan.nextLine();

                    if (codigoUbicacion.trim().isEmpty())
                    {
                        throw new IllegalArgumentException(
                            "El codigo de la ubicacion no puede estar vacio."
                        );
                    }

                    Ubicacion[] ubicaciones = casoActual.getUbicaciones();
                    int posicion = -1;

                    for (int i = 0; i < ubicaciones.length; i++)
                    {
                        if (ubicaciones[i] != null)
                        {
                            if (ubicaciones[i].getCodigo().equals(codigoUbicacion))
                            {
                                posicion = i;
                                break;
                            }
                        }
                    }

                    if (posicion == -1)
                    {
                        System.out.println("No se encontro una ubicacion con ese codigo.");
                    }
                    else
                    {
                        System.out.println("\nDatos actuales de la ubicacion:");
                        ubicaciones[posicion].mostrarInformacion();

                        System.out.print("\nIngresa el nuevo estado (true/false): ");
                        boolean estado = scan.nextBoolean();
                        scan.nextLine();

                        Ubicacion.modificarUbicacion(
                            ubicaciones,
                            posicion,
                            ubicaciones[posicion].getNivelRiesgo(),
                            estado
                        );

                        System.out.println("\nDatos actualizados:");
                        ubicaciones[posicion].mostrarInformacion();
                    }
                }
                else if (opcion == 6)
                {
                    System.out.print("Ingresa el codigo de la ubicacion que deseas descartar: ");
                    String codigoUbicacion = scan.nextLine();

                    if (codigoUbicacion.isEmpty())
                    {
                        throw new IllegalArgumentException(
                            "El codigo de la ubicacion no puede estar vacio."
                        );
                    }

                    Ubicacion[] ubicaciones = casoActual.getUbicaciones();
                    int posicion = -1;

                    for (int i = 0; i < ubicaciones.length; i++)
                    {
                        if (ubicaciones[i] != null)
                        {
                            if (ubicaciones[i].getCodigo().equals(codigoUbicacion))
                            {
                                posicion = i;
                                break;
                            }
                        }
                    }

                    if (posicion == -1)
                    {
                        System.out.println("No se encontro una ubicacion con ese codigo.");
                    }
                    else
                    {
                        System.out.println("\nDatos de la ubicacion que se descartara:");
                        ubicaciones[posicion].mostrarInformacion();

                        Ubicacion.descartarUbicacion(ubicaciones, posicion);
                    }
                }
                else if (opcion == 7)
                {
                    System.out.print("Ingresa el codigo de la pista: ");
                    String codigoPista = scan.nextLine();

                    System.out.print("Ingresa la descripcion de la pista: ");
                    String descripcion = scan.nextLine();

                    System.out.print("Ingresa el tipo de evidencia: ");
                    String tipoEvidencia = scan.nextLine();

                    System.out.print("Ingresa el nivel de importancia (1 a 10): ");
                    int nivelImportancia = scan.nextInt();
                    scan.nextLine();

                    System.out.print("Ingresa el nivel de confiabilidad (0 a 100): ");
                    int nivelConfiabilidad = scan.nextInt();
                    scan.nextLine();

                    Pista nuevaPista = new Pista(
                        codigoPista,
                        descripcion,
                        tipoEvidencia,
                        nivelImportancia,
                        nivelConfiabilidad
                    );

                    casoActual.registrarPista(nuevaPista);

                    System.out.println("\nLa pista fue registrada correctamente.");
                    nuevaPista.mostrarInformacion();
                }
                else if (opcion == 8)
                {
                    casoActual.consultarPista();
                }
                else if (opcion == 9)
                {
                    System.out.print("Ingresa el codigo de la pista que deseas consultar: ");
                    String codigoPista = scan.nextLine();

                    Pista pistaEncontrada = casoActual.buscarPista(codigoPista);

                    if (pistaEncontrada == null)
                    {
                        System.out.println("No se encontro una pista con ese codigo.");
                    }
                    else
                    {
                        System.out.println("\nDatos de la pista:");
                        pistaEncontrada.mostrarInformacion();
                    }
                }
                else if (opcion == 10)
                {
                    System.out.print("Ingresa el codigo de la pista que deseas modificar: ");
                    String codigoPista = scan.nextLine();

                    Pista pistaEncontrada = casoActual.buscarPista(codigoPista);

                    if (pistaEncontrada == null)
                    {
                        System.out.println("No se encontro una pista con ese codigo.");
                    }
                    else
                    {
                        System.out.println("\nDatos actuales de la pista:");
                        pistaEncontrada.mostrarInformacion();

                        System.out.print(
                            "\nIngresa el nuevo codigo (repite el actual para conservarlo): "
                        );
                        String nuevoCodigo = scan.nextLine();

                        System.out.print("Ingresa la nueva descripcion: ");
                        String descripcion = scan.nextLine();

                        System.out.print("Ingresa el nuevo tipo de evidencia: ");
                        String tipoEvidencia = scan.nextLine();

                        System.out.print("Ingresa el nuevo nivel de importancia (1 a 10): ");
                        int nivelImportancia = scan.nextInt();
                        scan.nextLine();

                        System.out.print("Ingresa el nuevo nivel de confiabilidad (0 a 100): ");
                        int nivelConfiabilidad = scan.nextInt();
                        scan.nextLine();

                        casoActual.modificarPista(
                            codigoPista,
                            nuevoCodigo,
                            descripcion,
                            tipoEvidencia,
                            nivelImportancia,
                            nivelConfiabilidad
                        );

                        System.out.println("\nDatos actualizados:");
                        pistaEncontrada.mostrarInformacion();
                    }
                }
                else if (opcion == 11)
                {
                    System.out.print("Ingresa el codigo de la pista que deseas descartar: ");
                    String codigoPista = scan.nextLine();

                    casoActual.descartarPista(codigoPista);
                }
                else if (opcion == 12)
                {
                    System.out.println("\nInformacion del caso:");
                    System.out.println("Nombre: " + casoActual.getNombreCaso());
                    System.out.println("Codigo: " + casoActual.getCodigo());
                    System.out.println("Detective responsable: " + casoActual.getNombreDetective());
                    System.out.println();

                    casoActual.mostrarEstadisticas();
                }
                else if (opcion == 13)
                {
                    System.out.println("Gracias por utilizar el sistema.");
                }

            }
            catch (InputMismatchException e)
            {
                System.out.println(
                    "Entrada invalida. Ingresa un entero para opciones, "
                    + "posiciones y riesgo, o true/false para el estado."
                );

                scan.nextLine();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Error: " + e.getMessage());
            }

            } while (opcion != 13);
        }
        finally
        {
            scan.close();
        }

    }
}
