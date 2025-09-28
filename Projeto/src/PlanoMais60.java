public class PlanoMais60 extends PlanoSaude {
    
    public PlanoMais60(String nome, String tipo){
        super(nome,tipo);
    }

    @Override
    public void setDescontoEspecialidade(String especialidade, float desconto){
        this.descontoEspecialidade.put(especialidade, desconto);
    }

}
