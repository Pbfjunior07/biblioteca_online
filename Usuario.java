
import java.util.UUID;

/**
 * Classe base abstrata para todos os usuários do sistema.
 * Define os atributos e comportamentos comuns.
 */
public abstract class Usuario {
    
    // Atributos protegidos (protected) são visíveis para as classes filhas
    protected String id;
    protected String nome;

    public Usuario(String nome) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.nome = nome;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString() {
        // getClass().getSimpleName() pega o nome da classe filha (ex: "Leitor")
        return getClass().getSimpleName() + " [ID=" + id + ", Nome=" + nome + "]";
    }
}