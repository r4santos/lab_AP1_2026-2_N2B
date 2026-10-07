import javax.print.DocFlavor.READER;

/**
 * Categoria do motorista.
 * Cada categoria tem um percentual de comissão cobrado pela plataforma:
 * BRONZE 25%; PRATA 20%; OURO 15%; DIAMANTE 10%.
 */

    //TODO Tarefa 1: associar a cada constante sua comissão (0.25, 0.20, 0.15, 0.10)
    // (atributo, construtor e método getComissao())
public enum Categoria {
    BRONZE(0.25), PRATA(0.20), OURO(0.15), DIAMANTE(0.10);

    private double comissao;

    private Categoria(double comissao) {
        this.comissao = comissao;
    }

    public double getComissao() {
        return comissao;
    }
}
