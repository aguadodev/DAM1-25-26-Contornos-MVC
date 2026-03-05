package boundary;

import control.MatriculaController;
import entity.Alumno;
import entity.Ciclo;

import java.util.List;
import java.util.Scanner;

public class ConsolaMatriculaUI {

    private MatriculaController controller = new MatriculaController();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {

        int opcion;

        do {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearAlumno();
                    break;
                case 2:
                    matricularAlumno();
                    break;
                case 3:
                    listarAlumnos();
                    break;
                case 4:
                    listarAlumnosPorCiclo();
                    break;
                case 5:
                    desmatricularAlumno();
                    break;
                case 0:
                    controller.guardarDatos();
                    System.out.println("Datos guardados.");
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n--- GESTIÓN MATRÍCULA ---");
        System.out.println("1. Crear alumno");
        System.out.println("2. Matricular alumno");
        System.out.println("3. Listar todos los alumnos");
        System.out.println("4. Listar alumnos por ciclo");
        System.out.println("5. Desmatricular alumno");
        System.out.println("0. Salir");
        System.out.print("Seleccione opción: ");
    }

    private void crearAlumno() {

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();

        System.out.print("DNI: ");
        String dni = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Alumno alumno = controller.crearAlumno(nombre, apellidos, dni, email);

        System.out.println("Alumno creado con ID: " + alumno.getId());
    }

    private void matricularAlumno() {

        System.out.print("ID del alumno: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Ciclos disponibles:");

        for (Ciclo c : controller.getCiclos()) {
            System.out.println("- " + c.getCodigo() + " : " + c.getNombre());
        }

        System.out.print("Código del ciclo: ");
        String codigo = scanner.nextLine();

        boolean exito = controller.matricularAlumno(id, codigo);

        if (exito) {
            System.out.println("Alumno matriculado correctamente.");
        } else {
            System.out.println("Error: alumno o ciclo no encontrado.");
        }
    }

    private void listarAlumnos() {
        controller.getAlumnos().forEach(System.out::println);
    }

    private void listarAlumnosPorCiclo() {

        System.out.println("Ciclos disponibles:");

        for (Ciclo c : controller.getCiclos()) {
            System.out.println("- " + c.getCodigo() + " : " + c.getNombre());
        }

        System.out.print("Código del ciclo: ");
        String codigo = scanner.nextLine();

        List<Alumno> alumnos = controller.obtenerAlumnosPorCiclo(codigo);

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos matriculados en este ciclo.");
            return;
        }

        System.out.println("\nAlumnos matriculados:");

        for (Alumno a : alumnos) {
            System.out.println(a.getId() + " - " + a.getNombreCompleto());
        }
    }

    private void desmatricularAlumno() {

        System.out.print("ID del alumno a desmatricular: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean exito = controller.desmatricularAlumno(id);

        if (exito) {
            System.out.println("Alumno desmatriculado correctamente.");
        } else {
            System.out.println("No se encontró el alumno o no estaba matriculado.");
        }
    }

    public static void main(String[] args) {

        ConsolaMatriculaUI app = new ConsolaMatriculaUI();

        app.controller.cargarDatos();

        app.iniciar();
    }
}