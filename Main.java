package ProgramacionIII.tpe;
public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("datasets\\Procesadores.csv", "datasets\\Tareas.csv");
		System.out.println(servicios.servicio1("T1"));
		System.out.println(servicios.servicio2(false));
	}
}
