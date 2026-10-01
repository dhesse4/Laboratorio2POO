/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package detectives;

/**
 *
 * @author Daniel Hesse
 */
public class Ubicacion 
{
    private String codigo;
    private String nombre;
    private String direccion;
    private Integer nivelRiesgo;
    private Boolean estado;

    public Ubicacion(String codigo, String nombre, String direccion, Integer nivelRiesgo, Boolean estado)
    {
        setCodigo(codigo);
        setNombre(nombre);
        setDireccion(direccion);
        setNivelRiesgo(nivelRiesgo);
        setEstado(estado);
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        if (codigo == null || codigo.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "El código de la ubicacion no puede ser nulo o vacío."
            );
        }

        this.codigo = codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        if (nombre == null || nombre.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "El nombre de la ubicacion no puede ser nulo o vacío."
            );
        }

        this.nombre = nombre;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        if (direccion == null || direccion.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "La direccion no puede ser nula o vacía."
            );
        }

        this.direccion = direccion;
    }

    public Integer getNivelRiesgo()
    {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(Integer nivelRiesgo)
    {
        if (nivelRiesgo == null || nivelRiesgo < 1 || nivelRiesgo > 10)
        {
            throw new IllegalArgumentException(
                "El nivel de riesgo debe estar entre 1 y 10."
            );
        }

        this.nivelRiesgo = nivelRiesgo;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        if (estado == null)
        {
            throw new IllegalArgumentException(
                "El estado no puede ser nulo."
            );
        }

        this.estado = estado;
    }

    public void mostrarInformacion()
    {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Direccion: " + direccion);
        System.out.println("Nivel de Riesgo: " + nivelRiesgo);
        System.out.println("Estado: " + (estado ? "Activo" : "Inactivo"));
    }

    public static void mostrarUbicacionesRegistradas(Ubicacion[] ubicaciones)
    {
        if (ubicaciones == null)
        {
            System.out.println("El arreglo de ubicaciones no está disponible.");
            return;
        }

        boolean ubicacionEncontrada = false;

        System.out.println("Ubicaciones Registradas:");

        for (int i = 0; i < ubicaciones.length; i++)
        {
            if (ubicaciones[i] != null)
            {
                System.out.println("Codigo: " + ubicaciones[i].getCodigo() + "; Nombre: " + ubicaciones[i].getNombre() + "; Direccion: " + ubicaciones[i].getDireccion() + "; Nivel de Riesgo: " + 
                ubicaciones[i].getNivelRiesgo());
                ubicacionEncontrada = true;
            }
        }

        if (!ubicacionEncontrada)
        {
            System.out.println("No hay ubicaciones registradas.");
        }
    }

    public static void mostrarUbicacionesEspecificas(Ubicacion[] ubicaciones, int posicion)
    {
        if (ubicaciones == null)
        {
            System.out.println("El arreglo de ubicaciones no esta disponible.");
            return;
        }

        if (posicion < 0 || posicion >= ubicaciones.length)
        {
            System.out.println("Posicion invalida para el arreglo de ubicaciones.");
            return;
        }

        Ubicacion ubicacion = ubicaciones[posicion];

        if (ubicacion != null)
        {
            System.out.println("Ubicacion en la posicion " + posicion + ":");
            ubicacion.mostrarInformacion();
        }
        else
        {
            System.out.println("No hay ubicacion registrada en la posicion " + posicion + ".");
        }
    }
    
    public static void modificarUbicacion(Ubicacion[] ubicaciones, int posicion, Integer nivelRiesgo, Boolean estado)
    {
        if (ubicaciones == null)
        {
            System.out.println("El arreglo de ubicaciones no esta disponible.");
            return;
        }

        if (posicion < 0 || posicion >= ubicaciones.length)
        {
            System.out.println("Posicion invalida para el arreglo de ubicaciones.");
            return;
        }

        if (ubicaciones[posicion] == null)
        {
            System.out.println("No hay ubicacion registrada en la posicion " + posicion + ".");
            return;
        }

        // Validar ambos datos antes de modificar la ubicación.
        if (nivelRiesgo == null || nivelRiesgo < 1 || nivelRiesgo > 10)
        {
            throw new IllegalArgumentException(
                "El nivel de riesgo debe estar entre 1 y 10."
            );
        }

        if (estado == null)
        {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }

        ubicaciones[posicion].setNivelRiesgo(nivelRiesgo);
        ubicaciones[posicion].setEstado(estado);

        System.out.println(
            "La ubicacion en la posicion " + posicion + " ha sido modificada."
        );
    }

    public static void descartarUbicacion(Ubicacion[] ubicaciones, int posicion)    
    {
        if (ubicaciones == null)
        {
            System.out.println("El arreglo de ubicaciones no esta disponible.");
            return;
        }

        if (posicion < 0 || posicion >= ubicaciones.length)
        {
            System.out.println("Posicion invalida para el arreglo de ubicaciones.");
            return;
        }

        if (ubicaciones[posicion] != null)
        {
            ubicaciones[posicion] = null;

            System.out.println("Ubicacion en la posicion " + posicion + " ha sido descartada.");
        }
        else
        {
            System.out.println("No hay ubicacion registrada en la posicion " + posicion + " para descartar.");
        }
    }
}
