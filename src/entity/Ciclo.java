package entity;

public class Ciclo {

    private String codigo;
    private String nombre;
    private int duracionHoras;

    public Ciclo(String codigo, String nombre, int duracionHoras) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Ciclo{codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", duracionHoras=" + duracionHoras +
                '}';
    }
}
