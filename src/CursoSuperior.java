public class CursoSuperior extends Curso {
    private final String codigoBasicoRequerido;

    public CursoSuperior(String nombre, String codigo, int limiteAlumnos, String codigoBasicoRequerido) {
        super(nombre, codigo, limiteAlumnos);
        this.codigoBasicoRequerido = codigoBasicoRequerido;
    }

    @Override
    public boolean validarDocente(Docente docente) {
        return docente.getAntiguedad() >= 3; // Requiere antigüedad mínima de 3 años
    }

    @Override
    public void inscribirAlumno(Alumno alumno) {
        if (!alumno.haCompletadoCurso(codigoBasicoRequerido)) {
            throw new ExcepcionNegocio("El alumno no ha completado el curso básico requerido (" + codigoBasicoRequerido + ").");
        }
        if (alumnosInscritos.contains(alumno)) {
            throw new ExcepcionNegocio("El alumno ya está inscrito en este curso.");
        }
        super.inscribirAlumno(alumno); // Realiza la inscripción desde la clase base
    }

    @Override
    public void calificar(Alumno alumno, int nota) {
        if (!alumnosInscritos.contains(alumno)) {
            throw new ExcepcionNegocio("El alumno no está inscrito en este curso.");
        }
        if (nota < 0 || nota > 100) {
            throw new ExcepcionNegocio("La nota debe estar entre 0 y 100.");
        }
        alumno.agregarCursoCompletado(codigo);
        System.out.println("El alumno " + alumno.getNombre() + " ha sido calificado con " + nota + " en el curso " + nombre);
    }
}
