import java.util.List;
import java.util.ArrayList;

public class Medico{
    private String nome;
    private String crm;
    private String especialidade;
    private double custoConsulta;
    private List<String> agenda;

    public Medico(String nome, String crm, String especialidade, double custo){
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.custoConsulta = custo;
        this.agenda = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCrm(){
        return this.crm;
    }

    public void setCRM(String crm){
        this.crm = crm;
    }

    public String getEspecialidade(){
        return this.especialidade;
    }

    public void setEspecialidade(String especialidade){
        this.especialidade = especialidade;
    }

    public double getCustoConsulta(){
        return custoConsulta;
    }

    public void setCustoConsulta(double custo){
        this.custoConsulta = custo;
    }

    public List<String> getAgenda(){
        return agenda;
    }

    public void addAtendimento(Consulta consulta){
        this.agenda.add(consulta.consultaMedico());
    }

    public void removerAtendimento(Consulta consulta){
        this.agenda.remove(consulta.consultaMedico());
    }

    public void removeHorario(String horarioRemover){
        this.agenda.remove(horarioRemover);
    }

    public boolean isHorarioLivre(String dataEscolhida, String horaEscolhida){
            if (agenda == null || agenda.isEmpty()){
                return true;
            }
            for (String atendimentos : agenda){
                String[] dados = atendimentos.split(";");
                
                if(dados.length<3)continue;

                String dataOcupada = dados[1].trim();
                String horaOcupada = dados[2].trim();

                if (dataEscolhida.equalsIgnoreCase(dataOcupada)){
                    if (horaEscolhida.equalsIgnoreCase(horaOcupada)){
                        return false;
                    }
                }
            } 
            return true;
    }

    public String relatorio(int numeroDeConsultas){
        return String.format("%s - %s - %s - %s - %d", nome, crm, especialidade, agenda, numeroDeConsultas);
    }
    
    //escrever no arquivo dos medicos
    @Override
    public String toString(){
        return String.format("%s,%s,%s,%.2f,%s", nome, crm, especialidade, custoConsulta, agenda);
    }

}
