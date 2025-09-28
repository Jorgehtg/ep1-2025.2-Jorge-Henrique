import java.util.Map;
import java.util.HashMap;

public class PlanoSaude {
    private String nome;
    private String tipo;
    protected Map<String, Float> descontoEspecialidade;

    public PlanoSaude(){//criação padrão para escrever no arquivo de quem não tem plano
        this.nome = "PARTICULAR";
        this.tipo = "PARTICULAR";
        this.descontoEspecialidade = new HashMap<>();
    }

    public PlanoSaude(String nome, String tipo){
        this.nome = nome;
        this.tipo = tipo;
        this.descontoEspecialidade = new HashMap<>();
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

    public Map<String, Float> getDescontoEspecialidade(){
        return this.descontoEspecialidade;
    }

    public void setDescontoEspecialidade(String especialidade, float desconto){
        if(nome.equalsIgnoreCase("PARTICULAR")){
            descontoEspecialidade.put(especialidade,0.0f);
        }else{
            descontoEspecialidade.put(especialidade, desconto);
        }
    }

}    
