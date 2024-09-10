package oi.github.antoniobrandao.clientes.rest;
import oi.github.antoniobrandao.clientes.model.entity.Cliente;
import oi.github.antoniobrandao.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    @Configuration
    public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
     registry.addMapping("/**")
     .allowedOrigins("http://localhost:4200") // Permitir solicitações apenas de http://localhost:4200
     .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir os métodos HTTP especificados
     .allowedHeaders("*"); // Permitir todos os cabeçalhos
      }
    }
    private final ClienteRepository repository;
    
    @Autowired
    public ClienteController(ClienteRepository repository){
        this.repository = repository;
    }

    @GetMapping   // ADICIONEI ESSE MÉTODO -----------------
    public List<Cliente> obterTodos(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente) {
        if (repository.existsByCpf(cliente.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
        }
        return repository.save(cliente);
    }
    @GetMapping("/verificar-cpf/{cpf}")
    public boolean verificarCPFCadastrado(@PathVariable String cpf) {
        return repository.existsByCpf(cpf);
    }
    @GetMapping("{id}")
    public Cliente acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){

        repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar (@PathVariable Integer id, @RequestBody Cliente clienteAtualizado){
        repository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    cliente.setEmail(clienteAtualizado.getEmail());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    // Atualize outros atributos conforme necessário
                    return repository.save(cliente); // Salve o cliente existente com as atualizações
                })
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
