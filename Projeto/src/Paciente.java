import java.util.List;
import java.util.ArrayList;

public class Paciente extends Pessoa {
    private String cpf;
    private int idade;
    private List<String> consultas;
    private List<String> internações;

    public Paciente(){
        super();
        this.cpf = "";
        this.idade = 0;
        this.consultas = new ArrayList<>();
        this.internações = new ArrayList<>();
    }

    public Paciente(String nome, String cpf, int idade){
        super(nome);
        this.cpf = cpf;
        this.idade = idade;
        this.consultas = new ArrayList<>();
        this.internações = new ArrayList<>();
    }

    public String getCPF(){
        return this.cpf;
    }

    public void setCPF(String cpf){
        this.cpf = cpf;
    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public List<String> getConsultas(){
        return this.consultas;
    }

    public void setConsultas(Consulta consulta){
        this.consultas.add(consulta.toString());
    }

    public List<String> getInternações(){
        return internações;
    }

    public void setInternações(String internação){
        this.internações.add(internação);
    }

    @Override
    public String toString(){
        return String.format("Nome: %s, CPF: %s, Idade: %d, Historico de Consultas: %s, Historico de internações: %s", getNome(), cpf, idade, consultas, internações);
    }
}
