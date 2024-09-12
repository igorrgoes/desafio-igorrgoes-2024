import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecintosZooTest {

    private RecintosZoo recintosZoo;

    @BeforeEach
    public void setUp() {
        recintosZoo = new RecintosZoo();
    }

    @Test
    public void deveRejeitarAnimalInvalido() {
        Exception exception = assertThrows(Erro.class, () -> {
            recintosZoo.analisaRecintos(null, 1); 
        });

        String expectedMessage = "Animal Inválido";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void deveRejeitarQuantidadeInvalida() {
        Exception exception = assertThrows(Erro.class, () -> {
            recintosZoo.analisaRecintos("macaco", 0); 
        });

        String expectedMessage = "Quantidade Invalida";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void naoDeveEncontrarRecintosPara10Macacos() {
        Exception exception = assertThrows(Erro.class, () -> {
            recintosZoo.analisaRecintos("macaco", 10); 
        });

        String expectedMessage = "Não há recinto viável";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void deveEncontrarRecintoPara1Crocodilo() {
        try {
            List<Recintos> recintosViaveis = recintosZoo.analisaRecintos("crocodilo", 1);
            assertEquals(1, recintosViaveis.size()); 
            assertEquals("Recinto 4 (espaço livre: 5 total: 8)", recintosViaveis.get(0).toString());
        } catch (Erro e) {
            fail("Erro não esperado: " + e.getMessage());
        }
    }

    @Test
    public void deveEncontrarRecintosPara2Macacos() {
        try {
            List<Recintos> recintosViaveis = recintosZoo.analisaRecintos("macaco", 2);
            assertEquals(3, recintosViaveis.size()); 
            assertEquals("Recinto 1 (espaço livre: 5 total: 10)", recintosViaveis.get(0).toString());
            assertEquals("Recinto 2 (espaço livre: 3 total: 5)", recintosViaveis.get(1).toString());
            assertEquals("Recinto 3 (espaço livre: 2 total: 7)", recintosViaveis.get(2).toString());
        } catch (Erro e) {
            fail("Erro não esperado: " + e.getMessage());
        }
    }
}
