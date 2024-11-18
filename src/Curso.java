import java.util.*;

import java.util.Set;

public abstract class Curso {
    protected String nombre;
    protected String codigo;
    protected int limiteAlumnos;
    protected Set<Alumno> alumnosInscritos;
    protected Docente docenteAsignado;

    public Curso(String nombre, String codigo, int limiteAlumnos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.limiteAlumnos = limiteAlumnos;
        this.alumnosInscritos = new HashSet<>();
    }

    public abstract boolean validarDocente(Docente docente);

    public void asignarDocente(Docente docente) {
        if (validarDocente(docente)) {
            this.docenteAsignado = docente;
        } else {
            throw new ExcepcionNegocio("El docente no cumple con los requisitos para este curso.");
        }
    }

    public void inscribirAlumno(Alumno alumno) {
        if (alumnosInscritos.size() >= limiteAlumnos) {
            throw new ExcepcionNegocio("No hay cupos disponibles en este curso.");
        }
        if (alumnosInscritos.contains(alumno)) {
            throw new ExcepcionNegocio("El alumno ya está inscrito en este curso.");
        }
        alumnosInscritos.add(alumno);
        alumno.agregarCursoCompletado(this.codigo); // Refleja la inscripción en el historial del alumno
    }

    public void calificar(Alumno alumno, int nota) {
        if (!alumnosInscritos.contains(alumno)) {
            throw new ExcepcionNegocio("El alumno no está inscrito en este curso.");
        }
        if (nota < 0 || nota > 100) {
            throw new ExcepcionNegocio("La nota debe estar entre 0 y 100.");
        }
        alumno.agregarCursoCompletado(codigo); // Agrega el curso al historial si el alumno aprueba
    }
}
