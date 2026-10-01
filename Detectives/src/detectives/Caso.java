/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package detectives;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Hesse
 */
public class Caso 
{
    private String nombreCaso;
    private String codigo;
    private String nombreDetective;
    private Ubicacion[] ubicaciones;
    private List<Pista> pistas;

    public Caso(String nombreCaso, String codigo, String nombreDetective)
    {
        setNombreCaso(nombreCaso);
        setCodigo(codigo);
        setNombreDetective(nombreDetective);

        this.ubicaciones = new Ubicacion[5];
        this.pistas = new ArrayList<>();
    }

    public String getNombreCaso()
    {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso)
    {
        if (nombreCaso == null || nombreCaso.isEmpty())
        {
            throw new IllegalArgumentException(
                "El nombre del caso no puede ser nulo o vacio."
            );
        }

        this.nombreCaso = nombreCaso;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        if (codigo == null || codigo.isEmpty())
        {
            throw new IllegalArgumentException(
                "El codigo del caso no puede ser nulo o vacio."
            );
        }

        this.codigo = codigo;
    }

    public String getNombreDetective()
    {
        return nombreDetective;
    }

    public void setNombreDetective(String nombreDetective)
    {
        if (nombreDetective == null || nombreDetective.isEmpty())
        {
            throw new IllegalArgumentException(
                "El nombre del detective no puede ser nulo o vacio."
            );
        }

        this.nombreDetective = nombreDetective;
    }

    public Ubicacion[] getUbicaciones()
    {
        return ubicaciones;
    }

    public void registrarUbicacion(Ubicacion ubicacion, int posicion)
    {
        if (posicion < 0 || posicion >= this.ubicaciones.length)
        {
            throw new IllegalArgumentException(
                "Posicion invalida para el arreglo de ubicaciones."
            );
        }

        if (ubicacion == null)
        {
            throw new IllegalArgumentException(
                "La ubicacion no puede ser nula."
            );
        }

        if (this.ubicaciones[posicion] != null)
        {
            throw new IllegalArgumentException(
                "La posicion " + posicion + " ya esta ocupada por otra ubicacion."
            );
        }

        
        this.ubicaciones[posicion - 1] = ubicacion;
    }

    public List<Pista> getPistas()
    {
        return pistas;
    }

    public void setPistas(List<Pista> pistas)
    {
        if (pistas == null)
        {
            throw new IllegalArgumentException(
                "La lista de pistas no puede ser nula."
            );
        }

        List<Pista> nuevasPistas = new ArrayList<>();

        for (Pista pista : pistas)
        {
            if (pista == null)
            {
                throw new IllegalArgumentException(
                    "La lista no puede contener pistas nulas."
                );
            }

            for (Pista p : nuevasPistas)
            {
                if (p.getCodigo().equals(pista.getCodigo()))
                {
                    throw new IllegalArgumentException(
                        "Ya existe una pista con el codigo "
                        + pista.getCodigo() + "."
                    );
                }
            }

            nuevasPistas.add(pista);
        }
        
        this.pistas = nuevasPistas;

    }

    public void consultarPista()
    {
        if (pistas.isEmpty())
        {
            System.out.println("No hay pistas registradas para el caso " + nombreCaso + ".");
            return;
        }

        System.out.println("Pistas del caso " + nombreCaso + ":");

        for (Pista pista : pistas)
        {
            pista.mostrarInformacion();
            System.out.println();
        }
    }

    public void registrarPista(Pista pista)
    {
        if (pista == null)
        {
            throw new IllegalArgumentException(
                "La pista no puede ser nula."
            );
        }

        for (Pista p : pistas)
        {
            if (p.getCodigo().equals(pista.getCodigo()))
            {
                throw new IllegalArgumentException(
                    "Ya existe una pista con el código "
                    + pista.getCodigo() + "."
                );
            }
        }

        pistas.add(pista);
    }

    public Pista buscarPista(String codigoPista)
    {
        if (codigoPista == null || codigoPista.isEmpty())
        {
            throw new IllegalArgumentException(
                "El codigo de la pista no puede ser nulo o vacío."
            );
        }

        for (Pista pista : pistas)
        {
            if (pista.getCodigo().equals(codigoPista))
            {
                return pista;
            }
        }

        return null;
    }

