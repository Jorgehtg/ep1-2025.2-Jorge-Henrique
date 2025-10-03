import java.util.ArrayList;
import java.util.List;

public class PlanoSaude {
    private String nome;
    private String tipo;
    private List<String> especialidades;
    private List<Integer> descontoEspecialidade;
    private List<Integer> descontoIdoso;
    private int descontoInternacao;


    public PlanoSaude(){//criação padrão para escrever no arquivo de quem não tem plano
        this.nome = "PARTICULAR";
        this.tipo = "PARTICULAR";
        this.descontoInternacao = 0;
    }

    public PlanoSaude(String nome, String tipo, int desconto){
        this.nome = nome;
        this.tipo = tipo;
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.especialidades = new ArrayList();
            this.descontoEspecialidade = new ArrayList<>();
            this.descontoIdoso = new ArrayList<>();
            this.descontoInternacao = desconto;
        }else{
            this.descontoInternacao = 0;
        }
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

    public List<String> getEspecialidades(){
        return this.especialidades;
    }

    public void setEspescialidades(List<String> especialidades){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.especialidades = especialidades;
        }
    }

    public void addEspecialidade(String especialidade){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.especialidades.add(especialidade);
        }
    }

    public List<Integer> getDescontosEspecialidades(){
        return this.descontoEspecialidade;
    }

    public void setDescontoEspecialidade(List<Integer> descontoEspecialidade){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.descontoEspecialidade = descontoEspecialidade;
        }
    }

    public void addDescontoEspecialidade(int desconto){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.descontoEspecialidade.add(desconto);
        }
    }

    public List<Integer> getDescontoIdoso(){
        return this.descontoIdoso;
    }

    public void setDescontoIdoso(List<Integer> descontoIdoso){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.descontoIdoso = descontoIdoso;
        }
    }

    public void addDescontoIdoso(int desconto){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.descontoIdoso.add(desconto);
        }
    }

    public int getDescontoInternacao(){
        return this.descontoInternacao;
    }

    public void setDescontoInternacao(int desconto){
        this.descontoInternacao = desconto;
    }

    public void addInfos(String especialidade, int descontoNormal, int descontoIdoso){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.especialidades.add(especialidade);
            this.descontoEspecialidade.add(descontoNormal);
            this.descontoIdoso.add(descontoIdoso);
        }
    }

    public String addDescontos(){
        StringBuilder descontos = new StringBuilder();
        for(String especialidade : especialidades){
            int i = especialidades.indexOf(especialidade);
            int desconto = this.descontoEspecialidade.get(i);
            int descontoIdoso = this.descontoIdoso.get(i);

            descontos.append(String.format("[%s,%d,%d]", especialidade, desconto, descontoIdoso));
        }
        return descontos.toString();
    }
    @Override
    public String toString(){
        return String.format("%s,%s,%s,%d", nome, tipo, addDescontos(), descontoInternacao);
    }

}    
