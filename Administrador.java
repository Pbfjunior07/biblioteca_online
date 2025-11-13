
/**
 * Representa o Administrador, que é um tipo de Usuario.
 * Terá permissões para gerenciar o acervo e usuários.
 * Herda da Classe Usuário
 */
public class Administrador extends Usuario {
     
    public Administrador(String nome) {
        super(nome); // Chama o construtor de Usuario(nome)
    }
    
    // No futuro, podemos adicionar métodos aqui:
    // public void gerenciarAcervo() { ... }
}