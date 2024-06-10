package tpe;

import java.util.Iterator;
import java.util.List;

public class AsingarTareaProcesadorBacktracking {
    private SolucionBack mejorSolucion;
    private int tiempoMaximo;
    private int indexTarea;
    private int estadosGenerados;

    public AsingarTareaProcesadorBacktracking(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaximo) {
        this.mejorSolucion = null;
        this.tiempoMaximo = tiempoMaximo;
        this.indexTarea = 0;
        this.estadosGenerados = 0;
        Estado inicial = new Estado(procesadores, tareas, indexTarea);
        backtracking(inicial);
    }

    /*
        backtracking: aplicamos el algortimo de tal manera, que en cada recusrsión de este que en
        caso de que al estado pasado por parametro no le queden tares y si la mejor solucion
        es null o tiene un tiempo de ejcución mayor la solucion del estado pase a ser la mejorsolucion
        en caso contrario iteramos cada procesador y en caso de que pueda asignar la tarea y no sea
        necesario podar la solucion parcial (estado). Asigne la tarea avance una tarea luego llama
        recursivamente a la misma función y continua retrocediendo cada paso desasignando cada tarea
        y retrocediendo para poder seguir buscando otras posibles soluciones usando los distintos
        procesadores
    */

    private void backtracking(Estado estado){
        estadosGenerados++; // Incrementar el contador al generar un nuevo estado
        if (!estado.quedanTareas()){
            if( mejorSolucion==null || mejorSolucion.getTiempoEjecucion() > estado.getTiempoEjecucion()){
                mejorSolucion = estado.getSolucion();
                mejorSolucion.setCantEstadosGenerados(estadosGenerados);
            }
        }else{
            Iterator<Procesador> it = estado.getProcesadoresIterator();
            while(it.hasNext()){
                Procesador procesador = it.next();
                if (procesador.puedeAsignarTarea(estado.getTareaActual(), tiempoMaximo) && !poda(estado)){
                    estado.asignar(estado.getTareaActual(), procesador);
                    estado.avanzarTarea();

                    backtracking(estado);

                    estado.retrocederTarea();
                    estado.designar(estado.getTareaActual(), procesador);
                }
            }
        }
    }

    private boolean poda(Estado estado){
        if (mejorSolucion != null)
            return mejorSolucion.getTiempoEjecucion() < estado.getTiempoEjecucion();
        return false;
    }

    public Solucion backtracking(){
        if (mejorSolucion != null){
            return mejorSolucion;
        }else{
            throw new RuntimeException("No se pueden asignar todas las tareas a los procesadores disponibles");
        }
    }
}
