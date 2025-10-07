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

    public void addAtendimento(Consulta consulta){
        this.agenda.add(consulta.consultaMedico());
    }

    public void removerAtendimento(Consulta consulta){
        this.agenda.remove(consulta.consultaMedico());
    }

    public void removeHorario(String horarioRemover){
        this.agenda.remove(horarioRemover);
    }

    public boolean isHorarioLivre(int dia, int mes, int ano, int hora, int minutos){
            if (agenda == null || agenda.isEmpty()){
                return true;
            }
            for (String atendimentos : agenda){
                String[] dados = atendimentos.split(";");

                String dataOcupada = dados[1].trim();
                String horaOcupada = dados[2].trim();

                String[] data = dataOcupada.split("/");
                String[] horaLista = horaOcupada.split(":");

                String diaS = String.valueOf(dia);
                String mesS = String.valueOf(mes);
                String anoS = String.valueOf(ano);
                String horaS  = String.valueOf(hora);
                String minutosS = String.valueOf(minutos);

                if (diaS.equals(data[0]) && mesS.equals(data[1]) && anoS.equals(data[2]) && horaS.equals(horaLista[0]) && minutosS.equals(horaLista[1])){
                    return false;
                }
            } 
            return true;
    }
    
    //escrever no arquivo dos medicos
    @Override
    public String toString(){
        return String.format("%s,%s,%s,%.2f,%s", nome, crm, especialidade, custoConsulta, agenda);
    }

}
