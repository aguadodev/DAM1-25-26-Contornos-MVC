package entity;

import java.util.Date;

public class FaltaAsistencia {

    private static int contador = 1;

    private int id;
    private Date fecha;
    private boolean justificada;
    private Modulo modulo;

    public FaltaAsistencia(Date fecha, Modulo modulo) {
        this.id = contador++;
        this.fecha = fecha;
        this.modulo = modulo;
        this.justificada = false;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void justificar() {
        this.justificada = true;
    }

    public boolean estaJustificada() {
        return justificada;
    }

    @Override
    public String toString() {
        return "Falta ID: " + id +
                " | Fecha: " + fecha +
                " | Módulo: " + modulo.getNombre() +
                " | Justificada: " + justificada;
    }
}