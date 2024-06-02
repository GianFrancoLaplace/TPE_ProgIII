package ProgramacionIII.tpe;
public class Procesador {
    private String id;
    private String codigo;
    private boolean refrigerado;
    private int anio;

    public Procesador(String id, String codigo, boolean refrigerado, int anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public int getAnio() {
        return anio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    
}
