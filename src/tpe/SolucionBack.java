package tpe;

public class SolucionBack extends Solucion {
    private int cantEstadosGenerados;

    public SolucionBack() {
        super();
        this.cantEstadosGenerados = 0;
    }

    public void setCantEstadosGenerados(int cantEstadosGenerados) {
        this.cantEstadosGenerados = cantEstadosGenerados;
    }

    public int getCantEstadosGenerados() {
        return cantEstadosGenerados;
    }

    @Override
    public String toString() {
        return "\nSolucionBacktracking [procesadores=" + procesadores
                + "\ncantEstadosGenerados=" + cantEstadosGenerados + "\nTiempoEjecucion=" + getTiempoEjecucion() + "]";
    }
}