    public void modificarPista(String codigoPista, String nuevoCodigo, String descripcion, String tipoEvidencia, Integer nivelImportancia, Integer nivelConfiabilidad)
    {
        Pista pista = buscarPista(codigoPista);

        if (pista == null)
        {
            System.out.println("No se encontro ninguna pista con el codigo " + codigoPista + ".");
            return;
        }

        for (Pista p : pistas)
        {
            if (p != pista && p.getCodigo().equals(nuevoCodigo))
            {
                throw new IllegalArgumentException(
                    "Ya existe una pista con el codigo " + nuevoCodigo + "."
                );
            }
        }

        // Validar todos los datos antes de cambiar la pista registrada.
        Pista datosValidados = new Pista(
            nuevoCodigo,
            descripcion,
            tipoEvidencia,
            nivelImportancia,
            nivelConfiabilidad
        );

        pista.setCodigo(datosValidados.getCodigo());
        pista.setDescripcion(datosValidados.getDescripcion());
        pista.setTipoEvidencia(datosValidados.getTipoEvidencia());
        pista.setNivelImportancia(datosValidados.getNivelImportancia());
        pista.setNivelConfiabilidad(datosValidados.getNivelConfiabilidad());

        System.out.println("La pista ha sido modificada correctamente.");
    }

    public void descartarPista(String codigoPista)
    {
        if (codigoPista == null || codigoPista.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "El codigo de la pista no puede ser nulo o vacío."
            );
        }

        boolean pistaEncontrada = false;

        for (int i = 0; i < pistas.size(); i++)
        {
            if (pistas.get(i).getCodigo().equals(codigoPista))
            {
                pistas.remove(i);
                pistaEncontrada = true;

                System.out.println( "La pista con código " + codigoPista + " ha sido descartada del caso " + nombreCaso + ".");

                break;
            }
        }

        if (!pistaEncontrada)
        {
            System.out.println("No se encontro ninguna pista con el codigo" + codigoPista + " en el caso " + nombreCaso + ".");
        }
    }

    public void mostrarEstadisticas()
    {
        int cantidadUbicaciones = 0;
        Ubicacion ubicacionMayorRiesgo = null;

        for (Ubicacion ubicacion : ubicaciones)
        {
            if (ubicacion != null)
            {
                cantidadUbicaciones++;

                if (ubicacionMayorRiesgo == null || ubicacion.getNivelRiesgo() > ubicacionMayorRiesgo.getNivelRiesgo())
                {
                    ubicacionMayorRiesgo = ubicacion;
                }
            }
        }

        int espaciosDisponibles = ubicaciones.length - cantidadUbicaciones;
        
        //Espacios disponibles y registrados en el array de las ubicaciones
        System.out.println("Estadisticas del caso " + nombreCaso + ":");
        System.out.println("Ubicaciones registradas: " + cantidadUbicaciones);
        System.out.println("Espacios disponibles: " + espaciosDisponibles);
        
        //Mostrar la ubicacion que tiene el nivel de riesgo mas grande
        if (ubicacionMayorRiesgo != null)
        {
            System.out.println("\nUbicacion con mayor nivel de riesgo:");
            ubicacionMayorRiesgo.mostrarInformacion();
        }
        else
        {
            System.out.println("No hay ubicaciones para determinar el mayor riesgo.");
        }
        
        //Muestra la cantidad de pistas que hay registradas
        System.out.println("Pistas registradas: " + pistas.size());

        if (pistas.isEmpty())
        {
            System.out.println("No hay pistas para calcular los niveles maximos ni el promedio.");
            return;
        }
        
        //Al recorrer la lista, econtramos la pista mas importante y la mas confiable
        Pista pistaMayorImportancia = pistas.get(0);
        Pista pistaMayorConfiabilidad = pistas.get(0);
        double sumaImportancia = 0;

        for (Pista pista : pistas)
        {
            if (pista.getNivelImportancia() > pistaMayorImportancia.getNivelImportancia())
            {
                pistaMayorImportancia = pista;
            }

            if (pista.getNivelConfiabilidad() > pistaMayorConfiabilidad.getNivelConfiabilidad())
            {
                pistaMayorConfiabilidad = pista;
            }

            sumaImportancia += pista.getNivelImportancia();
        }
        
        //Sacar el promedio de importancia de las pistas
        double promedioImportancia = sumaImportancia / pistas.size();

        System.out.println("\nPista con mayor nivel de importancia:");
        pistaMayorImportancia.mostrarInformacion();

        System.out.println("\nPista con mayor nivel de confiabilidad:");
        pistaMayorConfiabilidad.mostrarInformacion();

        System.out.println("\nPromedio del nivel de importancia: " + promedioImportancia);
    }
    
    public int buscarPosicionUbicacion(String codigoUbicacion)
    {
        if (codigoUbicacion == null || codigoUbicacion.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "El codigo de la ubicacion no puede ser nulo o vacio."
            );
        }

        for (int i = 0; i < ubicaciones.length; i++)
        {
            if (ubicaciones[i] != null)
            {
                if (ubicaciones[i].getCodigo().equals(codigoUbicacion))
                {
                    return i;
                }
            }
        }

        return -1;
    }
}
