import java.util.ArrayList;
import java.util.List;

public class PlanoSaude {
    private String nome;
    private String tipo;
    private List<String> especialidades;
    private List<Double> descontosNormais;
    private List<Double> descontosIdosos;
    private double descontoInternacao;


    public PlanoSaude(){//criação padrão para escrever no arquivo de quem não tem plano
        this.nome = "PARTICULAR";
        this.tipo = "PARTICULAR";
        this.descontoInternacao = 0;
        this.especialidades = new ArrayList<>();
        this.descontosNormais = new ArrayList<>();
        this.descontosIdosos = new ArrayList<>();
    }

    public PlanoSaude(String nome, String tipo, double descontoInternacao){
        this.nome = nome;
        this.tipo = tipo;
        this.especialidades = new ArrayList<>();
        this.descontosNormais = new ArrayList<>();
        this.descontosIdosos = new ArrayList<>();
        this.descontoInternacao = descontoInternacao;
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
        return new ArrayList<>(this.especialidades);
    }

    public void setEspescialidades(List<String> especialidades){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.especialidades = new ArrayList<>(especialidades);
        }
    }

    public List<Double> getDescontosNormais(){
        return new ArrayList<>(this.descontosNormais);
    }

    public void setDescontosNormais(List<Double> descontosNormais){
        if (!nome.equalsIgnoreCase("PARTICULAR")){
            this.descontosNormais = new ArrayList<>(descontosNormais);
        }
    }

    public List<Double> getDescontosIdosos() {
        return new ArrayList<>(this.descontosIdosos);
    }

    public void setDescontosIdosos(List<Double> descontosIdosos){
        if (!nome.equalsIgnoreCase("PARTICULAR")){
            this.descontosIdosos = new ArrayList<>(descontosIdosos);
        }
    }

    public double getDescontoInternacao() {
        return this.descontoInternacao;
    }

    public void setDescontoInternacao(double desconto) {
        if (desconto >= 0 && desconto <= 100) {
            this.descontoInternacao = desconto;
        }
    }

    public void addDescontos(String especialidade, double descontoNormal, double descontoIdoso){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            this.especialidades.add(especialidade.toLowerCase());
            this.descontosNormais.add(descontoNormal);
            this.descontosIdosos.add(descontoIdoso);
        }
    }

    public double getDescontoNormal(String especialidade){
        int i = especialidades.indexOf(especialidade.toLowerCase());
        return descontosNormais.get(i);
    }

    public double getDescontoIdoso(String especialidade){
        int i = especialidades.indexOf(especialidade.toLowerCase());
        return descontosIdosos.get(i);
    }

    public String formartarDescontos(){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            StringBuilder descontos = new StringBuilder();
            for (int i = 0; i < especialidades.size(); i++) {
                if (i > 0) descontos.append(";");
                descontos.append(String.format("[%s,%.1f,%.1f]", 
                    especialidades.get(i), 
                    descontosNormais.get(i), 
                    descontosIdosos.get(i)));
            } 
            return descontos.toString();
        }
        return "";
    }

    @Override
    public String toString(){
        if(!nome.equalsIgnoreCase("PARTICULAR")){
            return String.format("%s,%s,%s,%.2f", nome, tipo, formartarDescontos(), descontoInternacao);
        }
        return "";
    }

}    
