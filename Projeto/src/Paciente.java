import java.util.List;
import java.util.ArrayList;

public class Paciente{
    private String nome;
    private String cpf;
    private int idade;
    private PlanoSaude plano;
    private List<Consulta> consultas;
    private List<String> internacoes;

    public Paciente(String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.plano = new PlanoSaude(); //padrão que será alterado por outras classes
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

    public PlanoSaude getPlano(){
        return this.plano;
    }

    public void setPlano(PlanoSaude plano){
        this.plano = plano;
    }

    public List<Consulta> getConsultas(){
        return this.consultas;
    }

    public void addConsultas(Consulta consulta){
        this.consultas.add(consulta);
    }

    public List<String> getInternacoes(){
        return internacoes;
    }

    public void addInternacoes(String internação){
        this.internacoes.add(internação);
    }

    public boolean isIdoso(){
        return this.idade >= 60;
    }

    public double calcularCustoConsulta(double precoOriginal, String especialidade){
        return precoOriginal;
    }

    public String relatorio(){
        return String.format("%s - %s - %d - %s - %s - %s", nome, cpf, idade, plano.getNome(), consultas, internacoes);
    }

    @Override
    public String toString(){
        return String.format("%s,%s,%d,%s,%s,%s", nome, cpf, idade, plano.getNome(), consultas, internacoes);
    }

    
}
