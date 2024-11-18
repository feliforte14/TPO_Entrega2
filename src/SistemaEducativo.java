import java.util.*;

public class SistemaEducativo {

    private final Scanner scanner;
    private final List<Alumno> alumnos;
    private final List<Docente> docentes;
    private final List<Curso> cursos;

    public SistemaEducativo() {
        this.scanner = new Scanner(System.in);
        this.alumnos = new ArrayList<>();
        this.docentes = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                // Creación
                case 1 -> crearAlumno();
                case 2 -> crearDocente();
                case 3 -> crearCurso();

                // Consulta
                case 4 -> mostrarAlumnos();
                case 5 -> mostrarDocentes();
                case 6 -> listarCursos();
                case 7 -> mostrarHistorialAlumno();
                case 8 -> consultarDetallesCurso();

                // Modificación
                case 9 -> asignarDocenteACurso();
                case 10 -> inscribirAlumnoEnCurso();
                case 11 -> calificarAlumno();

                // Eliminación
                case 12 -> eliminarAlumno();
                case 13 -> eliminarDocente();
                case 14 -> eliminarCurso();

                // Salida
                case 0 -> continuar = confirmarSalida();
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n=======================================");
        System.out.println("         SISTEMA EDUCATIVO             ");
        System.out.println("=======================================");
        System.out.println("[CREACIÓN]");
        System.out.println(" 1. Crear Alumno");
        System.out.println(" 2. Crear Docente");
        System.out.println(" 3. Crear Curso");
        System.out.println("---------------------------------------");
        System.out.println("[CONSULTA]");
        System.out.println(" 4. Mostrar Todos los Alumnos");
        System.out.println(" 5. Mostrar Todos los Docentes");
        System.out.println(" 6. Listar Todos los Cursos");
        System.out.println(" 7. Mostrar Historial de un Alumno");
        System.out.println(" 8. Consultar Detalles de un Curso");
        System.out.println("---------------------------------------");
        System.out.println("[MODIFICACIÓN]");
        System.out.println(" 9. Asignar Docente a Curso");
        System.out.println("10. Inscribir Alumno en Curso");
        System.out.println("11. Calificar Alumno");
        System.out.println("---------------------------------------");
        System.out.println("[ELIMINACIÓN]");
        System.out.println("12. Eliminar Alumno");
        System.out.println("13. Eliminar Docente");
        System.out.println("14. Eliminar Curso");
        System.out.println("---------------------------------------");
        System.out.println("[SALIR]");
        System.out.println(" 0. Salir");
        System.out.println("=======================================");
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // CREACIÓN

    private void crearAlumno() {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nombre del alumno: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID del alumno: ");
        String id = scanner.nextLine();
        if (alumnos.stream().anyMatch(alumno -> alumno.getId().equals(id))) {
            System.out.println("Error: Ya existe un alumno con ese ID.");
        } else {
            alumnos.add(new Alumno(nombre, id));
            System.out.println("Alumno creado con éxito.");
        }
    }

    private void crearDocente() {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nombre del docente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID del docente: ");
        String id = scanner.nextLine();
        if (docentes.stream().anyMatch(docente -> docente.getId().equals(id))) {
            System.out.println("Error: Ya existe un docente con ese ID.");
        } else {
            System.out.print("Ingrese antigüedad del docente (en años): ");
            int antiguedad = leerOpcion();
            System.out.print("¿El docente tiene conocimiento de inglés? (true/false): ");
            boolean conocimientoIngles = scanner.nextBoolean();
            docentes.add(new Docente(nombre, id, antiguedad, conocimientoIngles));
            System.out.println("Docente creado con éxito.");
        }
    }

    private void crearCurso() {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nombre del curso: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese código del curso: ");
        String codigo = scanner.nextLine();

        if (cursos.stream().anyMatch(curso -> curso.codigo.equals(codigo))) {
            System.out.println("Error: Ya existe un curso con ese código.");
        } else {
            System.out.print("Ingrese límite de alumnos: ");
            int limite = leerOpcion();

            System.out.println("Seleccione el tipo de curso:");
            System.out.println("1. Curso Básico");
            System.out.println("2. Curso Superior");
            System.out.println("3. Curso de Inglés");
            int tipo = leerOpcion();
            scanner.nextLine(); // Limpiar el buffer después de leer la opción

            switch (tipo) {
                case 1 -> cursos.add(new CursoBasico(nombre, codigo, limite));
                case 2 -> {
                    System.out.print("Ingrese código del curso básico requerido: ");
                    String codigoBasicoRequerido = scanner.nextLine();
                    cursos.add(new CursoSuperior(nombre, codigo, limite, codigoBasicoRequerido));
                }
                case 3 -> cursos.add(new CursoIngles(nombre, codigo, limite));
                default -> System.out.println("Tipo de curso no válido.");
            }
            System.out.println("Curso creado con éxito.");
        }
    }

    // CONSULTA

    private void mostrarAlumnos() {
        System.out.println("Lista de Alumnos:");
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            alumnos.forEach(alumno -> System.out.println("- " + alumno.getNombre() + " (ID: " + alumno.getId() + ")"));
        }
    }

