package oi.github.antoniobrandao.clientes;

import oi.github.antoniobrandao.clientes.model.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteTest {

    @Test
    public void testCriarClienteComDadosValidos() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setCpf("12345678901");
        cliente.setEmail("joao@example.com");
        cliente.setTelefone("123456789");

        assertNotNull(cliente);
        assertEquals("João Silva", cliente.getNome());
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("joao@example.com", cliente.getEmail());
        assertEquals("123456789", cliente.getTelefone());
    }

    @Test
    public void testCriarClienteComCpfInvalido() {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Souza");
        cliente.setCpf("123456"); // CPF inválido
        cliente.setEmail("maria@example.com");
        cliente.setTelefone("987654321");

        assertThrows(IllegalArgumentException.class, () -> cliente.getCpf());
    }

    @Test
    public void testPrePersist() {
        Cliente cliente = new Cliente();
        cliente.setNome("José Oliveira");
        cliente.setCpf("98765432109");
        cliente.setEmail("jose@example.com");
        cliente.setTelefone("987654321");

        cliente.prePersist(); // Chama o método prePersist para definir a data de cadastro

        assertNotNull(cliente.getDataCadastro());
        assertEquals(LocalDate.now(), cliente.getDataCadastro());
    }

    @Test
    public void testAtualizarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Pedro Santos");
        cliente.setCpf("54321678901");
        cliente.setEmail("pedro@example.com");
        cliente.setTelefone("987654321");

        // Atualizar dados do cliente
        cliente.setNome("Pedro Almeida");
        cliente.setEmail("pedro.almeida@example.com");

        assertEquals("Pedro Almeida", cliente.getNome());
        assertEquals("pedro.almeida@example.com", cliente.getEmail());
    }

    // Teste de exclusão de cliente pode ser implementado dependendo da sua estratégia de persistência

}
