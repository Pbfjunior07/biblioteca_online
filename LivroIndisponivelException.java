
/**
 * Exceção customizada (Checked Exception) para quando um livro está indisponível.
 * Herda de Exception.
 */
public class LivroIndisponivelException extends Exception {
    public LivroIndisponivelException(String mensagem) {
        super(mensagem);
    }
}