    private void mostrarDocentes() {
        System.out.println("Lista de Docentes:");
        if (docentes.isEmpty()) {
            System.out.println("No hay docentes registrados.");
        } else {
            docentes.forEach(docente -> System.out.println("- " + docente.getNombre() + " (ID: " + docente.getId() + ")"));
        }
    }

    private void listarCursos() {
        System.out.println("Lista de Cursos:");
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
        } else {
            cursos.forEach(curso -> System.out.println("- " + curso.nombre + " (Código: " + curso.codigo + ")"));
        }
    }

    private void mostrarHistorialAlumno() {
        Alumno alumno = seleccionarAlumno();
        if (alumno != null) {
            System.out.println("Historial del alumno " + alumno.getNombre() + ":");
            alumno.consultarHistorial().forEach(curso -> System.out.println("- " + curso));
        }
    }

    private void consultarDetallesCurso() {
        Curso curso = seleccionarCurso();
        if (curso != null) {
            System.out.println("\n--- Detalles del Curso ---");
            System.out.println("Nombre: " + curso.nombre);
            System.out.println("Código: " + curso.codigo);
            System.out.println("Límite de alumnos: " + curso.limiteAlumnos);
            System.out.println("Alumnos inscritos:");
            curso.alumnosInscritos.forEach(alumno ->
                    System.out.println("- " + alumno.getNombre() + " (ID: " + alumno.getId() + ")"));
            System.out.println("Docente asignado: " +
                    (curso.docenteAsignado != null ? curso.docenteAsignado.getNombre() : "No asignado"));
        }
    }

    // ELIMINAR

    private void eliminarAlumno() {
        mostrarAlumnos();
        System.out.print("Ingrese el ID del alumno a eliminar: ");
        String id = scanner.next();
        boolean eliminado = alumnos.removeIf(alumno -> alumno.getId().equals(id));
        System.out.println(eliminado ? "Alumno eliminado con éxito." : "Alumno no encontrado.");
    }


    private void eliminarDocente() {
        mostrarDocentes();
        System.out.print("Ingrese el ID del docente a eliminar: ");
        String id = scanner.next();
        boolean eliminado = docentes.removeIf(docente -> docente.getId().equals(id));
        System.out.println(eliminado ? "Docente eliminado con éxito." : "Docente no encontrado.");
    }

    private void eliminarCurso() {
        listarCursos();
        System.out.print("Ingrese el código del curso a eliminar: ");
        String codigo = scanner.next();
        boolean eliminado = cursos.removeIf(curso -> curso.codigo.equals(codigo));
        System.out.println(eliminado ? "Curso eliminado con éxito." : "Curso no encontrado.");
    }

// Modificacion

    private void asignarDocenteACurso() {
        Curso curso = seleccionarCurso();
        Docente docente = seleccionarDocente();

        if (curso != null && docente != null) {
            try {
                curso.asignarDocente(docente);
                System.out.println("Docente asignado al curso con éxito.");
            } catch (ExcepcionNegocio e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void inscribirAlumnoEnCurso() {
        Curso curso = seleccionarCurso();
        Alumno alumno = seleccionarAlumno();

        if (curso != null && alumno != null) {
            try {
                curso.inscribirAlumno(alumno);
                System.out.println("Alumno inscrito en el curso con éxito.");
            } catch (ExcepcionNegocio e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void calificarAlumno() {
        Curso curso = seleccionarCurso();
        Alumno alumno = seleccionarAlumno();

        if (curso != null && alumno != null) {
            System.out.print("Ingrese la nota del alumno: ");
            int nota = leerOpcion();
            try {
                curso.calificar(alumno, nota);
                System.out.println("Alumno calificado con éxito.");
            } catch (ExcepcionNegocio e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private boolean confirmarSalida() {
        System.out.print("¿Está seguro de que desea salir? (s/n): ");
        String respuesta = scanner.next().toLowerCase();
        while (!respuesta.equals("s") && !respuesta.equals("n")) {
            System.out.print("Respuesta inválida. Ingrese 's' para sí o 'n' para no: ");
            respuesta = scanner.next().toLowerCase();
        }
        return !respuesta.equals("s");
    }

    // Métodos para seleccionar entidades
    private Curso seleccionarCurso() {
        listarCursos();
        System.out.print("Ingrese el código del curso: ");
        String codigo = scanner.next();

        return cursos.stream()
                .filter(curso -> curso.codigo.equals(codigo))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Curso no encontrado.");
                    return null;
                });
    }

    private Alumno seleccionarAlumno() {
        mostrarAlumnos();
        System.out.print("Ingrese el ID del alumno: ");
        String id = scanner.next();

        return alumnos.stream()
                .filter(alumno -> alumno.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Alumno no encontrado.");
                    return null;
                });
    }

    private Docente seleccionarDocente() {
        mostrarDocentes();
        System.out.print("Ingrese el ID del docente: ");
        String id = scanner.next();

        return docentes.stream()
                .filter(docente -> docente.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Docente no encontrado.");
                    return null;
                });
    }
}
