public class Apartamento extends Imovel {

    int andar;

    public Apartamento(String endereco, double valorMensal, int andar) {
        super(endereco, valorMensal);
        this.andar = andar;
    }

    public void exibir() {
        System.out.println("Apartamento: " + endereco);
        System.out.println("Valor mensal: R$ " + valorMensal);
        System.out.println("Andar: " + andar);
    }
}