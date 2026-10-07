
import java.util.ArrayList;
import java.util.List;

/**
 * Motorista que agrega corridas.
 * Complete os métodos marcados com //TODO (Tarefas 2 e 3).
 */
public class Motorista {
    public static final double KM_DESCONTO_COMISSAO = 500;

    private String nome;
    private List<Corrida> corridas;

    public Motorista(String nome) {
        this.nome = nome == null ? "" : nome;
        this.corridas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    /**
     * Adiciona corrida se o código ainda não existir.
     * @return true se adicionou; false se nula ou código duplicado
     */
    public boolean adicionar(Corrida c) {
        if (c == null) {
            return false;
        }
        for (Corrida existente : corridas) {
            if (existente.getCodigo().equals(c.getCodigo())) {
                return false;
            }
        }
        corridas.add(c);
        return true;
    }

    public int quantidadeCorridas() {
        return corridas.size();
    }

    /**
     * Conclui a corrida com o código informado, se ela existir e ainda não estiver concluída.
     */
    public boolean registrarConcluida(String codigo) {
        if (codigo == null) {
            return false;
        }
        for (Corrida c : corridas) {
            if (codigo.equals(c.getCodigo()) && !c.estaConcluida()) {
                c.concluir();
                return true;
            }
        }
        return false;
    }

    public double faturamentoBruto() {
        double soma = 0;
        for (Corrida c : corridas) {
            if (c.estaConcluida()) {
                soma += c.getValor();
            }
        }
        return soma;
    }

    public double kmRodados() {
        double soma = 0;
        for (Corrida c : corridas) {
            if (c.estaConcluida()) {
                soma += c.getKm();
            }
        }
        return soma;
    }

    /**
     * Pela taxa de conclusão (concluídas / total):
     * até 50% BRONZE; até 75% PRATA; até 90% OURO; acima DIAMANTE.
     * Lista vazia → BRONZE.
     */
    public Categoria categoria() {

        int total = quantidadeCorridas(); 
        int concluidas = 0;

        for (Corrida c : corridas) {
            if (c.estaConcluida())
                concluidas += 1;
        }

        double taxa_conclusao = (double) concluidas / total;

        if (taxa_conclusao <= 0.5) 
            return Categoria.BRONZE;

        if (taxa_conclusao <= 0.75)
            return Categoria.PRATA;

        if (taxa_conclusao <= 0.9)
            return Categoria.OURO;
        else
            return Categoria.DIAMANTE;
    }

    /**
     * faturamentoBruto descontada a comissão da categoria.
     * Acima de 500 km rodados, a comissão cai pela metade.
     */
    public double ganhoLiquido() {
        double valor_plataforma = 0;
        if (kmRodados() > 500) 
            valor_plataforma = faturamentoBruto() * (categoria().getComissao() / 2);
        else 
            valor_plataforma = faturamentoBruto() * (categoria().getComissao());

        return faturamentoBruto() - valor_plataforma;
    }

    public String resumo() {
        return nome
                + " | corridas=" + quantidadeCorridas()
                + " | km=" + String.format("%.1f", kmRodados())
                + " | bruto=" + String.format("%.2f", faturamentoBruto())
                + " | " + categoria()
                + " | liquido=" + String.format("%.2f", ganhoLiquido());
    }
}
