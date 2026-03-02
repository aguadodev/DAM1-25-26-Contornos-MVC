package boundary;

import control.FaltasController;
import entity.FaltaAsistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsolaFaltasUI {

    private FaltasController controller;
    private Scanner scanner;

    public ConsolaFaltasUI(FaltasController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void solicitarDatosFalta() {

        try {
            System.out.print("ID del alumno: ");
            int alumnoId = Integer.parseInt(scanner.nextLine());

            System.out.print("Código del módulo: ");
            String moduloCodigo = scanner.nextLine();

            System.out.print("Fecha (dd/MM/yyyy): ");
            String fechaTexto = scanner.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = sdf.parse(fechaTexto);

            controller.registrarFalta(alumnoId, moduloCodigo, fecha);

            System.out.println("Falta registrada correctamente.");

        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarFaltasAlumno() {

        try {
            System.out.print("ID del alumno: ");
            int alumnoId = Integer.parseInt(scanner.nextLine());

            List<FaltaAsistencia> faltas =
                    controller.obtenerFaltasAlumno(alumnoId);

            if (faltas.isEmpty()) {
                System.out.println("No hay faltas registradas.");
                return;
            }

            for (FaltaAsistencia f : faltas) {
                System.out.println(f);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarResumenFaltas(int alumnoId) {

        try {
            int total = controller.calcularTotalFaltas(alumnoId);
            System.out.println("Total de faltas: " + total);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}