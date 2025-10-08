public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private String data;
    private String hora;
    private String local;
    private String status;
    private String diagnostico;
    private Prescricao prescricao;

    public Consulta(Paciente paciente, Medico medico, String data, String hora, String local){//consulta apenas marcada
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.status = "AGENDADA"; //para ser criada a consulta tem que ser obrigatoriamente agendada
        this.diagnostico = "";
        this.prescricao = null;
    }

    public Consulta(Paciente paciente, Medico medico, String data, String hora, String local, String diagnostico, Prescricao prescricao){//consulta concluida
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.status = "CONCLUIDA";
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
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

    public void setStatus(String status){
        this.status = status;
    }

    public String getDiagnostico(){
        return this.diagnostico;
    }

    public void setDiagnostico(String diagnostico){
        this.diagnostico = diagnostico;
    }

    public Prescricao getPrescricao(){
        return prescricao;
    }

    public void setPrescricao(Prescricao prescrição){
        if (!"CONCLUIDA".equalsIgnoreCase(this.status)){
            this.prescricao = null;
        }
        this.prescricao = prescrição;
    }

    public void concluirConsulta(String diagnostico, Prescricao prescricao){
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;

    }

    public String consultaPaciente(){
        if (status.equalsIgnoreCase("CONCLUIDA")){
            return String.format("[Dr. %s,%s,%s,%s,%s,%s,%s,%s,%d]",medico.getNome(),medico.getCrm(), this.data, this.hora, this.local, this.status, this.diagnostico, prescricao, medico.getCustoConsulta());
        }else{
            return String.format("[Dr. %s,%s,%s,%s,%s,%s]", medico.getNome(),medico.getCrm(), this.data, this.hora, this.local, this.status);
        }
    }

    public String consultaMedico(){
        return String.format("%s; %s; %s; %s", paciente.getNome(), this.data, this.hora, this.local);
    }

    @Override
    public String toString(){
        if (!status.equalsIgnoreCase("CONCLUIDA")){
            return String.format("%s,%s,%s,%s,%s,%s,%s,%s", paciente.getNome(), paciente.getCpf(), medico.getNome(), medico.getCrm(), data, hora, local, status);
        }
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s", paciente.getNome(), medico.getNome(), data, hora, local, status, diagnostico, prescricao.prescricaoConsulta());
    }

    
}
