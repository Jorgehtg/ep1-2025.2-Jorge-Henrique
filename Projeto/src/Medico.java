import java.util.List;
import java.util.ArrayList;

public class Medico{
    private String nome;
    private String crm;
    private String especialidade;
    private double custoConsulta;
    private List<String> agenda;

    public Medico(String nome, String crm, String especialidade, double custo){//medico com especialidade
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.custoConsulta = custo;
        this.agenda = new ArrayList<>();
    }

    public Medico(String nome, String crm, double custo){//medico sem especialidade
        this.nome = nome;
        this.crm = crm;
        this.especialidade = "Clinico Geral";
        this.custoConsulta = custo;
        this.agenda = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCRM(){
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

    public void addAtendimento(Consulta atendimento){
        this.agenda.add(atendimento.consultaMedico());
    }

    public void removeHorario(String horarioRemover){
        this.agenda.remove(horarioRemover);
    }

    public boolean isHorarioLivre(String data, String hora){
            if (agenda == null || agenda.isEmpty()){
                return true;
            }
            for (String atendimentos: agenda){
                String[] dados = atendimentos.split(";");

                String dataOcupada = dados[1].trim();
                String horaOcupada = dados[2].trim();

                if (dataOcupada.equalsIgnoreCase(data) && horaOcupada.equalsIgnoreCase(hora)){
                    return false;
                } 
            }
            return true;
    }

}
