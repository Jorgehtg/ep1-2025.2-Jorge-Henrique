import java.util.List;
import java.util.ArrayList;

public class Paciente{
    private String nome;
    private String cpf;
    private int idade;
    private List<String> consultas;
    private List<String> internacoes;

    public Paciente(String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.consultas = new ArrayList<>();
        this.internacoes = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String cpf){
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
        this.consultas.add(consulta.agendarConsulta());
    }

    public List<String> getInternacoes(){
        return internacoes;
    }

    public void setInternacoes(String internação){
        this.internacoes.add(internação);
    }

    @Override
    public String toString(){
        return String.format("Nome: %s, CPF: %s, Idade: %d, Historico de Consultas: %s, Historico de internações: %s", getNome(), cpf, idade, consultas, internacoes);
    }
}
