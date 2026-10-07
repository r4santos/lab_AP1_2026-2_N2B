import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Os testes prontos servem de exemplo.
 * Complete os testes marcados com //TODO (Tarefas 4 e 5).
 */
public class MotoristaTest {

    private Motorista motorista;

    @BeforeEach
    void setUp() {
        motorista = new Motorista("Bia");
    }

    @Test
    void naoDeveAdicionarCodigoDuplicado() {
        assertTrue(motorista.adicionar(new Corrida("C1", 10, 30)));
        assertFalse(motorista.adicionar(new Corrida("C1", 5, 20)));
        assertEquals(1, motorista.quantidadeCorridas());
    }

    @Test
    void deveRegistrarConcluidaPorCodigo() {
        motorista.adicionar(new Corrida("C1", 10, 30));
        assertTrue(motorista.registrarConcluida("C1"));
        assertFalse(motorista.registrarConcluida("C1"), "já concluída");
        assertFalse(motorista.registrarConcluida("C9"), "inexistente");
    }

    @Test
    void deveSomarFaturamentoEKmApenasDeConcluidas() {
        motorista.adicionar(new Corrida("C1", 10, 30));
        motorista.adicionar(new Corrida("C2", 20, 50));
        motorista.registrarConcluida("C1");
        assertEquals(30.0, motorista.faturamentoBruto(), 0.001);
        assertEquals(10.0, motorista.kmRodados(), 0.001);
    }

    @Test
    void resumoDeveConterNomeECategoria() {
        motorista.adicionar(new Corrida("C1", 10, 30));
        String r = motorista.resumo();
        assertTrue(r.contains("Bia"), r);
        assertTrue(r.contains("BRONZE"), r);
    }

    @Test
    void deveDefinirCategoria() {
        //TODO Tarefa 4: testar categoria em pelo menos dois cenários
        // (ex.: 4 corridas com 3 concluídas → PRATA; 4 com 4 concluídas → DIAMANTE)
        motorista.adicionar(new Corrida("C1", 10, 30));
        motorista.adicionar(new Corrida("C2", 20, 40));
        motorista.adicionar(new Corrida("C3", 30, 50));
        motorista.adicionar(new Corrida("C4", 40, 60));
        motorista.registrarConcluida("C1");
        motorista.registrarConcluida("C2");
        motorista.registrarConcluida("C3");
        assertEquals(motorista.categoria(), Categoria.PRATA);
        motorista.registrarConcluida("C4");
        assertEquals(motorista.categoria(), Categoria.DIAMANTE);
    }

    @Test
    void deveCalcularGanhoLiquido() {
        //TODO Tarefa 5: testar ganhoLiquido usando a comissão da categoria
        // e o desconto de comissão acima de 500 km
        motorista.adicionar(new Corrida("C1", 10, 30));
        motorista.registrarConcluida("C1");
        assertEquals(motorista.ganhoLiquido(), 27, 0.001);
        motorista.adicionar(new Corrida("C2", 491, 70));
        motorista.registrarConcluida(("C2"));
        assertEquals(motorista.ganhoLiquido(), 95, 0.001);
    }
}
