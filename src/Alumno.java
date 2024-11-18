import java.util.*;

public class Alumno {
    private String nombre;
    private String id;
    private Set<String> cursosCompletados;

    public Alumno(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.cursosCompletados = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public void agregarCursoCompletado(String codigoCurso) {
        cursosCompletados.add(codigoCurso);
    }

    public Set<String> consultarHistorial() {
        return new HashSet<>(cursosCompletados);
    }

    public boolean haCompletadoCurso(String codigoCurso) {
        return cursosCompletados.contains(codigoCurso);
    }
}
