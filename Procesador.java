package tpe;

public class Procesador {
    
    private String id_procesador;
    private String codigo_procesador;
    private Boolean esta_refrigerado;
    private Integer anio_funcionamiento;
    
    public Procesador(String id_procesador, String codigo_procesador, Boolean esta_refrigerado,
            Integer anio_funcionamiento) {
        this.id_procesador = id_procesador;
        this.codigo_procesador = codigo_procesador;
        this.esta_refrigerado = esta_refrigerado;
        this.anio_funcionamiento = anio_funcionamiento;
    }

    
}
