public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private String data;
    private String hora;
    private String local;
    private String status;

    public Consulta(){
        this.paciente = null;
        this.medico = null;
        this.data = "";
        this.hora = "";
        this.local = "";
        this.status = "AGENDADA"; //para ser criada a consulta tem que ser obrigatoriamente agendada 
    }

    public Consulta(Paciente paciente, Medico medico, String data, String hora, String local){
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.status = "AGENDADA"; //mesma ideia da criação padrão
    }

    public Paciente getPaciente(){
        return paciente;
    }

    public Medico getMedico(){
        return medico;
    }

    public String getData(){
        return this.data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getHora(){
        return this.hora;
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public String getLocal(){
        return this.local;
    }

    public void setLocal(String local){
        this.local = local;
    }

    public String getStatus(){
        return this.status;
    }

    public void agendarConsulta(){
        this.status = "AGENDADA";
    }

    public void cancelarConsulta(){
        this.status = "CANCELADA";
    }

    public void concluirConsulta(){
        this.status = "CONCLUIDA";
    }
}
