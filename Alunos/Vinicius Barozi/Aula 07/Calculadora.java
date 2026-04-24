import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

// ==================== ENDEREÇO ====================
class Endereco {
    String estado, cidade, bairro, rua;

    public Endereco(String estado, String cidade, String bairro, String rua) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public String apresentarLogradouro() {
        return rua + ", " + bairro + " - " + cidade + "/" + estado;
    }
}

// ==================== HERANÇA ====================
class Pessoa {
    String nome;
    int idade;
    Endereco endereco;

    public Pessoa(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + " | Idade: " + idade);
    }
}

// ==================== CLIENTE ====================
class Cliente extends Pessoa {
    public Cliente(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
    }
}

// ==================== VENDEDOR ====================
class Vendedor extends Pessoa {
    String empresa;
    double salarioBase;
    double[] salarioRecebido;

    public Vendedor(String nome, int idade, String empresa, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.empresa = empresa;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new double[]{salarioBase, salarioBase * 1.1, salarioBase * 1.05};
    }

    public double calcularMedia() {
        return Arrays.stream(salarioRecebido).average().orElse(0);
    }

    public double calcularBonus() {
        return salarioBase * 0.20;
    }
}

// ==================== GERENTE ====================
class Gerente extends Pessoa {
    String loja;
    double salarioBase;
    double[] salarioRecebido;

    public Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new double[]{salarioBase, salarioBase * 1.2, salarioBase * 1.1};
    }

    public void apresentarse() {
        System.out.println(nome + " | " + idade + " | Loja: " + loja);
    }

    public double calcularMedia() {
        return Arrays.stream(salarioRecebido).average().orElse(0);
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}

// ==================== ITEM ====================
class Item {
    int id;
    String nome, tipo;
    double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.println(id + " | " + nome + " | " + tipo + " | R$ " + valor);
    }
}

// ==================== PEDIDO ====================
class Pedido {
    int id;
    LocalDate dataCriacao, dataPagamento, dataVencimentoReserva;
    Cliente cliente;
    Vendedor vendedor;
    String loja;
    List<Item> itens;

    public Pedido(int id, Cliente cliente, Vendedor vendedor, String loja, List<Item> itens) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
        this.dataCriacao = LocalDate.now();
        this.dataVencimentoReserva = dataCriacao.plusDays(2);
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(i -> i.valor).sum();
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido criado em: " + dataCriacao);
        System.out.println("Valor total: R$ " + calcularValorTotal());
    }
}

// ==================== PROCESSADOR ====================
class ProcessaPedido {

    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, String loja, List<Item> itens) {
        Pedido pedido = new Pedido(id, cliente, vendedor, loja, itens);

        if (confirmarPagamento(pedido)) {
            pedido.dataPagamento = LocalDate.now();
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Reserva vencida!");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        return LocalDate.now().isBefore(pedido.dataVencimentoReserva);
    }
}

// ==================== LOJA ====================
class Loja {
    String nomeFantasia;
    List<Vendedor> vendedores = new ArrayList<>();
    List<Cliente> clientes = new ArrayList<>();
    List<Gerente> gerentes = new ArrayList<>();

    public Loja(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}

// ==================== MAIN ====================
public class Calculadora {

    static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static final List<LocalDate> datasVendas = new ArrayList<>();
    static final List<String> historico = new ArrayList<>();
    static final Scanner sc = new Scanner(System.in);

    static final Loja loja = new Loja("My Plant");

    static void inicializarDados() {
        Endereco e1 = new Endereco("PR", "Ubiratã", "Centro", "Rua A");
        Endereco e2 = new Endereco("PR", "Ubiratã", "Centro", "Rua B");

        loja.vendedores.add(new Vendedor("Ana", 28, loja.nomeFantasia, e1, 2000));
        loja.clientes.add(new Cliente("Maria", 30, e2));
        loja.gerentes.add(new Gerente("Carlos", 40, loja.nomeFantasia, e1, 5000));
    }

    static double calcularPrecoTotal(int qtd, double preco) {
        double total = qtd * preco;
        if (qtd > 10) total *= 0.95;

        datasVendas.add(LocalDate.now());
        historico.add("Venda: R$ " + total);
        return total;
    }

    static double calcularTroco(double pago, double compra) {
        return pago - compra;
    }

    static void buscarPorDia(String dataStr) {
        LocalDate data = LocalDate.parse(dataStr, FMT);
        long count = datasVendas.stream().filter(d -> d.equals(data)).count();
        System.out.println("Total no dia: " + count);
    }

    static void buscarPorMes(int mes) {
        long count = datasVendas.stream().filter(d -> d.getMonthValue() == mes).count();
        System.out.println("Total no mês: " + count);
    }

    static void menuPedidos() {
        List<Item> itens = new ArrayList<>();
        itens.add(new Item(1, "Rosa", "Flor", 10));
        itens.add(new Item(2, "Vaso", "Acessório", 20));

        Pedido p = new ProcessaPedido().processar(
                1,
                loja.clientes.get(0),
                loja.vendedores.get(0),
                loja.nomeFantasia,
                itens
        );

        p.gerarDescricaoVenda();
    }

    public static void main(String[] args) {
        inicializarDados();

        int op;
        do {
            System.out.println("\n[1] Calcular Preço");
            System.out.println("[2] Troco");
            System.out.println("[3] Histórico");
            System.out.println("[4] Buscar por Dia");
            System.out.println("[5] Buscar por Mês");
            System.out.println("[6] Criar Pedido");
            System.out.println("[7] Sair");

            op = sc.nextInt();

            switch (op) {
                case 1 -> System.out.println("Total: " + calcularPrecoTotal(5, 10));
                case 2 -> System.out.println("Troco: " + calcularTroco(100, 50));
                case 3 -> historico.forEach(System.out::println);
                case 4 -> buscarPorDia("01/01/2024");
                case 5 -> buscarPorMes(1);
                case 6 -> menuPedidos();
            }

        } while (op != 7);
    }
}