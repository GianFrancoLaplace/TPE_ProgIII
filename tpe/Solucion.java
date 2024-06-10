package tpe;

import java.util.ArrayList;
import java.util.List;

public class Solucion {
    protected ArrayList<Procesador> procesadores;

    public Solucion(){
        this.procesadores = new ArrayList<>();
    }
    

    public List<Procesador> getProcesadores() {
        ArrayList<Procesador> listaProcesadores = new ArrayList<>();
        for (Procesador procesador : procesadores){
            listaProcesadores.add(procesador.getCopia());

        }
        return listaProcesadores;
    }

    public int getTiempoEjecucion(){
        int mayor = 0;
        for(Procesador p : procesadores){
            int tiempoP = p.getTiempoEjecucion();
            if(tiempoP > mayor){
                mayor = tiempoP;
            }
        }
        return mayor;
    }

    public void addProcesador(Procesador p){
        procesadores.add(p);
    }

    
}
