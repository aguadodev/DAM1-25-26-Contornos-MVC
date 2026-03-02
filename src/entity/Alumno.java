package entity;

public class Alumno {

    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private Matricula matricula;

    public Alumno(int id, String nombre, String apellidos, String dni, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void matricular(Matricula matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Alumno{id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}