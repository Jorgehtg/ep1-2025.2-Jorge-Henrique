import java.util.List;
import java.util.ArrayList;

public class Medico{
    private String nome;
    private String crm;
    private String especialidade;
    private double custoConsulta;
    private List<String> agenda;

    public Medico(){
        this.nome = "";
        this.crm = "";
        this.especialidade = "";
        this.custoConsulta = 0.0;
        this.agenda = new ArrayList<>();
    }

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
        this.especialidade = "";
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

    public void addHorario(String hora){
        this.agenda.add(hora);
    }

    public void removeHorario(String hora){
        this.agenda.remove(hora);
    }

}
