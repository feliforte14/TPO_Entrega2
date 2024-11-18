public class Docente {
    private String nombre;
    private String id;
    private int antiguedad;
    private boolean conocimientoIngles;

    public Docente(String nombre, String id, int antiguedad, boolean conocimientoIngles) {
        this.nombre = nombre;
        this.id = id;
        this.antiguedad = antiguedad;
        this.conocimientoIngles = conocimientoIngles;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public boolean tieneConocimientoIngles() {
        return conocimientoIngles;
    }
}
