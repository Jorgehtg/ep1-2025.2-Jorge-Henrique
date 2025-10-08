import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GerenciamentoHospitalar{
    static ArrayList<Paciente> pacientes = new ArrayList<>();
    static ArrayList<PacienteEspecial> pacientesEspeciais = new ArrayList<>();
    static ArrayList<String> cpfs = new ArrayList<>();
    static ArrayList<Medico> medicos = new ArrayList<>();
    static ArrayList<Medico> gerais = new ArrayList<>();
    static ArrayList<String> crms = new ArrayList<>();
    static ArrayList<String> especialidades = new ArrayList<>();
    static ArrayList<PlanoSaude> planos = new ArrayList<>();
    static ArrayList<String> nomesPlanos = new ArrayList<>();
    static ArrayList<Consulta> consultas = new ArrayList<>();
    static ArrayList<Internacao> internacoes = new ArrayList<>();
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
    
        while (true){
            System.out.println("========= GERENCIAMENTO HOSPITALAR =========");
            System.out.println("Modo Paciente - 1");
            System.out.println("Modo Medico - 2");
            System.out.println("Modo Administrativo - 3");
            System.out.println("Sair - 0");
            System.out.println("============================================");

            int valor = validarInt(input);

            switch (valor){
                case 1:
                    modoPaciente(input);
                    break;
                case 2:
                    modoMedico(input);
                    break;
                case 3:
                    modoAdministrativo(input);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Escolha uma opção válida");
            }
        }  
    }

    public static void modoPaciente(Scanner input){
        while (true){
            System.out.println("============== MODO PACIENTE ===============");
            System.out.println("Cadastrar paciente - 1");
            System.out.println("Marcar consulta - 2");
            System.out.println("Cancelar cosnulta - 3");
            System.out.println("Checar consultas marcadas - 4");
            System.out.println("Checar histórico de consultas - 5");
            System.out.println("Checar histórico de internações - 6");
            System.out.println("Voltar à tela anterior - 0");
            System.out.println("============================================");

            int valor = validarInt(input);

            switch (valor){
                case 1:
                    System.out.println("============================================");
                    cadastrarPaciente(input);
                    System.out.println("============================================\n");
                    break;
                case 2:
                    System.out.println("============================================");
                    marcarConsulta(input);
                    System.out.println("============================================\n");
                    break;
                case 3:
                    System.out.println("============================================");
                    cancelarConsultaPaciente(input);
                    System.out.println("============================================\n");
                    break;
                case 4:
                    System.out.println("============================================");
                    checarConsultasMarcadas(input);
                    System.out.println("============================================\n");
                    break;
                case 5:
                    System.out.println("============================================");
                    checarHistoricoConsultas(input);
                    System.out.println("============================================\n");
                    break;
                case 6:
                    System.out.println("============================================");
                    checarHistoricoInternacoes(input);
                    System.out.println("============================================\n");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }

    public static void cadastrarPaciente(Scanner input){
        System.out.print("Digite o nome do paciente: ");
        String nome = validarEntrada(input);

        String cpf = validarCpf(input);

        System.out.printf("Digite a idade do paciente: ");
        int idade = validarInt(input);

        System.out.println("O paciente tem plano de saude? [S/N]");
        String resposta = validarResposta(input);

        if (resposta.equals("S")){
            pacienteComPlano(input, nome, cpf, idade);
            System.out.println("Paciente Cadastrado");
            return;
        }
        Paciente paciente = new Paciente(nome, cpf, idade);
        System.out.println("Paciente Cadastrado");
        pacientes.add(paciente);
        cpfs.add(cpf);
        //salvarPaciente;
        return;
    }

    public static void marcarConsulta(Scanner input){
        String cpf = validarCpf(input);
        Paciente paciente = buscarPacientePeloCpf(cpf);
        if (paciente == null) return;
        System.out.println("Deseja alguma especialidade em especifico? [S/N]");
        String resposta1 = validarResposta(input);
        if (resposta1.equals("S")){
            while (true){
                System.out.print("Digite a especialidade: ");
                String especialidade = input.nextLine();
                if (especialidades.contains(especialidade)){
                    registrarConsulta(input, especialidade, paciente);
                    break;
                } else{
                    System.out.println("Desculpa! Não temos essa especialidade, gostaria de tentar outra? [S/N]");
                    String resposta2 = validarResposta(input);
                    if (resposta2.equals("N")){
                        System.out.println("Gostaria de ser atendido(a) por um clinico geral? [S/N]");
                        String resposta3 = validarResposta(input);
                        if (resposta3.equals("S")){
                            registrarConsulta(input, "clinico geral", paciente);
                        } 
                        return;
                    }
                }
            }
        } else{
            registrarConsulta(input, null, paciente);
            
        }
    }

    public static void cancelarConsultaPaciente(Scanner input){
        String cpf = validarCpf(input);
        Paciente paciente = buscarPacientePeloCpf(cpf);
        if (paciente == null) return;
        List<Consulta> consultasPaciente = new ArrayList<>();
        for (int i = 0; i < consultas.size(); i++){
            Consulta consulta = consultas.get(i);
            if (consulta.getPaciente().getCpf().equalsIgnoreCase(cpf) && consulta.getStatus().equalsIgnoreCase("AGENDADA")){
                System.out.printf("%d) Dr. %s %s - Dia %s às %s\n",consultasPaciente.size() + 1, consulta.getMedico().getNome(), consulta.getMedico().getCrm(), consulta.getData(), consulta.getHora());
                consultasPaciente.add(consulta);
            }
        }
        if (consultasPaciente.isEmpty()){
            System.out.println("Sem consultas marcadas");
            return;
        }
        while (true){
            System.out.println("Digite o número da consulta que gostaria de cancelar (0 para cancelar a operação)");
            if (!input.hasNextInt()){
                System.out.println("Digite um numero valido");
                input.nextLine();
            }
            int escolha = validarInt(input);
            if (escolha == 0)return;
            if (escolha >= 1 && escolha <= consultasPaciente.size()){
                Consulta consultaCancelar = consultasPaciente.get(escolha - 1);
                consultaCancelar.setStatus("CANCELADA");
                consultaCancelar.getMedico().removerAtendimento(consultaCancelar);
                //atualizarArquivoConsultas(consultaCancelar);
                System.out.println("Consulta cancelada com sucesso!");
                return;
            } else{
                System.out.println("Digite um numero valido");
            }
        }
    }

    public static void checarConsultasMarcadas(Scanner input){
        String cpf = validarCpf(input);
        Paciente paciente = buscarPacientePeloCpf(cpf);
        if (paciente == null) return;
        boolean temConsultas = false;
        for (Consulta consulta : consultas){
            if (consulta.getPaciente().getCpf().equals(cpf) && consulta.getStatus().equals("AGENDADA")){
                System.out.printf("Atendimento com Dr. %s dia %s às %s\n", consulta.getMedico().getNome(), consulta.getData(), consulta.getHora());
                temConsultas = true;
            }
        }
        if (!temConsultas){
            System.out.println("Você não tem consultas agendadas");
        }
    }

    public static void checarHistoricoInternacoes(Scanner input){
        String cpf = validarCpf(input);
        boolean temInternacoes = false;
        for (Internacao internacao : internacoes){
            if (internacao.getPaciente().getCpf().equals(cpf)){
                System.out.printf("Médico responsavel: Dr. %s - Data de entrada: %s - Data de Saida: %s - Quarto: %d\n", internacao.getMedico().getNome(), internacao.getDataEntrada(), internacao.getDataSaida(), internacao.getNumQuarto());
                temInternacoes = true;
            }
        }
        if (!temInternacoes){
            System.out.println("Nenhuma internação no historico");
        }
    }

    public static void checarHistoricoConsultas(Scanner input){
        String cpf = validarCpf(input);
        boolean temConsultas = false;
        for (Consulta consulta : consultas){
            if (consulta.getPaciente().getCpf().equals(cpf)){
                System.out.printf("Consulta com Dr. %s dia %s às %s", consulta.getMedico().getNome(), consulta.getData(), consulta.getHora());
                temConsultas = true;
            }
        }
        if (!temConsultas){
            System.out.println("Nenhuma consulta no historico");
        }
    }



    public static void modoMedico(Scanner input){
        while(true){
            System.out.println("=============== MODO MEDICO ================");
            System.out.println("Cadastrar Medico - 1");
            System.out.println("Concluir Consulta - 2");
            System.out.println("Cancelar Consulta - 3");
            System.out.println("Checar agenda de consultas - 4");
            System.out.println("Internar um paciente - 5");
            System.out.println("Finalizar uma internação - 6");
            System.out.println("Checar seus pacientes internados - 7");
            System.out.println("Voltar a tela anterior - 0");
            System.out.println("============================================");
            
            int valor = validarInt(input);

            switch (valor){
                case 1:
                    System.out.println("============================================");
                    cadastrarMedico(input);
                    System.out.println("============================================\n");
                    break;
                case 2:
                    System.out.println("============================================");
                    concluirConsulta(input);
                    System.out.println("============================================\n");
                    break;
                case 3:
                    System.out.println("============================================");
                    cancelarConsultaMedico(input);
                    System.out.println("============================================\n");
                    break;
                case 4:
                    System.out.println("============================================");
                    checarAgendaDeConsultas(input);
                    System.out.println("============================================\n");
                    break;
                case 5:
                    System.out.println("============================================");
                    internarPaciente(input);
                    System.out.println("============================================\n");
                    break;
                case 6:
                    System.out.println("============================================");
                    finalizarInternacao(input);
                    System.out.println("============================================\n");
                    break;
                case 7:
                    System.out.println("============================================");
                    pacientesInternados(input);
                    System.out.println("============================================\n");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Digite uma opção valida");
            }
        }
    }

    public static void cadastrarMedico(Scanner input){
        System.out.print("Digite o nome do medico: ");
        String nome = validarEntrada(input);

        String crm = validarCrm(input);
        if (crms.contains(crm)){
            System.out.println("Medico já cadastrado");
            return;
        }

        System.out.println("O medico tem especialidade? [S/N]");
        String resposta = validarResposta(input);
        String especialidade;
        if (resposta.equals("S")){
            System.out.print("Digite a especialidade: ");
            especialidade = input.nextLine().toLowerCase();
        } else{
            especialidade = "clinico geral";
        }
        System.out.print("Digite o valor cobrado pela consulta (Formato 000,00): ");
        double preco = validarDouble(input);
        Medico medico = new Medico(nome, crm, especialidade, preco);
        medicos.add(medico);
        crms.add(crm);
        if (!especialidades.contains(especialidade)){
            especialidades.add(especialidade);
        }
        //salvarMedico()
        System.out.println("Medico cadastrado");
        return;
    }

    public static void concluirConsulta(Scanner input){
        String crm = validarCrm(input);
        Medico medico = buscarMedicoPeloCrm(crm);
        if (medico == null) return;
        String cpf = validarCpf(input);
        List<Consulta> consultasAgendadas = new ArrayList<>();
        int i = 1;
        for (Consulta consulta : consultas){
            if (consulta.getMedico().getCrm().equals(crm) && consulta.getPaciente().getCpf().equals(cpf) && consulta.getStatus().equalsIgnoreCase("AGENDADA")){
                consultasAgendadas.add(consulta);
                System.out.printf("%d) Paciente: %s - Data: %s - Hora: %s\n", i, consulta.getPaciente().getNome(), consulta.getData(), consulta.getHora());
                i++;
            }
        }
        if (consultasAgendadas.isEmpty()){
            System.out.println("Nenhuma consulta agendada com este paciente");
            return;
        }
        System.out.print("Digite o número da consulta que deseja concluir (ou 0 para cancelar a operação): ");
        int escolha;
        while (true){
            escolha = validarInt(input);
            if (escolha == 0) return;
            if (escolha < 1 || escolha > consultasAgendadas.size()){
                System.out.println("Digite um número valido");
                continue;
            }
            break;
        }
        Consulta consulta = consultasAgendadas.get(escolha - 1);
        System.out.print("Digite o diagnostico: ");
        String diagnostico = input.nextLine();
        System.out.println("Tem prescrição? [S/N]");
        String resposta = validarResposta(input);
        if (resposta.equals("S")){
            System.out.print("Digite o nome do remedio: ");
            String remedio = input.nextLine().trim();
            System.out.print("Digite a quantidade de dias que o remedio deve ser consumido: ");
            int dias = validarInt(input);
            Prescricao prescricao = new Prescricao(medico, remedio, dias);
            consulta.concluirConsulta(diagnostico, prescricao);
            medico.removerAtendimento(consulta);
            consulta.getPaciente().addConsultas(consulta);
            //atualizarArquivoConsulta();
            //atualizarListaConsulta();
            System.out.println("Consulta concluida");
            return;     
        }
        consulta.concluirConsulta(diagnostico, null);
        medico.removerAtendimento(consulta);
        consulta.getPaciente().addConsultas(consulta);
        System.out.println("Consulta concluida");
        return;
    }

    public static void cancelarConsultaMedico(Scanner input){
        String crm = validarCrm(input);
        Medico medico = buscarMedicoPeloCrm(crm);
        if (medico == null) return;
        int i = 1;
        List<Consulta> consultasAgendadas = new ArrayList<>();
        for (Consulta consulta : consultas){
            if (consulta.getMedico().getCrm().equals(crm) && consulta.getStatus().equals("AGENDADA")){
                consultasAgendadas.add(consulta);
                System.out.printf("%d) Paciente: %s - Data: %s - Hora: %s\n", i, consulta.getPaciente().getNome(), consulta.getData(), consulta.getHora());
                i++;
            }
        }
        if (consultasAgendadas.isEmpty()){
            System.out.println("Nenhuma consulta agendada");
            return;
        }
        int escolha;
        while (true){
            System.out.print("Digite o número da consulta que gostaria de cancelar (0 para cancelar a operação): ");
            escolha = validarInt(input);
            if(escolha == 0) return;
            if(escolha < 1 || escolha > consultasAgendadas.size()){
                System.out.println("Digite um número valido");
                continue;
            }
            break;
        }
        Consulta consulta = consultasAgendadas.get(escolha-1);
        consulta.setStatus("CANCELADA");
        medico.removerAtendimento(consulta);
        //atualizarArquivoConsultas(consultaCancelar);
        System.out.println("Consulta cancelada");
        return;
    }

    public static void checarAgendaDeConsultas(Scanner input){
        String crm = validarCrm(input);
        Medico medico = buscarMedicoPeloCrm(crm);
        if (medico == null) return;
        if (medico.getAgenda().isEmpty()){
            System.out.println("A agenda está vazia");
        }
        for (String consulta : medico.getAgenda()){
            System.out.println(consulta);
        }
    }

    public static void internarPaciente(Scanner input){
        String crm = validarCrm(input);
        Medico medico = buscarMedicoPeloCrm(crm);
        if (medico == null) return;
        String cpf = validarCpf(input);
        Paciente paciente = buscarPacientePeloCpf(cpf);
        if (paciente == null) return;
        System.out.print("Digite a data de entrada (Formato DD/MM/AAAA): ");
        String dataEntrada = validarData(input);
        System.out.println("Digite o numero do quarto: ");
        int numQuarto = validarInt(input);
        System.out.print("Digite o valor da diaria da internação: ");
        double valor = validarDouble(input);
        Internacao internacao = new Internacao(paciente, medico, dataEntrada, numQuarto, valor);
        internacoes.add(internacao);
        System.out.println("Paciente internado");
    }

    public static void finalizarInternacao(Scanner input){
        String crm = validarCrm(input);
        Medico medico = buscarMedicoPeloCrm(crm);
        if (medico == null) return;
        int i = 1;
        List<Internacao> internacoesAtivas = new ArrayList<>();
        for (Internacao internacao : internacoes){
            if(internacao.getMedico().getCrm().equals(crm) && internacao.getDataSaida() == null){
                internacoesAtivas.add(internacao);
                System.out.printf("%d) Paciente: %s - Quarto: %d - Data de inicio: %s\n", i ,internacao.getPaciente().getNome(), internacao.getNumQuarto(), internacao.getDataEntrada());
                i++;
            }
        }
        if (internacoesAtivas.isEmpty()){
            System.out.println("Sem internações ativas");
            return;
        }
        int escolhida;
        while (true){
            System.out.print("Digite o numero da internaçao que gostaria de finalizar (ou 0 para cancelar a operação): ");
            escolhida = validarInt(input);
            if (escolhida == 0){
                System.out.println("Operação cancelada");
                return;
            }
            if (escolhida < 1 || escolhida > internacoesAtivas.size()){
                System.out.println("Número invalido");
            }
            break;
        }
        Internacao internacao = internacoesAtivas.get(escolhida - 1);
        System.out.print("Digite a data de saida (Formato DD/MM/AAAA): ");
        String dataSaida = validarData(input);
        internacao.finalizarInternacao(dataSaida);
        System.out.printf("Internação finalizada no dia %s", dataSaida);
        return;        
    }

    public static void pacientesInternados(Scanner input){
        String crm = validarCrm(input);
        Medico medico = buscarMedicoPeloCrm(crm);
        if (medico == null) return;
        int i = 1;
        boolean temPaciente = false;
        for (Internacao internacao : internacoes){
            if(internacao.getMedico().getCrm().equals(crm) && internacao.getDataSaida() == null){
                System.out.printf("%d) Paciente: %s - Quarto: %d - Data de inicio: %s\n", i ,internacao.getPaciente().getNome(), internacao.getNumQuarto(), internacao.getDataEntrada());
                temPaciente = true;
                i++;
            }
        }
        if (!temPaciente){
            System.out.println("Sem pacientes internados");
        }
        return;
    }

    public static void modoAdministrativo(Scanner input){
        while (true){
            System.out.println("========= MODO ADMINISTRATIVO =========");
            System.out.println("Cadastrar Plano - 1");
            System.out.println("Cadastrar Paciente - 2");
            System.out.println("Cadastrar Medico - 3");
            System.out.println("Checar Internações ativas - 4");
            System.out.println("Checar Pacientes - 5");
            System.out.println("Checar Medicos - 6");
            System.out.println("Checar Consultas - 7");
            System.out.println("Checar Plano de Saude - 8");
            System.out.println("Checar Estatiticas Gerais - 9");
            System.out.println("Voltar a tela anterior - 0");
            System.out.println("============================================");

            int valor = validarInt(input);

            switch (valor){
                case 1:
                    System.out.println("============================================");
                    cadastrarPlano(input);
                    System.out.println("============================================\n");
                    break;
                case 2:
                    System.out.println("============================================");
                    cadastrarPaciente(input);
                    System.out.println("============================================\n");
                    break;
                case 3:
                    System.out.println("============================================");
                    cadastrarMedico(input);
                    System.out.println("============================================\n");
                    break;
                case 4:
                    System.out.println("============================================");
                    checarInternacoes();
                    System.out.println("============================================\n");
                    break;
                case 5: 
                    System.out.println("============================================");
                    checarPacientes();
                    System.out.println("============================================\n");
                    break;
                case 6:
                    System.out.println("============================================");
                    checarMedicos();
                    System.out.println("============================================\n");
                    break;
                case 7:
                    System.out.println("============================================");
                    checarConsultas();
                    System.out.println("============================================\n");
                    break;
                case 8:
                    System.out.println("============================================");
                    checarPlanos();
                    System.out.println("============================================\n");
                    break;
                case 9:
                    System.out.println("============================================");
                    checarEstatisticas();
                    System.out.println("============================================\n");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Digite uma opção valida");
            }

        }
    }

    public static void cadastrarPlano(Scanner input){
        System.out.print("Digite o nome do plano: ");
        String nome = validarEntrada(input);
        if (nomesPlanos.contains(nome.toLowerCase())) {
            System.out.println("Plano já cadastrado");
            return;
        }
        System.out.println("Digite o tipo do plano: ");
        String tipo = validarEntrada(input);
        System.out.println("Digite o valor de desconto por internação (em porcentagem):");
        int descontoInternacao;
        while(true){
            descontoInternacao = validarInt(input);
            if (descontoInternacao < 0 || descontoInternacao > 100) {
                System.out.println("Desconto de internação deve estar entre 0% e 100%!");
                continue;
            }
            break;
        }
        String[] especialidades;
        String[] descontosNormais;
        String[] descontosIdoso;
        while(true){
            System.out.println("Digite todas as especialidades presentes no plano separadas por vírgula:");
            especialidades = validarEntrada(input).split(",");
            System.out.println("Digite os valores de desconto (em porcentagem) respectivos a cada especialidade separados por vírgula:");
            descontosNormais = validarEntrada(input).split(",");
            System.out.println("Digite os valores de desconto para idosos (em porcentagem) respectivos a cada especialidade separados por vírgula:");
            descontosIdoso = validarEntrada(input).split(",");
            if (especialidades.length != descontosNormais.length || especialidades.length != descontosIdoso.length) {
                System.out.println("O número de especialidades deve ser igual ao número de descontos");
                continue;
            }
            break;
        }
        System.out.println("É um plano especial? [S/N]");
        String resposta = validarResposta(input);
        if (resposta.equals("S")){
            PlanoEspecial plano = new PlanoEspecial(nome, tipo, descontoInternacao);
            for(int i = 0; i < descontosNormais.length; i++){
                double descontoNormal = Double.parseDouble(descontosNormais[i]);
                double descontoIdoso = Double.parseDouble(descontosIdoso[i]);
                plano.addDescontos(especialidades[i], descontoNormal, descontoIdoso);
            }
            planos.add(plano);
            //salvarPlano();
            System.out.println("Plano de Saude especial cadastrado");
            return;
        }
        PlanoSaude plano = new PlanoSaude(nome, tipo, descontoInternacao);
        for(int i = 0; i < descontosNormais.length; i++){
                double descontoNormal = Double.parseDouble(descontosNormais[i]);
                double descontoIdoso = Double.parseDouble(descontosIdoso[i]);
                plano.addDescontos(especialidades[i], descontoNormal, descontoIdoso);
            }
        planos.add(plano);
        //salvarPlano();
        System.out.println("Plano de Saude cadastrado");
        return;
    }

    public static void checarInternacoes(){
        try (FileWriter writer = new FileWriter("internacoes_ativas.txt")){
            writer.write("Nome do paciente - Medico responsavel - Data de começo - Numero do quarto\n");
            for (Internacao internacao : internacoes) {
                if (internacao.getDataSaida() == null){
                    writer.write(internacao.relatorio() + "\n");
                }
            }
            System.out.println("Relatorio exportado com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao exportar arquivo: " + e.getMessage());
        }
    }

    public static void checarPacientes(){
        try (FileWriter writer = new FileWriter("relatorio_pacientes.txt")){
            writer.write("Nome - CPF - Idade - Plano de Saude - Historico de Consultas - Historico de Internacoes\n");
            for (Paciente paciente : pacientes){
                writer.write(paciente.relatorio() + "\n");
            }
            System.out.println("Relatorio exportado com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao exportar arquivo: " + e.getMessage());
        }
    }

    public static void checarMedicos(){
        try (FileWriter writer = new FileWriter("relatorio_medicos.txt")){
            writer.write("Nome - CRM - Especialidade - Historico de Consultas - Numero de Consultas Realizadas\n");
            for(Medico medico : medicos){
                int numConsultas = 0;
                for(Consulta consulta : consultas){
                    if(consulta.getStatus().equals("CONCLUIDA") && consulta.getMedico().getCrm().equals(medico.getCrm())) numConsultas++;
                }
                writer.write(medico.relatorio(numConsultas) + "\n");
            }
            System.out.println("Relatorio exportado com sucesso");
        } catch(IOException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void checarConsultas(){
        try (FileWriter writer = new FileWriter("relatorio_consultas.txt")){
            writer.write("Paciente - Medico - Data - Hora - Local - Status - Diagnostico - Prescricao\n");
            for(Consulta consulta : consultas){
                if(!consulta.getStatus().equals("CANCELADA")){
                    writer.write(consulta.relatorio() + "\n");
                }
            }
            System.out.println("Relatorio exportado com sucesso");
        } catch(IOException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void checarPlanos() {
        try (FileWriter writer = new FileWriter("relatorio_planos.txt")){
            writer.write("Plano de Saude - Valor Economizado\n");
            Map<String, Double> economiaPorPlano = new HashMap<>();
            for (Consulta consulta : consultas){
                if (!consulta.getStatus().equalsIgnoreCase("CONCLUIDA")) continue;
                Paciente paciente = consulta.getPaciente();
                if (!(paciente instanceof PacienteEspecial)) continue;
                PlanoSaude plano = ((PacienteEspecial) paciente).getPlano();
                if (plano == null) continue;
                String especialidade = consulta.getMedico().getEspecialidade();
                double valorBase = consulta.getMedico().getCustoConsulta();
                double descontoPercentual;
                if (paciente.isIdoso()){
                    descontoPercentual = plano.getDescontoIdoso(especialidade);
                } else{
                    descontoPercentual = plano.getDescontoNormal(especialidade);
                }
                double economia = valorBase * (descontoPercentual / 100.0);
                economiaPorPlano.put(plano.getNome(), economiaPorPlano.getOrDefault(plano.getNome(), 0.0) + economia);
            }
            for (PlanoSaude plano : planos) {
                double totalEconomizado = economiaPorPlano.getOrDefault(plano.getNome(), 0.0);
                writer.write(String.format("%s - %.2fR$\n", plano.getNome(), totalEconomizado));
            }
            System.out.println("Relatorio exportado com sucesso");        
        } catch (IOException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void checarEstatisticas(){
        try (FileWriter writer = new FileWriter("estatisticas_gerais.txt")){
            writer.write("Estatisticas Gerais\n");
            writer.write("Numero de pacients cadastrados: " + pacientes.size() + "\n");
            writer.write("Numero de medicos cadastrados: " + medicos.size() + "\n");
            if (!especialidades.isEmpty()){
                for (String especialidade : especialidades){
                    int i = 0;
                    for (Medico medico : medicos){
                        if (medico.getEspecialidade().equals(especialidade)) i++;
                    }
                    writer.write(String.format("Numero de medicos com a %s cadastrados: %d\n", especialidade, i));
                }
            }
            Map<Medico, Integer> consultasPorMedico = new HashMap<>();
            for (Consulta consulta : consultas) {
                if (consulta.getStatus().equalsIgnoreCase("CONCLUIDA")) {
                    Medico medico = consulta.getMedico();
                    consultasPorMedico.put(medico, consultasPorMedico.getOrDefault(medico, 0) + 1);
                }
            }
            Medico medicoMaisAtendeu = null;
            int maxConsultas = 0;
            for (Map.Entry<Medico, Integer> entry : consultasPorMedico.entrySet()) {
                if (entry.getValue() > maxConsultas) {
                    maxConsultas = entry.getValue();
                    medicoMaisAtendeu = entry.getKey();
                }
            }
            if (medicoMaisAtendeu != null) {
                writer.write(String.format("Medico que mais atendeu: Dr. %s %s com %d consultas\n", medicoMaisAtendeu.getNome(), medicoMaisAtendeu.getCrm(), maxConsultas));
            }
            int i = 0;
            for (Consulta consulta : consultas){
                if (consulta.getStatus().equals("CONCLUIDA")) i++;
            }
            writer.write("Numero de consultas concluidas: " + i + "\n");
            int j = 0;
            for(Internacao internacao : internacoes){
                if (internacao.getDataSaida() == null) j++;
            }
            writer.write("Numero de internacoes ativas no momento: " + j + "\n");
            System.out.println("Relatorio exportado com sucesso"); 
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }



    public static void pacienteComPlano(Scanner input, String nome, String cpf, int idade){
        while (true){
            System.out.print("Digite o nome do plano: ");
            String nomePlano = validarEntrada(input);

            if (nomesPlanos.contains(nomePlano)){
                PlanoSaude plano = buscarPlanoPeloNome(nomePlano);
                PacienteEspecial paciente = new PacienteEspecial(nome, cpf, idade, plano);
                pacientes.add(paciente);
                cpfs.add(cpf);
                //salvarPaciente;
                return;

            } else{
                System.out.println("Parece que não trabalhamos com esse plano, gostaria de tentar outro? [S/N]");
                String resposta = validarResposta(input);
                if (resposta.equals("N")){
                    Paciente paciente = new Paciente(nome, cpf, idade);
                    pacientes.add(paciente);
                    cpfs.add(cpf);
                    //salvarPaciente(paciente);
                    return;
                }
            }
        }
    }

    public static void registrarConsulta(Scanner input ,String especialidade, Paciente paciente){
        int i = 1;
        List<Medico> medicosEspecialidade = new ArrayList<>();
        if (especialidade != null){
            for (Medico medico : medicos){
                if (medico.getEspecialidade().equalsIgnoreCase(especialidade)){
                    medicosEspecialidade.add(medico);
                    System.out.printf("%d) Dr %s %s - Especialidade: %s - Preço da consulta: %.2f R$\n", i, medico.getNome(), medico.getCrm(), medico.getEspecialidade(), medico.getCustoConsulta());
                    i++;
                }
            }
        }else{
            for(Medico medico : medicos){
                medicosEspecialidade.add(medico);
                System.out.printf("%d) Dr %s %s - Especialidade: %s - Preço da consulta: %.2f R$\n", i, medico.getNome(), medico.getCrm(), medico.getEspecialidade(), medico.getCustoConsulta());
                i++;
            }

        }
        if(especialidade != null){
            if(medicosEspecialidade.isEmpty()){
                System.out.println("Não temos medicos dessa especialidade disponiveis no momento");
                return;
            }
        } else{
            if(medicosEspecialidade.isEmpty()){
                System.out.println("Não temos medicos disponiveis no momento");
                return;
            }
        }

        System.out.println("Qual medico você gostaria? (Digite o numero ou 0 cancelar a operação)");
        int numEscolhido;
        while(true){
            numEscolhido = validarInt(input); 
            if (numEscolhido == 0) return;
            if (numEscolhido < 1 || numEscolhido > medicosEspecialidade.size()){
                System.out.println("Por favor digite um numero valido");
                continue;
            }
            break;
        }

        Medico medico = medicosEspecialidade.get(numEscolhido-1);
        String data;
        String hora;
        while (true){
            System.out.println("Digite a data da consulta (Formato DD/MM/AAAA): ");
            data = validarData(input);
            System.out.println("Digite o horario da consulta (Formato HH:MM): ");
            hora = validarHora(input);
            if(medico.isHorarioLivre(data, hora)){
                break;
            } else{
                System.out.println("Esse horario já está ocupado. Escolha outro");
            }
        }

        Consulta consulta = new Consulta(paciente, medico, data, hora, "Consultorio");
        consultas.add(consulta);
        medico.addAtendimento(consulta);
        //salvarConsulta(consulta);
        
        System.out.printf("Consulta marcada para %s às %s com Dr. %s", data, hora, medico.getNome());
    }

    public static void descontosPlano(Scanner input){}


    //buscadores
    public static PlanoSaude buscarPlanoPeloNome(String nome){
        for (PlanoSaude plano : planos){
            if (plano.getNome().equalsIgnoreCase(nome)){
                return plano;
            }
        }
        return null;
    }

    public static Paciente buscarPacientePeloCpf(String cpf) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCpf().equals(cpf)) {
                return paciente;
            }
        }
        for (PacienteEspecial paciente : pacientesEspeciais) {
            if (paciente.getCpf().equals(cpf)) {
                return paciente;
            }
        }
        System.out.println("Paciente não encontrado");
        return null;
    }


    public static Medico buscarMedicoPeloCrm(String crm){
        for(Medico medico : medicos){
            if (medico.getCrm().equals(crm)){
                return medico;
            }
        }
        System.out.println("Medico não encotrado");
        return null;
    }


    //validadores
    public static String validarCpf(Scanner input){
        System.out.print("Digite o cpf do paciente (formato 000000000-00): ");
        String cpf;
        while (true){
            cpf = input.nextLine();
            if (!cpf.matches("\\d{9}-\\d{2}")){
                System.out.println("Formato invalido");
                continue;
            }
            return cpf;
        }
    }
    
    public static int validarInt(Scanner input){
        int valor;
        while (true){
            if (input.hasNextInt()){
                valor = input.nextInt();
                input.nextLine();
                if (valor >= 0){
                    return valor;
                } else{
                    System.out.println("Digite um valor positivo");
                }
            } else{
                System.out.println("Digite apenas números");
                input.nextLine();
            }
        }
    }

    public static String validarResposta(Scanner input){
        String resposta;
        while (true){
            resposta = input.nextLine();
            if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("N")){
                return resposta;
            }
            System.out.println("Digite S ou N");
        }
    }

    public static String validarData(Scanner input){
        while(true){
            String data = input.nextLine();
            if (!data.matches("\\d{2}/\\d{2}/\\d{4}")){
                System.out.println("Formato invalido");
                continue;
            }
            String[] dados = data.split("/");
            int dia = Integer.parseInt(dados[0]);
            int mes = Integer.parseInt(dados[1]);
            int ano = Integer.parseInt(dados[2]);

            if (ano<2025){
                System.out.println("Não pode ser um ano que já passou");
                continue;
            }

            boolean anoBissexto = (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) ? true : false;

            if (anoBissexto){
                if (mes < 1 || mes > 12){
                    System.out.println("Digite um mes valido");
                    continue;
                }
                if (mes == 2){
                    if (dia < 1 || dia > 29){
                        System.out.println("Digite um dia entre 1 e 29");
                        continue;
                    }
                }else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
                    if (dia < 1 || dia > 31){
                        System.out.println("Digite um dia entre 1 e 31");
                        continue;
                    }
                } else{
                    if (dia < 1 ||dia > 30){
                        System.out.println("Digite um dia entre 1 e 30");
                        continue;
                    }
                }
            } else{
                if (mes < 1 || mes > 12){
                    System.out.println("Digite um mes valido");
                    continue;
                }
                if (mes == 2){
                    if (dia < 1 || dia > 29){
                        System.out.println("Digite um dia entre 1 e 28");
                        continue;
                    }
                }else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
                    if (dia < 1 || dia > 31){
                        System.out.println("Digite um dia entre 1 e 31");
                        continue;
                    }
                } else{
                    if (dia < 1 ||dia > 30){
                        System.out.println("Digite um dia entre 1 e 30");
                        continue;
                    }
                }
            }
            return data;
        }
    }

    public static String validarHora(Scanner input){
        String hora;
        while (true){
            hora = input.nextLine();
            if (!hora.matches("([01]?\\d|2[0-3]):[0-5]\\d")) {
                System.out.println("Formato invalido");
                continue;
            }
            return hora;
        }
    }

    public static String validarCrm(Scanner input){
        System.out.print("Digite o crm (Formato CRM/XX 000000): ");
        String crm = "";
        while (true){
            crm = input.nextLine().trim().toUpperCase();

            if (!crm.matches("CRM/[A-Z]{2}\\s\\d{6}")){
                System.out.println("Formato invalido! Use CRM/XX 000000");
                continue;
            }
            return crm;
        }
    }

    public static double validarDouble(Scanner input){
        double valor;
        while (true){
            if (input.hasNextDouble()){
                valor = input.nextDouble();
                input.nextLine();
                if (valor >= 0){
                    return valor;
                } else{
                    System.out.println("Digite um valor positivo");
                }
            } else{
                System.out.println("Digite apenas números");
                input.nextLine();
            }
        }
    }

    public static String validarEntrada(Scanner input) {
        String entrada;
        while (true) {
            entrada = input.nextLine().trim().toLowerCase();
            if (!entrada.isEmpty()) {
                return entrada;
            } else {
                System.out.println("Entrada não pode ser vazia");
            }
        }
    }

}
