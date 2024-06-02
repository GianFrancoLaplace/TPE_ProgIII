package ProgramacionIII.tpe;
import ProgramacionIII.tpe.utils.CSVReader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	private HashMap<String, Tarea> hashTareaId;
	private HashSet<Boolean, Tarea> hashTareaCritica;

	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		hashTareaId = reader.getHashId();
		hashTareaCritica = reader.getHashCritica();
	}
	
	/*
     * Expresar la complejidad temporal del servicio 1.
     */
	public Tarea servicio1(String ID) {
		return tareas.get(ID);
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
	public List<Tarea> servicio2(boolean esCritica) {
		return hashTareaCritica.get(esCritica);
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
	// public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) { }

}
