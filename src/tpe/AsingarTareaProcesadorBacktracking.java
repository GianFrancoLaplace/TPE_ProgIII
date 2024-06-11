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
      En esta estrategia vamos asignando las tareas a cada procesador, con las restricciones del tiempo máximo de ejecución
      de cada procesador no refrigerado y el límite máximo de tareas criticas por procesador.
      Guardamos la mejor solución en una variable, y el estado con la solucion actual.
      Mediante el mecanismo de poda, vamos descartando aquellas soluciones actuales que sean peor que la mejor solucion.
      Con esto reducimos considerablemente el costo de la solución el cual vamos calculando con el contador de estados generados.
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
