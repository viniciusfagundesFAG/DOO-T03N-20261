public class Imobiliaria {

    Contrato[] contratos = new Contrato[10];
    int total = 0;

    public void adicionarContrato(Contrato c) {
        if (total < 10) {
            contratos[total] = c;
            total++;
        } else {
            System.out.println("Limite de contratos atingido!");
        }
    }

    public void listarAtivos() {
        System.out.println("\n--- CONTRATOS ATIVOS ---");

        for (int i = 0; i < total; i++) {
            if (!contratos[i].encerrado) {
                contratos[i].exibir();
            }
        }
    }
}