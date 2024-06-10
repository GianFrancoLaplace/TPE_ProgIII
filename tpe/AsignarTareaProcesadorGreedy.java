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
        greedy: aplicamos el algortimo de tal manera, que cada iteracion de las tareas provenientes
        del CSVReader se agreguen a una lista de candidatos, hacemos lo mismo con los procesadores
        luego en un bucle seleccionamos la primera tarea, la eliminamos de la lista de candidatos
        y sumamaos uno a la cantidad de canditatos considerados, controlamos si el primer procesador
        puede asignar la tarea y en tal caso realizarlo, hasta que no haya más candidatos. Por último
        agregamos los procesadores a la solución y controlamos que la misma sea falida y en caso de serlo
        asignamos la cantidad de candidatos considerados y retornamos la solución, en caso contrario,
        usamos una excepción que muestra que no hay solución valida.
    */
    public Solucion greedy() {
        SolucionGreedy solucion = new SolucionGreedy();
        ArrayList<Procesador> procesadoresCopia = new ArrayList<>();
        for (Procesador procesador : procesadores) {
//            solucion.addProcesador(procesador.getCopia());
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
