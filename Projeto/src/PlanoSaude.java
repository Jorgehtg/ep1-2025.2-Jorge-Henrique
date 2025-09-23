import java.util.Map;
import java.util.HashMap;

public class PlanoSaude {
    private String nome;
    private Map<String, Float> descontoEspecialidade;

    public PlanoSaude(){
        this.nome = "";
        this.descontoEspecialidade = new HashMap<>();
    }

    public PlanoSaude(String nome){
        this.nome = nome;
        this.descontoEspecialidade = new HashMap<>();
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public float getDescontoEspecialidade(String especialidade){
        return descontoEspecialidade.getOrDefault(especialidade, 0.0f);
    }

    public void setDescontoEspecialidade(String especialidade, float desconto){
        descontoEspecialidade.put(especialidade, desconto); 
    }

}    
