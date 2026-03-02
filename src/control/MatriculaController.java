package control;

import entity.Alumno;
import entity.Ciclo;
import entity.Matricula;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatriculaController {

    private List<Alumno> alumnos = new ArrayList<>();
    private List<Ciclo> ciclos = new ArrayList<>();
    private int contadorAlumno = 1;
    private int contadorMatricula = 1;

    public MatriculaController() {
        // Ciclos de ejemplo
        ciclos.add(new Ciclo("DAM", "Desarrollo de Aplicaciones Multiplataforma", 2000));
        ciclos.add(new Ciclo("DAW", "Desarrollo de Aplicaciones Web", 2000));
    }

    public Alumno crearAlumno(String nombre, String apellidos, String dni, String email) {
        Alumno alumno = new Alumno(contadorAlumno++, nombre, apellidos, dni, email);
        alumnos.add(alumno);
        return alumno;
    }

    public boolean matricularAlumno(int alumnoId, String codigoCiclo) {
        Optional<Alumno> alumnoOpt = alumnos.stream()
                .filter(a -> a.getId() == alumnoId)
                .findFirst();

        Optional<Ciclo> cicloOpt = ciclos.stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigoCiclo))
                .findFirst();

        if (alumnoOpt.isPresent() && cicloOpt.isPresent()) {
            Matricula matricula = new Matricula(contadorMatricula++, alumnoOpt.get(), cicloOpt.get());
            alumnoOpt.get().matricular(matricula);
            return true;
        }

        return false;
    }

    public List<Ciclo> getCiclos() {
        return ciclos;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }
}
