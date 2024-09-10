package oi.github.antoniobrandao.clientes;

import oi.github.antoniobrandao.clientes.model.entity.Funcionario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    @Test
    public void testFuncionarioValido() {
        Funcionario funcionario = Funcionario.builder()
                .nome("João da Silva")
                .cpf("123.456.789-09")
                .cargo("Desenvolvedor")
                .email("joao.silva@example.com")
                .telefone("123456789")
                .build();

        assertNotNull(funcionario);
        assertEquals("João da Silva", funcionario.getNome());
        assertEquals("123.456.789-09", funcionario.getCpf());
        assertEquals("Desenvolvedor", funcionario.getCargo());
        assertEquals("joao.silva@example.com", funcionario.getEmail());
        assertEquals("123456789", funcionario.getTelefone());
    }

    @Test
    public void testFuncionarioComNomeNulo() {
        Funcionario funcionario = Funcionario.builder()
                .nome(null)
                .cpf("123.456.789-09")
                .cargo("Desenvolvedor")
                .email("joao.silva@example.com")
                .telefone("123456789")
                .build();

        assertNotNull(funcionario);
        assertNull(funcionario.getNome());
        assertEquals("123.456.789-09", funcionario.getCpf());
        assertEquals("Desenvolvedor", funcionario.getCargo());
        assertEquals("joao.silva@example.com", funcionario.getEmail());
        assertEquals("123456789", funcionario.getTelefone());
    }

    @Test
    public void testFuncionarioComCpfInvalido() {
        Funcionario funcionario = Funcionario.builder()
                .nome("João da Silva")
                .cpf("12345678900") // CPF inválido
                .cargo("Desenvolvedor")
                .email("joao.silva@example.com")
                .telefone("123456789")
                .build();

        assertNotNull(funcionario);
        assertEquals("João da Silva", funcionario.getNome());
        assertEquals("12345678900", funcionario.getCpf());
        assertEquals("Desenvolvedor", funcionario.getCargo());
        assertEquals("joao.silva@example.com", funcionario.getEmail());
        assertEquals("123456789", funcionario.getTelefone());
    }
}
