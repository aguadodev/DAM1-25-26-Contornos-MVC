package entity;

import java.util.ArrayList;
import java.util.List;

public class Ciclo {

    private String codigo;
    private String nombre;
    private int duracionHoras;

    private List<Modulo> modulos;

    public Ciclo(String codigo, String nombre, int duracionHoras) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
        this.modulos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void agregarModulo(Modulo modulo) {
        modulos.add(modulo);
    }

    public Modulo obtenerModulo(String codigo) {

        for (Modulo m : modulos) {
            if (m.getCodigo().equalsIgnoreCase(codigo)) {
                return m;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Ciclo{codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", duracionHoras=" + duracionHoras +
                '}';
    }
}