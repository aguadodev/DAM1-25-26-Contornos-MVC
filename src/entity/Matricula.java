package entity;

import java.time.LocalDate;

public class Matricula {

    private int id;
    private LocalDate fecha;
    private Alumno alumno;
    private Ciclo ciclo;

    public Matricula(int id, Alumno alumno, Ciclo ciclo) {
        this.id = id;
        this.fecha = LocalDate.now();
        this.alumno = alumno;
        this.ciclo = ciclo;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    @Override
    public String toString() {
        return "Matricula{id=" + id +
                ", fecha=" + fecha +
                ", alumno=" + alumno.getNombreCompleto() +
                ", ciclo=" + ciclo.getNombre() +
                '}';
    }
}