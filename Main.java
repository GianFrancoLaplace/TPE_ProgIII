package tpe;



public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("Programacion3/src/tpe/datasets/Procesadores.csv", "Programacion3/src/tpe/datasets/Tareas.csv");
        System.out.println("SERVICIO 1" + servicios.servicio1("T2"));
        System.out.println("SERVICIO 2" + servicios.servicio2(true));
        System.out.println("SERVICIO 3" + servicios.servicio3(2, 69));
    }
}
