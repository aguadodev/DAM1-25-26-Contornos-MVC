package control;

import entity.Alumno;
import entity.FaltaAsistencia;
import entity.Modulo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FaltasController {

    private List<Alumno> alumnos;

    public FaltasController(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void registrarFalta(int alumnoId, String moduloCodigo, Date fecha) {

        Alumno alumno = buscarAlumno(alumnoId);
        if (alumno == null) {
            throw new IllegalArgumentException("Alumno no encontrado");
        }

        Modulo modulo = alumno.getMatricula()
                               .getCiclo()
                               .obtenerModulo(moduloCodigo);

        if (modulo == null) {
            throw new IllegalArgumentException("Módulo no encontrado");
        }

        FaltaAsistencia falta = new FaltaAsistencia(fecha, modulo);
        alumno.agregarFalta(falta);
    }

    public void justificarFalta(int faltaId) {
        for (Alumno alumno : alumnos) {
            for (FaltaAsistencia falta : alumno.getFaltas()) {
                if (falta.getId() == faltaId) {
                    falta.justificar();
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Falta no encontrada");
    }

    public List<FaltaAsistencia> obtenerFaltasAlumno(int alumnoId) {

        Alumno alumno = buscarAlumno(alumnoId);
        if (alumno == null) {
            throw new IllegalArgumentException("Alumno no encontrado");
        }

        return alumno.getFaltas();
    }

    public int calcularTotalFaltas(int alumnoId) {

        Alumno alumno = buscarAlumno(alumnoId);
        if (alumno == null) {
            throw new IllegalArgumentException("Alumno no encontrado");
        }

        return alumno.calcularTotalFaltas();
    }

    private Alumno buscarAlumno(int id) {
        for (Alumno a : alumnos) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
}