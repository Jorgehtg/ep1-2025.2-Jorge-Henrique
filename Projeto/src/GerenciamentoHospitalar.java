import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class GerenciamentoHospitalar{
    static ArrayList<Paciente> pacientes = new ArrayList<>();
    static ArrayList<String> cpfs = new ArrayList<>();
    static ArrayList<Medico> medicos = new ArrayList<>();
    static ArrayList<Medico> gerais = new ArrayList<>();
    static ArrayList<String> crms = new ArrayList<>();
    static ArrayList<String> especialidades = new ArrayList<>();
    static ArrayList<PlanoSaude> planos = new ArrayList<>();
    static ArrayList<String> nomesPlanos = new ArrayList<>();
    static ArrayList<Consulta> consultas = new ArrayList<>();
    static ArrayList<Internacao> internacaos = new ArrayList<>();
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
                    //modoMedico(input);
                    break;
                case 3:
                    // modoAdministrativo(input);
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
                    //checarAtendimentosMarcados(input);
                    System.out.println("============================================\n");
                    break;
                case 5:
                    System.out.println("============================================");
                    //checarHistoricoConsultas(input);
                    System.out.println("============================================\n");
                    break;
                case 6:
                    System.out.println("============================================");
                    //checarHistoricoInternacoes(input);
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
        String nome = input.nextLine().toLowerCase();

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
        if (!cpfs.contains(cpf)){
            System.out.println("CPF não encontrado, gostaria de se cadastrar? [S/N]");
            String res = validarResposta(input);
            if (res.equals("S")){
                cadastrarPaciente(input);
            } else{
                return;
            }
        }
        Paciente paciente = buscarPacientePeloCpf(cpf);
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
        List<Consulta> consultasPaciente = new ArrayList<>();

        for (int i = 0; i < consultas.size(); i ++){
            Consulta consulta = consultas.get(i);
            if (consulta.getPaciente().getCpf().equalsIgnoreCase(cpf) && consulta.getStatus().equalsIgnoreCase("AGENDADA")){
                System.out.printf("%d. Dr. %s %s - Dia %s às %s\n", i+1, consulta.getMedico().getNome(), consulta.getMedico().getCRM(), consulta.getData(), consulta.getHora());
                consultasPaciente.add(consulta);
            }
        }

        if (consultasPaciente.isEmpty()){
            System.out.println("Sem consultas marcadas");
            return;
        }

        while (true){
            System.out.println("Qual dessas deseja cancelar? (Digite o numero ou 0 para cancelar a operação)");
            if (!input.hasNextInt()){
                System.out.println("Digite um numero valido");
                input.nextLine();
            }

            int escolha = validarInt(input);

            if (escolha == 0){
                return;
            }

            if (escolha >= 1 && escolha <= consultas.size()) {
                Consulta consultaCancelar = consultas.get(escolha - 1);
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

    public static void checarConsultasAgendadas(Scanner input){
        String cpf = validarCpf(input);
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
        for (Internacao internacao : internacaos){
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


    public static void pacienteComPlano(Scanner input, String nome, String cpf, int idade){
        while (true){
            System.out.print("Digite o nome do plano: ");
            String nomePlano = input.nextLine().toLowerCase();
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
                    System.out.printf("%d. Dr %s %s - Especialidade: %s - Preço da consulta: %.2fR$\n", i, medico.getNome(), medico.getCRM(), medico.getEspecialidade(), medico.getCustoConsulta());
                    i++;
                }
            }
        }else{
            for(Medico medico : medicos){
                medicosEspecialidade.add(medico);
                System.out.printf("%d. Dr %s %s - Especialidade: %s - Preço da consulta: %.2fR$\n", i, medico.getNome(), medico.getCRM(), medico.getEspecialidade(), medico.getCustoConsulta());
                i++;
            }

        }

        if(medicosEspecialidade.isEmpty()){
            System.out.println("Não temos medicos dessa especialidade no momento");
            return;
        }
            
        System.out.println("Qual medico você gostaria? (Digite o numero ou 0 para sair)");
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

        System.out.println("Digite a data da consulta (Formato DD/MM/AAAA): ");
        String data = validarData(input);

        System.out.println("Digite o horario da consulta (Formato HH:MM): ");
        String hora = validarHora(input);

        Consulta consulta = new Consulta(paciente, medico, data, hora, "Consultorio");
        consultas.add(consulta);
        medico.addAtendimento(consulta);
        //salvarConsulta(consulta);
        
        System.out.printf("Consulta marcada para %s às %s com Dr. %s", data, hora, medico.getNome());
    }


    //buscadores
    public static PlanoSaude buscarPlanoPeloNome(String nome){
        for (PlanoSaude plano : planos){
            if (plano.getNome().equalsIgnoreCase(nome)){
                return plano;
            }
        }
        return null;
    }

    public static Paciente buscarPacientePeloCpf(String cpf){
        for(Paciente paciente : pacientes){
            if (paciente.getCpf().equals(cpf)){
                return paciente;
            }
        }
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
        String data;
        while(true){
            data = input.nextLine();
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
                return data;
            }
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

}
