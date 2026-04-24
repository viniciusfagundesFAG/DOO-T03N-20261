public class Casa extends Imovel {

    boolean quintal;

    public Casa(String endereco, double valorMensal, boolean quintal) {
        super(endereco, valorMensal);
        this.quintal = quintal;
    }

    public void exibir() {
        System.out.println("Casa: " + endereco);
        System.out.println("Valor mensal: R$ " + valorMensal);
        System.out.println("Possui quintal: " + (quintal ? "Sim" : "Não"));
    }
}