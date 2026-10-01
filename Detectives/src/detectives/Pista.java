/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package detectives;

/**
 *
 * @author Daniel Hesse
 */
public class Pista 
{
    private String codigo;
    private String descripcion;
    private String tipoEvidencia;
    private Integer nivelImportancia;
    private Integer nivelConfiabilidad;

    public Pista(String codigo, String descripcion, String tipoEvidencia, Integer nivelImportancia, Integer nivelConfiabilidad)
    {
        //Se ejecutan las excepciones y se comprueban que los valores sean correctos de una vez,
        // sin necesidad de hacerlo nuevamente al ingresar los valores en el main
        setCodigo(codigo);
        setDescripcion(descripcion);
        setTipoEvidencia(tipoEvidencia);
        setNivelImportancia(nivelImportancia);
        setNivelConfiabilidad(nivelConfiabilidad);
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
                "El codigo de la pista no puede ser nulo o vacio."
            );
        }

        this.codigo = codigo;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        if (descripcion == null || descripcion.isEmpty())
        {
            throw new IllegalArgumentException(
                "La descripcion no puede ser nula o vacía."
            );
        }

        this.descripcion = descripcion;
    }

    public String getTipoEvidencia()
    {
        return tipoEvidencia;
    }

    public void setTipoEvidencia(String tipoEvidencia)
    {
        if (tipoEvidencia == null || tipoEvidencia.isEmpty())
        {
            throw new IllegalArgumentException(
                "El tipo de evidencia no puede ser nulo o vacio."
            );
        }

        this.tipoEvidencia = tipoEvidencia;
    }

    public Integer getNivelImportancia()
    {
        return nivelImportancia;
    }

    public void setNivelImportancia(Integer nivelImportancia)
    {
        if (nivelImportancia == null || nivelImportancia < 1 || nivelImportancia > 10)
        {
            throw new IllegalArgumentException(
                "El nivel de importancia debe estar entre 1 y 10."
            );
        }

        this.nivelImportancia = nivelImportancia;
    }

    public Integer getNivelConfiabilidad()
    {
        return nivelConfiabilidad;
    }

    public void setNivelConfiabilidad(Integer nivelConfiabilidad)
    {
        if (nivelConfiabilidad == null || nivelConfiabilidad < 0 || nivelConfiabilidad > 100)
        {
            throw new IllegalArgumentException(
                "El nivel de confiabilidad debe estar entre 0 y 100."
            );
        }

        this.nivelConfiabilidad = nivelConfiabilidad;
    }

    public void mostrarInformacion()
    {
        System.out.println("Codigo: " + codigo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Tipo de evidencia: " + tipoEvidencia);
        System.out.println("Nivel de importancia: " + nivelImportancia);
        System.out.println("Nivel de confiabilidad: " + nivelConfiabilidad);
    }
}
