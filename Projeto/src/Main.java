    import java.nio.file.Paths;
    import java.nio.file.Files;
    import java.io.IOException;
    import java.nio.file.StandardOpenOption;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

    public class Main{
        
        static ArrayList<Paciente> pacientes = new ArrayList<>();
        static ArrayList<String> cpfs = new ArrayList<>();
        static ArrayList<Medico> medicos = new ArrayList<>();
        static ArrayList<Medico> gerais = new ArrayList<>();
        static ArrayList<String> especialidades = new ArrayList<>();
        static ArrayList<PlanoSaude> planos = new ArrayList<>();
        static ArrayList<String> nomePlanos = new ArrayList<>();
        static ArrayList<Consulta> consultas = new ArrayList<>();
        static ArrayList<Internacao> internacaos = new ArrayList<>();
        

        public static void main(String[] args){

            carregarPacientesECpfs();
            carregarMedicosEEspecialidades();
            carregarPlanos();
            // carregarConsultas();
            // carregarInternacoes();
            
            
            Scanner input = new Scanner(System.in);

            while (true){
                System.out.println("========= GERENCIAMENTO HOSPITALAR =========");
                System.out.println("Modo Paciente - 1");
                System.out.println("Modo Medico - 2");
                System.out.println("Modo Administrativo - 3");
                System.out.println("Sair - 0");
                System.out.println("============================================");

                int valor = input.nextInt();
                input.nextLine();

                switch (valor){
                    case 1:
                        modoPaciente(input);
                        break;
                    case 2:
                        modoMedico(input);
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
                System.out.println("Marcar atendimento - 2");
                System.out.println("Cancelar atendimento - 3");
                System.out.println("Checar atendimentos marcados - 4");
                System.out.println("Checar histórico de consultas - 5");
                System.out.println("Checar histórico de internações - 6");
                System.out.println("Voltar à tela anterior - 0");
                System.out.println("============================================");

                int valor = input.nextInt();
                input.nextLine();

                switch (valor){
                    case 1:
                        System.out.println("============================================");
                        cadastrarPaciente(input);
                        System.out.println("============================================\n");
                        break;
                    case 2:
                        marcarAtendimento(input);
                        break;
                    case 3:
                        cancelarAtendimento(input);
                        break;
                    case 4:
                        return;
                    case 5:
                        return;
                    case 6:
                        return;
                    case 0:
                        return;
                    default:
                        System.out.println("Digite uma opção válida");
                }
            }
        }

        public static void cadastrarPaciente(Scanner input){
            System.out.print("Digite o nome do paciente: ");
            String nome = input.nextLine();

            System.out.print("Digite o cpf do paciente (formato 000000000-00): ");
            String cpf = "";

            while (true) {
                cpf = input.nextLine();

                if (!cpf.matches("\\d{9}-\\d{2}")){ 
                    System.out.println("Formato invalido! Use: 000000000-00");
                    continue;
                } else{
                        if (cpfs.contains(cpf)){
                            System.out.println("Paciente já cadastrado");
                            return;
                        }
                        break;
                    }
            }

            int idade = 0;
            while (true) {
                System.out.print("Digite a idade do paciente: ");
                
                if (input.hasNextInt()){
                    idade = input.nextInt();
                    input.nextLine();
                    
                    if (idade >= 0){
                        break;
                    } else{
                        System.out.println("Digite um valor positivo");
                    }
                } else{
                    System.out.println("Digite apenas números");
                    input.nextLine();
                }
            }
            
            while (true){
                System.out.println("Tem plano de saúde? [S/N]");
                String res = input.nextLine().toUpperCase();

                if (res.equalsIgnoreCase("S")){
                    while (true){
                        System.out.println("Qual o nome do seu plano?");
                        String nomePlano = input.nextLine();

                        if (nomePlanos.contains(nomePlano)){
                            PlanoSaude plano = buscarPlanoPeloNome(nomePlano);
                            
                            PacienteEspecial paciente = new PacienteEspecial(nome, cpf, idade, plano);
                            
                            pacientes.add(paciente);
                            cpfs.add(cpf);
                            salvarPaciente(paciente);
                            
                            System.out.println("Paciente cadastrado");
                            return;
                        } else{
                            System.out.println("Parece que não trabalhamos com esse plano, gostaria de tentar outro? [S/N]");
                            String res1 = input.nextLine();

                            if (res1.equalsIgnoreCase("S")){
                                continue;
                            } else if (res1.equalsIgnoreCase("N")){
                                Paciente paciente = new Paciente(nome, cpf, idade);
                                
                                pacientes.add(paciente);
                                cpfs.add(cpf);
                                salvarPaciente(paciente);
                                
                                System.out.println("Paciente cadastrado");
                                return;
                            } else{
                                System.out.println("Por favor digite S ou N");
                            }
                        }
                    }
                } else if (res.equals("N")){
                    Paciente p = new Paciente(nome, cpf, idade);
                    
                    pacientes.add(p);
                    cpfs.add(cpf);
                    salvarPaciente(p);
                    
                    System.out.println("Paciente cadastrado");
                    return;
                } else{
                    System.out.println("Digite uma resposta valida!");
                } 
            }
        }

        public static void marcarAtendimento(Scanner input) {
            System.out.print("Digite seu CPF: ");
            String cpf = "";

            while (true) {
                cpf = input.nextLine();

                if (!cpf.matches("\\d{9}-\\d{2}")){ 
                    System.out.println("Formato invalido! Use: 000000000-00");
                    continue;
                }
                break;
            }

            if(!cpfs.contains(cpf)) {
                System.out.println("Desculpa! Não encontramos seu cpf! Gostaria de se cadastrar? [S/N]");
                String res1 = input.nextLine();
                
                if (res1.equalsIgnoreCase("S")){
                    cadastrarPaciente(input);
                }
                return;
            }

            Paciente paciente = buscarPacientePorCpf(cpf);
            
            while (true){
                System.out.println("Alguma especialidade em especifico? [S/N]");
                String res2 = input.nextLine(); 
                
                Medico medico = null;    

                if (res2.equalsIgnoreCase("S")){
                    while (true){
                        System.out.println("Qual especialidade?");
                        String especialidade = input.nextLine();
                        
                        if (especialidades.contains(especialidade)){
                            List<Medico> medicosEspecialidade = mostrarMedicosNumerados(especialidade);

                            while (true){
                                System.out.println("Qual médico você gostaria? (Digite o número)");
                            
                                try{
                                    int numeroMedico = input.nextInt();
                                    input.nextLine();
                                
                                    if (numeroMedico >= 1 && numeroMedico <= medicosEspecialidade.size()){
                                        medico = medicosEspecialidade.get(numeroMedico - 1);
                                        Consulta consulta = registrarConsulta(input, medico, paciente);
                                        if (consulta != null) {
                                            System.out.printf("Atendimento marcado para dia %s às %s com Dr. %s\n", consulta.getData(), consulta.getHora(), medico.getNome());
                                            return;
                                        }
                                        break;
                                    } else{
                                        System.out.println("Número inválido! Tente novamente.");
                                    }
                                } catch (Exception e){
                                    System.out.println("Erro: " + e.getMessage());
                                    input.nextLine();
                                }
                            }
                        } else{
                            System.out.println("Desculpa, ainda não temos essa especialidade! Alguma outra? [S/N]");
                            String res4 = input.nextLine();

                            if (res4.equalsIgnoreCase("S")){
                                continue;
                            } else if (res4.equalsIgnoreCase("N")){
                                System.out.println("Gostaria de marcar com um clinico geral? [S/N]");
                                String res5 = input.nextLine();

                                if (res5.equalsIgnoreCase("S")){
                                    List<Medico> medicosClinico = mostrarMedicosNumerados("Clinico Geral");
                                    
                                    if (!medicosClinico.isEmpty()) {
                                        System.out.println("Qual médico você gostaria? (Digite o número)");
                                        try {
                                            int numeroMedico = input.nextInt();
                                            input.nextLine();
                                            
                                            if (numeroMedico >= 1 && numeroMedico <= medicosClinico.size()) {
                                                medico = medicosClinico.get(numeroMedico - 1);
                                                Consulta consulta = registrarConsulta(input, medico, paciente);
                                                if (consulta != null) {
                                                    System.out.printf("Atendimento marcado para dia %s às %s com Dr. %s\n", consulta.getData(), consulta.getHora(), medico.getNome());
                                                    return;
                                                }
                                            } else {
                                                System.out.println("Número inválido!");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Por favor, digite um número válido.");
                                            input.nextLine();
                                            continue;
                                        }
                                    }
                                    break;
                                } else if(res5.equalsIgnoreCase("N")){
                                    return;
                                } else{
                                    System.out.println("Digite S ou N");
                                }
                            } else {
                                System.out.println("Digite uma resposta valida");
                            }
                        }
                    }
                } else if (res2.equalsIgnoreCase("N")){
                    List<Medico> medicosClinico = mostrarMedicosNumerados("Clinico Geral");
                    
                    if (!medicosClinico.isEmpty()){
                        while (true){
                            System.out.println("Qual médico você gostaria? (Digite o número)");

                            if (!input.hasNextInt()) {
                                System.out.println("Por favor, digite um número válido.");
                                input.nextLine();
                                continue;
                            }

                            int numeroMedico = input.nextInt();
                            input.nextLine();
                            
                            if (numeroMedico >= 1 && numeroMedico <= medicosClinico.size()) {
                                medico = medicosClinico.get(numeroMedico - 1);
                                Consulta consulta = registrarConsulta(input, medico, paciente);
                                if (consulta != null){
                                    System.out.printf("Atendimento marcado para dia %s às %s com Dr. %s\n", consulta.getData(), consulta.getHora(), medico.getNome());
                                    return;
                                }
                                break;
                            } else{
                                System.out.println("Número inválido!");
                            }
                        }
                    }
                } else {
                    System.out.println("Digite S ou N");
                }
            }
        }

        public static void cancelarAtendimento(Scanner input){
            System.out.print("Digite seu cpf: ");
            String cpf = "";

            while (true) {
                cpf = input.nextLine();

                if (!cpf.matches("\\d{9}-\\d{2}")){ 
                    System.out.println("Formato invalido! Use: 000000000-00");
                    continue;
                }
                break;
            }

            if(!cpfs.contains(cpf)) {
                System.out.println("Desculpa! Não encontramos seu cpf! Gostaria de se cadastrar? [S/N]");
                String res1 = input.nextLine();
                
                if (res1.equalsIgnoreCase("S")){
                    cadastrarPaciente(input);
                }
                return;
            }

            for (int i = 0; i < consultas.size(); i ++){
                Consulta consulta = consultas.get(i);
                if (consulta.getPaciente().getCpf().equalsIgnoreCase(cpf) && consulta.getStatus().equalsIgnoreCase("AGENDADA")){
                    System.out.printf("%d. Dr. %s - Dia %s às %s\n", i+1, consulta.getMedico().getNome(), consulta.getData(), consulta.getHora());
                }
            }

            while (true){
                System.out.println("Qual dessas deseja cancelar? (0 para finalizar operação)");
                if (!input.hasNextInt()){
                    System.out.println("Digite um numero valido");
                    input.nextLine();
                }

                int escolha = input.nextInt();
                input.nextLine();

                if (escolha == 0){
                    return;
                }

                if (escolha >= 1 && escolha <= consultas.size()) {
                    Consulta consultaCancelar = consultas.get(escolha - 1);
                    consultaCancelar.setStatus("CANCELADA");
                    consultaCancelar.getMedico().removerAtendimento(consultaCancelar);
                    //atualizarArquivoConsultas();
                    
                    System.out.println("Consulta cancelada com sucesso!");
                }

                
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
                System.out.println("Finalizar internação - 6");
                System.out.println("Checar seus pacientes internados - 7");
                System.out.println("Voltar a tela anterior - 0");
                System.out.println("============================================");
                
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

        public static Paciente buscarPacientePorCpf(String cpf) {
            for (Paciente paciente : pacientes) {
                if (paciente.getCpf().equals(cpf)) {
                    return paciente;
                }
            }
            return null;
        }

        public static PlanoSaude buscarPlanoPeloNome(String nome){
            for (PlanoSaude plano: planos){
                if (plano.getNome().equalsIgnoreCase(nome)){
                    return plano;
                }
            }
            return null;
        }

        public static List<Medico> mostrarMedicosNumerados(String especialidade) {
            List<Medico> medicosFiltrados = new ArrayList<>();
            int numero = 1;
            
            for (Medico medico : medicos) {
                if (medico.getEspecialidade().equalsIgnoreCase(especialidade)) {
                    System.out.printf("%d. Dr. %s - CRM: %s - Preço: R$ %.2f%n", 
                        numero, medico.getNome(), medico.getCRM(), medico.getCustoConsulta());
                    medicosFiltrados.add(medico);
                    numero++;
                }
            }
            
            if (medicosFiltrados.isEmpty()) {
                System.out.println("Nenhum médico encontrado para esta especialidade.");
            }
            return medicosFiltrados;
        }

        

        public static Consulta registrarConsulta(Scanner input, Medico medico, Paciente paciente){

            System.out.println("Qual data você gostaria? (formato DD/MM/AAAA)");
            String data = "";

            while (true){
                data = input.nextLine();

                if (!data.matches("\\d{2}/\\d{2}/\\d{4}")){
                    System.out.println("Formato invalido! Use: DD/MM/AAAA");
                    continue;
                } else{
                    String[] partesData = data.split("/");
                    int dia = Integer.parseInt(partesData[0]);
                    int mes = Integer.parseInt(partesData[1]);
                    int ano = Integer.parseInt(partesData[2]);

                    if (ano >= 2025){
                        if (ano == 2025){
                            if (mes < 10 || mes > 12){
                                System.out.println("Mês inválido");
                                continue;
                            } else{
                                if (mes == 10){
                                    if (dia < 6 || dia > 31){
                                        System.out.println("Dia inválido");
                                        continue;
                                    }
                                } else{
                                    if (mes == 11){
                                        if (dia < 1 || dia > 30){
                                            System.out.println("Dia inválido");
                                            continue;
                                        } else{
                                            if (dia < 1 || dia > 31){
                                                System.out.println("Dia inválido");
                                                continue;
                                            }
                                        }
                                    }
                                }
                            }

                        }
                        if ((ano % 400 == 0) || (ano % 4 == 0 && ano % 100 != 0)){
                            if (mes == 2){
                                if (dia < 1 || dia > 29){
                                    System.out.println("Dia inválido! Digite um valor entre 01 e 29");
                                    continue;
                                }
                            } else if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
                                if (dia < 1 || dia > 31){
                                    System.out.println("Dia inválido! Digite um valor entre 01 e 31");
                                    continue;
                                }
                            } else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
                                if (dia < 1 || dia > 30){
                                    System.out.println("Dia inválido! Digite um valor entre 01 e 30");
                                    continue;
                                }
                            } else{
                                System.out.println("Mês inválido! Digite um valor entre 01 e 12");
                                continue;
                            }
                        } else{
                            if (mes == 2){
                                if (dia < 1 || dia > 28){
                                    System.out.println("Dia inválido! Digite um valor entre 01 e 28");
                                    continue;
                                }
                            } else if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
                                if (dia < 1 || dia > 31){
                                    System.out.println("Dia inválido! Digite um valor entre 01 e 31");
                                    continue;
                                }
                            } else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
                                if (dia < 1 || dia > 30){
                                    System.out.println("Dia inválido! Digite um valor entre 01 e 30");
                                    continue;
                                }
                            } else{
                                System.out.println("Mês inválido! Digite um valor entre 01 e 12");
                                continue;
                            }
                        }
                    } else{
                        System.out.println("O ano não pode ser anterior a 2025");
                        continue;
                        }
                    break;
                }
            }

            System.out.println("Qual horario você gostaria? (formato HH:MM)");
            String horario = "";

            while (true){
                horario = input.nextLine();
                if (!horario.matches("\\d{2}:\\d{2}")){
                    System.out.println("Forma inválido! Use HH:MM");
                    continue;
                }

                String[] partesHora = horario.split(":");
                int hora = Integer.parseInt(partesHora[0]);
                int minutos = Integer.parseInt(partesHora[1]);

                if (hora < 0 || hora > 23){
                    System.out.println("Hora inválida");
                    continue;
                }
                if (minutos < 0 || minutos > 59){
                    System.out.println("Minutos inválidos");
                    continue;
                }
                break;
            }
            
            if (medico.isHorarioLivre(data, horario)){
                Consulta consulta = new Consulta(paciente, medico, data, horario, "Consultório");

                medico.addAtendimento(consulta);
                consultas.add(consulta);
                salvarConsulta(consulta);
                return consulta;
            } else{
                return null;
            }
        }

        public static void mostrarConsultasMarcadas(Paciente paciente){

        }

        public static void salvarPaciente(Paciente paciente){ 
            try{
                Files.write(Paths.get("Dados/Pacientes.csv"), ("\n" + paciente.toString()).getBytes(), StandardOpenOption.APPEND);
            } catch(IOException erro){
                System.out.println("Erro: " + erro.getMessage());
            }
        }

        public static void salvarConsulta(Consulta consulta){
            try{
                Files.write(Paths.get("Dados/Consultas.csv"), ("\n" + consulta.toString()).getBytes(), StandardOpenOption.APPEND);
            } catch(IOException erro){
                System.out.println("Erro: " + erro.getMessage());
            }
        }

        public static void carregarPacientesECpfs(){
            try {
                List<String> linhas = Files.readAllLines(Paths.get("Dados/Pacientes.csv"));
                
                for (int i = 1; i < linhas.size(); i++ ){
                    String linha = linhas.get(i);
                    String[] dados = linha.split(",");
                    
                    int idade = Integer.parseInt(dados[2].trim());

                    Paciente paciente = new Paciente(dados[0].trim(), dados[1], idade);
                    
                    pacientes.add(paciente);
                    if (!cpfs.contains(dados[1].trim())){
                        cpfs.add(dados[1].trim());
                    }
                }
            } catch (IOException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }

        public static void carregarMedicosEEspecialidades(){
            try{
                List<String> linhas = Files.readAllLines(Paths.get("Dados/Medicos.csv"));

                for (int i = 1; i < linhas.size(); i++){
                    String linha = linhas.get(i);
                    String[] dados = linha.split(",");

                    double custoConsulta = Double.parseDouble(dados[3].trim());
                    
                    if (dados[2].equalsIgnoreCase("Clinico Geral")){
                        Medico medico = new Medico(dados[0], dados[1], custoConsulta);
                        medicos.add(medico);
                        if (!especialidades.contains(dados[2].trim())){
                            especialidades.add(dados[2].trim());
                        }
                    } else{
                        Medico medico = new Medico(dados[0], dados[1], dados[2], custoConsulta);
                        medicos.add(medico);
                        if (!especialidades.contains(dados[2].trim())){
                            especialidades.add(dados[2].trim());
                        }
                    }
                }
            } catch (IOException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }

        public static void carregarPlanos() {
            try {
                List<String> linhas = Files.readAllLines(Paths.get("Dados/Planos.csv"));
                
                for (int i = 1; i < linhas.size(); i++) {
                    String linha = linhas.get(i);
                    String[] dados = linha.split(",");
                    
                    if (dados.length >= 2) {
                        String nome = dados[0].trim();
                        String tipo = dados[1].trim();
                        int descontoInternacao = Integer.parseInt(dados[2].trim());
                        
                        PlanoSaude plano = new PlanoSaude(nome, tipo, descontoInternacao);
                        planos.add(plano);
                        nomePlanos.add(nome);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar planos: " + e.getMessage());
            }
        }
}
