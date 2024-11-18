public class CursoIngles extends Curso {

    public CursoIngles(String nombre, String codigo, int limiteAlumnos) {
        super(nombre, codigo, limiteAlumnos);
    }

    @Override
    public boolean validarDocente(Docente docente) {
        return docente.tieneConocimientoIngles(); // Requiere que el docente tenga conocimientos de inglés
    }

    @Override
    public void inscribirAlumno(Alumno alumno) {
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
