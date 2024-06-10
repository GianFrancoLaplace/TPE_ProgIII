package tpe;

public class SolucionGreedy extends Solucion {
    private int candidatosConsiderados;

    public SolucionGreedy(){
        super();
        candidatosConsiderados = 0;
    }

    public int getCandidatosConsiderados() {
        return candidatosConsiderados;
    }

    public void setCandidatosConsiderados(int candidatosConsiderados) {
        this.candidatosConsiderados = candidatosConsiderados;
    }

    @Override
    public String toString() {
        return "\nSolucionGreedy [procesadores=" + procesadores
                + "\ncandidatosConsiderados=" + candidatosConsiderados + "\nTiempoEjecucion=" + getTiempoEjecucion() + "]";
    }

    
}
