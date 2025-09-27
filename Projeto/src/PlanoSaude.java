import java.util.Map;
import java.util.HashMap;

public class PlanoSaude {
    private String nome;
    private String tipo;
    private Map<String, Float> descontoEspecialidade;

    public PlanoSaude(){
        this.nome = "PARTICULAR";
        this.tipo = "";
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

    public float getDescontoEspecialidade(String especialidade){
        if(tipo.equalsIgnoreCase("PARTICULAR")){
            return 0.0f;
        }
        return descontoEspecialidade.getOrDefault(especialidade, 0.0f);
    }

    public void setDescontoEspecialidade(String especialidade, float desconto){
        descontoEspecialidade.put(especialidade, desconto); 
    }

}    
