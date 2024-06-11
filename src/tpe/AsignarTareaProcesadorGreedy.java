package tpe;

import java.util.*;

public class AsignarTareaProcesadorGreedy {
    private int tiempoMaximo;
    private List<Tarea> tareas;
    private int candidatosConsiderados;
    private List<Procesador> procesadores;

    public AsignarTareaProcesadorGreedy(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
        this.tareas = tareas;
        this.procesadores = procesadores;
        this.candidatosConsiderados = 0;
    }
    
    /*
    Dado un conjunto de procesadores y un conjunto de tareas para asignar a los procesadores lo que hacemos con greedy es
    ordenar las tareas primero por prioridad(Criticas van primero), luego según tiempo de ejecución de manera descendente
    y en base a eso se asignan tareas a los procesadores y cuando el procesador ya superó el tiempo de ejecución máximo y
    alcanza el límite máximo de tareas críticas, lo agrega a otro procesador. Así sucesivamente hasta que no queden mas tareas.
    Esto devuelve una solución que se considera válida cuando se asignaron todas las tareas.
    Esta estrategia encuentra la solución más eficiente en el menor tiempo posible, pero no siempre encuentra la mejor solución.
     */
    public Solucion greedy() {
        SolucionGreedy solucion = new SolucionGreedy();
        ArrayList<Procesador> procesadoresCopia = new ArrayList<>();
        for (Procesador procesador : procesadores) {
            procesadoresCopia.add(procesador.getCopia());
        }
        List<Tarea> candidatos = new LinkedList<>();
        for (Tarea tarea : tareas) {
            candidatos.add(tarea.getCopia());
        }

        // Ordenar tareas por duración descendente de mayor a menor (estrategia greedy)
        Collections.sort(candidatos);

        while (!candidatos.isEmpty() && !esSolucion(solucion)) { //la misma que backtracking
            Tarea tarea = seleccionar(candidatos);
            candidatos.remove(tarea);
            candidatosConsiderados++; // Incrementar el contador al considerar un candidato
            asignarSiPuede(procesadoresCopia ,tarea);
            asignarTiempoEjecucion(solucion);
        }

        for(Procesador p : procesadoresCopia){
            solucion.addProcesador(p);
        }

        if (esSolucion(solucion)) {
            solucion.setCandidatosConsiderados(candidatosConsiderados);
            return solucion;
        } else {
            //sino es solucion crear una nueva solucion con las tareas que se asignaron y devolver que no se puedieron asignar x
            throw new RuntimeException("No se pueden asignar todas las tareas a los procesadores disponibles"); // No hay solución factible
        }
    }

    private Tarea seleccionar(List<Tarea> candidatos) {
        return candidatos.get(0); // Selecciona la tarea con mayor duración (primer elemento después de ordenar)
    }

    private void asignarSiPuede(ArrayList<Procesador> list,Tarea tarea) {
        Iterator<Procesador> it = list.iterator();
        while (it.hasNext()) {
            Procesador procesador = it.next();
            if (procesador.puedeAsignarTarea(tarea, tiempoMaximo)){
                procesador.asignarTarea(tarea);
                return;
            }
        }
    }

    private boolean esSolucion(Solucion solucion) {
        int totalTareasAsignadas = 0;
        for (Procesador procesador : solucion.getProcesadores()) {
            totalTareasAsignadas += procesador.getTareas().size();
        }
        return totalTareasAsignadas == tareas.size();
    }

    public void asignarTiempoEjecucion(Solucion solucion){
        int mayorTiempo = 0;
        for (Procesador procesador : solucion.getProcesadores()) {
            if (procesador.getTiempoEjecucion()>mayorTiempo){
                mayorTiempo = procesador.getTiempoEjecucion();
            }
        }
    }

    public int getCandidatosConsiderados() {
        return candidatosConsiderados;
    }

    public void setCandidatosConsiderados(int candidatosConsiderados) {
        this.candidatosConsiderados = candidatosConsiderados;
    }
}
