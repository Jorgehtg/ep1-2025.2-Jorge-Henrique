import java.util.ArrayList;

public class PlanoEspecial extends PlanoSaude {
    private boolean internacaoCurta;

    public PlanoEspecial(String nome, String tipo, int descontoInternacao){
        super(nome, tipo, descontoInternacao);
        this.internacaoCurta = false;

        if (!nome.equalsIgnoreCase("PARTICULAR")){
            this.setEspescialidades(new ArrayList<>());
            this.setDescontosNormais(new ArrayList<>());
            this.setDescontosIdosos(new ArrayList<>());
        }
    }

    public PlanoEspecial(String nome, String tipo, int descontoInternacao, boolean internacaoCurta){
        super(nome, tipo, descontoInternacao);
        this.internacaoCurta = internacaoCurta;

        if (!nome.equalsIgnoreCase("PARTICULAR")){
            this.setEspescialidades(new ArrayList<>());
            this.setDescontosNormais(new ArrayList<>());
            this.setDescontosIdosos(new ArrayList<>());
        }
    }

    public boolean temInternacaoCurta(){
            return this.internacaoCurta;
    }

    public void setInternacaoCurta(boolean internacaoCurta){
        this.internacaoCurta = internacaoCurta;
    }

    public boolean isInternacaoGratuita(int dias){
        return this.internacaoCurta && dias < 7;
    }

}
