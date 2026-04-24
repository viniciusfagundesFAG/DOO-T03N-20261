public abstract class Imovel {

    String endereco;
    double valorMensal;

    public Imovel(String endereco, double valorMensal) {
        this.endereco = endereco;
        this.valorMensal = valorMensal;
    }

    public abstract void exibir();
}