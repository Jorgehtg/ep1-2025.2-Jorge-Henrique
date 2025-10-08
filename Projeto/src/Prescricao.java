public class Prescricao{
    private Medico medico;
    private String remedio;
    private int dias;

    public Prescricao(Medico medico, String remedio, int dias){
        this.medico = medico;
        this.remedio = remedio;
        this.dias = dias;
    }

    public Medico getMedico(){
        return medico;
    }

    public String getRemedio(){
        return this.remedio;
    }

    public void setRemedio(String remedio){
        this.remedio = remedio;
    }

    public int getDias(){
        return dias;
    }

    public void setDias(int dias){
        this.dias = dias;
    }

    public String prescricaoConsulta(){
        return String.format("%s por %d dias", remedio, dias);
    }

    @Override
    public String toString(){
        return "Prescrição: " + medico.getNome() + "(CRM: " + medico.getCrm() + ") " + remedio;
    }
}
