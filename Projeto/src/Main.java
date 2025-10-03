import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Paciente> pacientes = new ArrayList<>();
    static ArrayList<Medico> medicos = new ArrayList<>();
    static ArrayList<String> especialidades = new ArrayList<>();
    static ArrayList<String> planos = new ArrayList<>();
    static ArrayList<String> gerais = new ArrayList<>();
    static ArrayList<String> cpfs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("GERENCIAMENTO HOSPITALAR");
            System.out.println("Modo Paciente - 1");
            System.out.println("Modo Medico - 2");
            System.out.println("Modo Administrativo - 3");
            System.out.println("Sair - 0");

            int valor = input.nextInt();
            input.nextLine();

            switch (valor) {
                case 1:
                    ModoPaciente(input);
                    break;
                case 2:
                    ModoMedico(input);
                    break;
                case 3:
                    System.out.println("Adm");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Escolha uma opção válida");
            }
        }
        
    }

    public static void ModoPaciente(Scanner input) {
        while (true) {
            System.out.println("============= MODO PACIENTE ==============");
            System.out.println("Cadastrar paciente - 1");
            System.out.println("Marcar atendimento - 2");
            System.out.println("Cancelar atendimento - 3");
            System.out.println("Checar atendimentos marcados - 4");
            System.out.println("Checar histórico de consultas - 5");
            System.out.println("Checar histórico de internações - 6");
            System.out.println("Para voltar à tela anterior - 0");
            System.out.println("===========================================");

            int valor = input.nextInt();
            input.nextLine();

            switch (valor) {
                case 1:
                    CadastrarPaciente(input);
                    break;
                case 2:
                    MarcarAtendimento(input);
                    System.out.println("Atendimento marcado");
                    break;
                case 3:
                    System.out.println("Atendimento cancelado");
                    return;
                case 4:
                    System.out.println("Atendimentos");
                    return;
                case 5:
                    System.out.println("Histórico Consultas");
                    return;
                case 6:
                    System.err.println("Historico Internações");
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }

    public static void CadastrarPaciente(Scanner input){
        System.out.print("Digite o nome do paciente: ");
        String nome = input.nextLine();

        System.out.print("Digite o cpf do paciente: ");
        String cpf = input.nextLine();
        if(cpfs.contains(cpf)){
            System.out.println("Paciente já cadastrado");
            return;
        }

        System.out.print("Digite a idade do paciente: ");
        int idade = input.nextInt();
        input.nextLine();
        
        do{
            System.out.println("Tem plano de saúde? [S/N]");
            String res = input.nextLine().toUpperCase();

            if(res.equalsIgnoreCase("S")){
                break;
            } else if(res.equals("N")){
                Paciente p = new Paciente(nome, cpf, idade);
                pacientes.add(p);
                cpfs.add(cpf);
                break;
            } else {
                System.out.println("Digite uma resposta valida!");
            } 
        } while(true);
    }

    public static void MarcarAtendimento(Scanner input){
        System.out.print("Digite seu CPF:");
        String cpf = input.nextLine();

        if(cpfs.contains(cpf)){

            do{
                System.out.println("Alguma especialidade em especifico? [S/N]");
                String res = input.nextLine();

                if(res.equalsIgnoreCase("S")){
                    do{
                        System.out.println("Qual?");
                        String res2 = input.nextLine();

                        if(especialidades.contains(res2)){
                            break;
                        }else{
                            System.out.println("Desculpa, ainda não temos essa especialidade! Alguma outra? [S/N]");
                            String res3 = input.nextLine();
                            if(res3.equalsIgnoreCase("S")){
                            }else if(res3.equalsIgnoreCase("N")){
                                System.out.println("Gostaria de marcar com um clinico geral? [S/N]");
                                String res4 = input.nextLine();

                                return;
                            }else{
                                System.out.println("Digite uma respota valida");
                            }
                        }
                    }while (true);
                }else if(res.equalsIgnoreCase("N")){
                    System.out.println("Quais desses medicos você gostaria então?");
                    for(Medico medico: medicos){
                        System.out.println(medico.toString());
                    }
                    String res6 = input.nextLine();
                    return;
                }
            }while (true);
        }else{
            System.out.println("Desculpa! Não encontramos seu cpf! Gostaria de se cadastrar? [S/N]");
            String res5 = input.nextLine();

            if(res5.equalsIgnoreCase("S")){
                CadastrarPaciente(input);
            }
            else{
                return;
            }

        }



    }

    public static void ModoMedico(Scanner input){
        while(true){
            System.out.println("=======MODO MEDICO=======");
            System.out.println("Cadastrar Medico - 1");
            System.out.println("Concluir Consulta - 2");
            System.out.println("Cancelar Consulta - 3");
            System.out.println("Checar agenda de consultas - 4");
            System.out.println("Internar um paciente - 5");
            System.out.println("Finalizar internação - 6");
            System.out.println("Checar seus pacientes internados - 7");
            System.out.println("Voltar a tela anterior - 0");
            
            int valor = input.nextInt();
            input.nextLine();

            switch (valor){
                case 1:
                    System.out.println("Medico cadastrado");
                    break;
                case 2:
                    System.out.println("Consulta concluida");
                    break;
                case 0:
                    return;
            }
        }
        



    }
}
