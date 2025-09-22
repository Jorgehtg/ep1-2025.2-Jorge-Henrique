public class testes {
    public static void main(String[] args) {
        Medico m1 = new Medico("Cleiton", "00000000", 200.00);
        Medico m2 = new Medico("Vini", "0000000", 300.00);
        Paciente p1 = new Paciente("Lucas", "0000000000", 20);
        Consulta c1 = new Consulta(p1, m1, "21/09", "00:00", "sala 3");
        Consulta c2 = new Consulta(p1, m2, "22/09", "13:00", "sala 2");
        c1.concluirConsulta();
        System.out.println(c1.toString());
        p1.setConsultas(c1);
        p1.setConsultas(c2);
        System.out.println(p1.getConsultas());
    }

}
