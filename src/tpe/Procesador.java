package tpe;

import java.util.ArrayList;

public class Procesador {
    
    private String id_procesador;
    private String codigo_procesador;
    private Boolean esta_refrigerado;
    private Integer anio_funcionamiento;
    private int tareasCriticas;
    private ArrayList<Tarea> tareas;
    
    public Procesador(String id_procesador, String codigo_procesador, Boolean esta_refrigerado,
            Integer anio_funcionamiento) {
        this.id_procesador = id_procesador;
        this.codigo_procesador = codigo_procesador;
        this.esta_refrigerado = esta_refrigerado;
        this.anio_funcionamiento = anio_funcionamiento;
        this.tareas = new ArrayList<>();
    }

    public ArrayList<Tarea> getTareas() {
        ArrayList<Tarea> tareasCopia = new ArrayList<>();
        for (Tarea t : tareas) {
            tareasCopia.add(t.getCopia());
        } return tareasCopia;
    }

    public Procesador getCopia(){
        Procesador copia = new Procesador(id_procesador, codigo_procesador, esta_refrigerado, anio_funcionamiento);
        for (Tarea t : this.getTareas()) { // itera las copias
            copia.asignarTarea(t);
        } return copia;
    }

    public void asignarTarea(Tarea tarea) {
        tareas.add(tarea);
        if (tarea.esCritica()) {
            tareasCriticas++;
        }
    }

    public void quitarTarea(Tarea tarea) {
        tareas.remove(tarea);
        if (tarea.esCritica()) {
            tareasCriticas--;
        }
    }

    public boolean puedeAsignarTarea(Tarea tarea, int tiempoMax) {
        if (!this.isRefrigerado() && (this.getTiempoEjecucion() + tarea.getTiempo_ejecucion() > tiempoMax)) {
            return false;
        }
        if (tarea.esCritica() && this.getTareasCriticas() >= 2) {
            return false;
        }
        return true;
    }

    public int getTiempoEjecucion() {
        int tiempoEjecucion = 0;
        for (int i = 0; i < tareas.size(); i++) {
            tiempoEjecucion+= tareas.get(i).getTiempo_ejecucion();
        }return tiempoEjecucion;
    }

    public boolean isRefrigerado() {
        return esta_refrigerado;
    }

    public int getTareasCriticas() {
        return tareasCriticas;
    }

    @Override
    public String toString() {
        return "\n[Procesador(" + id_procesador + ") TiempoEjecucion=" + getTiempoEjecucion()
          + tareas + "\n---------";
    }

    
    
    
}
