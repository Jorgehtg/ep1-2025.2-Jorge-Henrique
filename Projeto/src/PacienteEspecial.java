import java.util.List;

public class PacienteEspecial extends Paciente{
    public PacienteEspecial(String nome, String cpf, int idade, PlanoSaude plano){
        super(nome, cpf, idade);
        setPlano(plano);
    }

    @Override
    public double calcularCustoConsulta(double precoOriginal, String especialidade){
        if (this.getPlano() == null || this.getPlano().getNome().equalsIgnoreCase("PARTICULAR")){
            return precoOriginal;
        }

        return calcularDescontoEspecialidade(precoOriginal, especialidade);
    }

    private double calcularDescontoEspecialidade(double precoOriginal, String especialidade){
        PlanoSaude plano = this.getPlano();
        List<String> especialidadesPlano = plano.getEspecialidades();

        if (especialidadesPlano == null || !especialidadesPlano.contains(especialidade)){
            return precoOriginal;
        }

        int index = especialidadesPlano.indexOf(especialidade);
        List<Integer> descontosNormais = plano.getDescontosEspecialidades();
        List<Integer> descontosIdosos = plano.getDescontoIdoso();

        if (descontosNormais == null || index >= descontosNormais.size()){
            return precoOriginal;
        }

        int porcentagemDesconto;

        if (this.isIdoso() && descontosIdosos != null){
            porcentagemDesconto = descontosIdosos.get(index);
        } else{
            porcentagemDesconto = descontosNormais.get(index);
        }

        double desconto = precoOriginal*(porcentagemDesconto/100.0);
        return precoOriginal - desconto;
    }

    public double calcularCustoInternacao(double custoDiario, int dias){
        if (this.getPlano() == null || this.getPlano().getNome().equalsIgnoreCase("PARTICULAR")){
            return custoDiario * dias;
        }

        PlanoSaude plano = this.getPlano();

        if (plano instanceof PlanoEspecial){
            PlanoEspecial planoEspecial = (PlanoEspecial) plano;
            if (planoEspecial.isInternacaoGratuita(dias)){
                return 0.0;
            }
        }

        int descontoInternacao = plano.getDescontoInternacao();
        double custoTotal = custoDiario * dias;
        double desconto = custoTotal * (descontoInternacao/100.0);

        return custoTotal - desconto;
    }
}