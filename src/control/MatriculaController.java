package control;

import entity.Alumno;
import entity.Ciclo;
import entity.Matricula;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatriculaController {

    private List<Alumno> alumnos = new ArrayList<>();
    private List<Ciclo> ciclos = new ArrayList<>();
    private int contadorAlumno = 1;
    private int contadorMatricula = 1;

    public MatriculaController() {

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

    public List<Alumno> obtenerAlumnosPorCiclo(String codigoCiclo) {

        List<Alumno> resultado = new ArrayList<>();

        for (Alumno a : alumnos) {

            if (a.getMatricula() != null &&
                    a.getMatricula().getCiclo().getCodigo().equalsIgnoreCase(codigoCiclo)) {

                resultado.add(a);
            }
        }

        return resultado;
    }

    public boolean desmatricularAlumno(int alumnoId) {

        Alumno alumno = buscarAlumno(alumnoId);

        if (alumno == null || alumno.getMatricula() == null) {
            return false;
        }

        alumno.matricular(null);

        return true;
    }

    private Alumno buscarAlumno(int id) {

        for (Alumno a : alumnos) {
            if (a.getId() == id) {
                return a;
            }
        }

        return null;
    }

    public void guardarDatos() {

        guardarAlumnos();
        guardarCiclos();

    }

private void guardarAlumnos() {

    try (PrintWriter pw = new PrintWriter("Alumno.tsv")) {

        for (Alumno a : alumnos) {

            pw.println(
                    a.getId() + "\t" +
                    a.getNombreCompleto() + "\t" +
                    a.getMatricula()
            );

        }

    } catch (Exception e) {
        System.out.println("Error guardando alumnos: " + e.getMessage());
    }

}
private void guardarCiclos() {

    try (PrintWriter pw = new PrintWriter("Ciclo.tsv")) {

        for (Ciclo c : ciclos) {

            pw.println(
                    c.getCodigo() + "\t" +
                    c.getNombre() + "\t" +
                    c.getDuracionHoras()
            );

        }

    } catch (Exception e) {
        System.out.println("Error guardando ciclos: " + e.getMessage());
    }

}

public void cargarDatos() {

    cargarCiclos();
    cargarAlumnos();

}

private void cargarCiclos() {

    File f = new File("Ciclo.tsv");

    if (!f.exists()) {
        // Ciclos de ejemplo
        ciclos.add(new Ciclo("DAM", "Desarrollo de Aplicaciones Multiplataforma", 2000));
        ciclos.add(new Ciclo("DAW", "Desarrollo de Aplicaciones Web", 2000));        
        return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(f))) {

        String linea;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split("\t");

            String codigo = datos[0];
            String nombre = datos[1];
            int horas = Integer.parseInt(datos[2]);

            ciclos.add(new Ciclo(codigo, nombre, horas));
        }

    } catch (Exception e) {
        System.out.println("Error cargando ciclos: " + e.getMessage());
    }

}

private void cargarAlumnos() {

    File f = new File("Alumno.tsv");

    if (!f.exists()) {
        return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(f))) {

        String linea;

        while ((linea = br.readLine()) != null) {

            String[] datos = linea.split("\t");

            int id = Integer.parseInt(datos[0]);
            String nombre = datos[1];

            Alumno a = new Alumno(id, nombre, "", "", "");

            alumnos.add(a);
        }

    } catch (Exception e) {
        System.out.println("Error cargando alumnos: " + e.getMessage());
    }

}

}
