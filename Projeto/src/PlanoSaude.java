import java.util.Map;
import java.util.HashMap;

public class PlanoSaude {
    private String nome;
    private String tipo;
    private Map<String, Integer> descontoEspecialidade;
    private Map<String, Integer> descontoIdoso;
    private int descontoInternacao;


    public PlanoSaude(){//criação padrão para escrever no arquivo de quem não tem plano
        this.nome = "PARTICULAR";
        this.tipo = "PARTICULAR";
        this.descontoEspecialidade = new HashMap<>();
        this.descontoIdoso = new HashMap<>();
        this.descontoInternacao = 100;
    }

    public PlanoSaude(String nome, String tipo, int desconto){
        this.nome = nome;
        this.tipo = tipo;
        this.descontoEspecialidade = new HashMap<>();
        this.descontoIdoso = new HashMap<>();
        this.descontoInternacao = desconto;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public Map<String, Integer> getDescontoEspecialidade(){
        return this.descontoEspecialidade;
    }

    public void setDescontoEspecialidade(String especialidade, int desconto){
        if(nome.equalsIgnoreCase("PARTICULAR")){
            descontoEspecialidade.put(especialidade,100);//esse 100 quer dizer 100% do valor
        }else{
            descontoEspecialidade.put(especialidade, desconto);
        }
    }

    public Map<String, Integer> getDescontoIdoso(){
        return this.descontoIdoso;
    }

    public void setDescontoIdoso(String especialidade, int desconto){
        if(nome.equalsIgnoreCase("PARTICULAR")){
            descontoIdoso.put(especialidade,100);
        }else{
            descontoIdoso.put(especialidade,desconto);
        }
    }

    public int getDescontoInternacao(){
        return this.descontoInternacao;
    }

    public void setDescontoInternacao(int desconto){
        if(nome.equalsIgnoreCase("PARTICULAR")){
            this.descontoInternacao = 100;
        }else{
            this.descontoInternacao = desconto;
        }
    }

}    
