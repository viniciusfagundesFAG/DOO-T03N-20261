import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Imobiliaria imobiliaria = new Imobiliaria();

        Inquilino inquilino = null;
        Imovel imovel = null;

        int opcao;

        do {
            System.out.println("\n=== IMOBILIARIA ===");
            System.out.println("[1] Cadastrar Inquilino");
            System.out.println("[2] Cadastrar Apartamento");
            System.out.println("[3] Cadastrar Casa");
            System.out.println("[4] Criar Contrato");
            System.out.println("[5] Encerrar Contrato");
            System.out.println("[6] Listar Contratos Ativos");
            System.out.println("[7] Demonstraçao");
            System.out.println("[0] Sair");
            System.out.print("Opçao: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();

                    inquilino = new Inquilino(nome, cpf, tel);
                    break;

                case 2:
                    System.out.print("Endereço: ");
                    String endA = sc.nextLine();

                    System.out.print("Valor mensal: ");
                    double valA = sc.nextDouble();

                    System.out.print("Andar: ");
                    int andar = sc.nextInt();
                    sc.nextLine();

                    imovel = new Apartamento(endA, valA, andar);
                    break;

                case 3:
                    System.out.print("Endereço: ");
                    String endC = sc.nextLine();

                    System.out.print("Valor mensal: ");
                    double valC = sc.nextDouble();

                    System.out.print("Tem quintal? (true/false): ");
                    boolean q = sc.nextBoolean();
                    sc.nextLine();

                    imovel = new Casa(endC, valC, q);
                    break;

                case 4:
                    if (inquilino != null && imovel != null) {
                        System.out.print("Meses de contrato: ");
                        int meses = sc.nextInt();

                        LocalDate inicio = LocalDate.now();
                        LocalDate fim = inicio.plusMonths(meses);

                        Contrato c = new Contrato(inquilino, imovel, inicio, fim);
                        imobiliaria.adicionarContrato(c);
                    } else {
                        System.out.println("Cadastre inquilino e imóvel primeiro!");
                    }
                    break;

                case 5:
                    if (imobiliaria.total > 0) {
                        imobiliaria.contratos[0].encerrar();
                        System.out.println("Contrato encerrado!");
                    }
                    break;

                case 6:
                    imobiliaria.listarAtivos();
                    break;

                case 7:
                    Inquilino i1 = new Inquilino("João", "111", "9999");
                    Inquilino i2 = new Inquilino("Maria", "222", "8888");

                    Apartamento ap = new Apartamento("Rua A, 123", 1200, 5);
                    Casa casa = new Casa("Rua B, 456", 1800, true);

                    Contrato c1 = new Contrato(
                            i1,
                            ap,
                            LocalDate.of(2025, 1, 1),
                            LocalDate.of(2025, 7, 1)
                    );

                    Contrato c2 = new Contrato(
                            i2,
                            casa,
                            LocalDate.of(2025, 3, 1),
                            LocalDate.of(2025, 9, 1)
                    );

                    c1.encerrar();

                    imobiliaria.adicionarContrato(c1);
                    imobiliaria.adicionarContrato(c2);

                    imobiliaria.listarAtivos();
                    break;
            }

        } while (opcao != 0);

        sc.close();
    }
}