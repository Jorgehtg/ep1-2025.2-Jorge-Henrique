import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Paciente> pacientes = new ArrayList<>();
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
                    System.out.println("Medico");
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
            System.out.println("Modo Paciente");
            System.out.println("Cadastrar paciente - 1");
            System.out.println("Atendimento marcado - 2");
            System.out.println("Marcar atendimento - 3");
            System.out.println("Checar histórico de consultas - 4");
            System.out.println("Checar histórico de internações - 5");
            System.out.println("Para voltar à tela anterior - 0");

            int valor = input.nextInt();
            input.nextLine();

            switch (valor) {
                case 1:
                    CadastrarPaciente(input);
                    System.out.println("Paciente cadastrado");
                    break;
                case 2:
                    System.out.println("Atendimentos marcados");
                    return;
                case 3:
                    System.out.println("Atendimento marcado");
                    return;
                case 4:
                    System.out.println("Histórico de consultas");
                    return;
                case 5:
                    System.out.println("Histórico de internações");
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

        System.out.print("Digite a idade do paciente: ");
        int idade = input.nextInt();
        input.nextLine();
        
        do{
            System.out.println("Tem plano de saúde? [S/N]");
            String res = input.nextLine().toUpperCase();

            if(res.equals("S")){
                break;
            } else if(res.equals("N")){
                Paciente p = new Paciente(nome, cpf, idade);
                pacientes.add(p);
                break;
            } else {
                System.out.println("Digite uma resposta valida!");
            } 
        } while(true);
    }
}
