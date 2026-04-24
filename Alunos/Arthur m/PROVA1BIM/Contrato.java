import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Contrato {

    Inquilino inquilino;
    Imovel imovel;
    LocalDate inicio;
    LocalDate fim;
    boolean encerrado;

    public Contrato(Inquilino inquilino, Imovel imovel,
                    LocalDate inicio, LocalDate fim) {

        this.inquilino = inquilino;
        this.imovel = imovel;
        this.inicio = inicio;
        this.fim = fim;
        this.encerrado = false;
    }

    public void encerrar() {
        encerrado = true;
    }

    public double calcularTotal() {
        long meses = ChronoUnit.MONTHS.between(inicio, fim);
        return meses * imovel.valorMensal;
    }

    public void exibir() {
        System.out.println("\n--- CONTRATO ---");
        inquilino.exibir();
        imovel.exibir();
        System.out.println("Início: " + inicio);
        System.out.println("Fim: " + fim);
        System.out.println("Total: R$ " + calcularTotal());
        System.out.println("Status: " + (encerrado ? "Encerrado" : "Ativo"));
    }
}