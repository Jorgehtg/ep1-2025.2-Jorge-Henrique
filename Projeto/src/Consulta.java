import java.util.ArrayList;
import java.util.List;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private String data;
    private String hora;
    private String local;
    private String status;
    private String diagnostico;
    private List<Prescricao> prescrição;

    public Consulta(Paciente paciente, Medico medico, String data, String hora, String local){
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.status = "AGENDADA"; //para ser criada a consulta tem que ser obrigatoriamente agendada
        this.diagnostico = "";
        this.prescrição = new ArrayList<>();
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

    public String getDiagnostico(){
        return this.diagnostico;
    }

    public void setDiagnostico(String diagnostico){
        this.diagnostico = diagnostico;
    }

    public List<Prescricao> getPrescrição(){
        return prescrição;
    }

    public void addPrescrição(Prescricao prescrição){
        this.prescrição.add(prescrição);
    }

    public String agendarConsulta(){
        return "[" + medico.getNome() + ";" + getData() + ";" + getHora() + ";" + getLocal() + ";" + getStatus() + "]";
    }

    public void cancelarConsulta(){
        this.status = "CANCELADA";
    }

    public String concluirConsulta(){
        this.status = "CONCLUIDA";
        return "[" + medico.getNome() + ";" + getData() + ";" + getHora() + ";" + getLocal() + ";" + getStatus() + ";" + diagnostico + ";" + prescrição + "]";
    }
}
