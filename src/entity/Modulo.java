package entity;

import java.util.Objects;

public class Modulo {

    private String codigo;
    private String nombre;
    private int horasTotales;

    public Modulo(String codigo, String nombre, int horasTotales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horasTotales = horasTotales;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHorasTotales() {
        return horasTotales;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + horasTotales + "h)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modulo)) return false;
        Modulo modulo = (Modulo) o;
        return Objects.equals(codigo, modulo.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}