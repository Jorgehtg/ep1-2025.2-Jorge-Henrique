public class Internacao {
    private Paciente paciente;
    private Medico medico;
    private String dataEntrada;
    private String dataSaida;
    private int numQuarto;
    private double valorInternação;

    public Internacao(Paciente paciente, Medico medico, String dataEntrada, int numQuarto, double valor){//paciente ainda não liberado
        this.paciente = paciente;
        this.medico = medico;
        this.dataEntrada = dataEntrada;
        this.dataSaida = "";
        this.numQuarto = numQuarto;
        this.valorInternação = valor;
    }

    public Paciente getPaciente(){
        return this.paciente;
    }

    public Medico getMedico(){
        return this.medico;
    }

    public String getDataEntrada(){
        return this.dataEntrada;
    }

    public void setDataEntrada(String dataEntrada){
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida(){
        return this.dataSaida;
    }

    public void setDataSaida(String dataSaida){
        this.dataSaida = dataSaida;
    }

    public int getNumQuarto(){
        return numQuarto;
    }

    public void setNumQuarto(int numQuarto){
        this.numQuarto = numQuarto;
    }

    public double getValorInternação(){
        return valorInternação;
    }

    public void setValorInternação(double valorInternação){
        this.valorInternação = valorInternação;
    }

    public void finalizarInternacao(String dataSaida){
        this.dataSaida = dataSaida;
    }

    public String relatorio(){
        return String.format("%s - %s %s - %s - %d", paciente.getNome(), medico.getNome(), medico.getCrm(), dataEntrada, numQuarto);
    }

    @Override
    public String toString(){
        if(dataSaida == null){
            return String.format("%s,%s,%s,%s,%s,%d,%.2f",paciente.getNome(), paciente.getCpf(), medico.getNome(), medico.getCrm(), dataEntrada, numQuarto, valorInternação);
        }
        return String.format("%s,%s,%s,%s,%s,%s,%d,%.2f",paciente.getNome(), paciente.getCpf(), medico.getNome(), medico.getCrm(), dataEntrada, dataSaida, numQuarto, valorInternação);
    }

}
