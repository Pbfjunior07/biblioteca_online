
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um Leitor, que é um tipo de Usuario.
 * Pode pegar livros emprestados e consultar seu histórico.
 */
public class Leitor extends Usuario {
    
    // Atributo específico do Leitor
    private List<Emprestimo> historicoEmprestimos;

    /**
     * Construtor do Leitor.
     * Chama o construtor da classe-mãe (Usuario) usando 'super()'.
     */
    public Leitor(String nome) {
        super(nome); // Chama o construtor de Usuario(nome)
        this.historicoEmprestimos = new ArrayList<>();
    }

    // --- Métodos Específicos do Leitor ---
    
    public void adicionarEmprestimoAoHistorico(Emprestimo emprestimo) {
        this.historicoEmprestimos.add(emprestimo);
    }

    public void consultarHistorico() {
        System.out.println("---- Histórico de Empréstimos de " + getNome() + " ----");
        if (historicoEmprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado.");
        } else {
            for (Emprestimo emp : historicoEmprestimos) {
                System.out.println(emp); // Usa o toString() da classe Emprestimo
            }
        }
    }
}