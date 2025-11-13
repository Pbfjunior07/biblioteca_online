
/**
 * Exceção customizada (Checked Exception) para quando um livro não é encontrado no acervo.
 * Herda de Exception, o que a torna "checked" (verificada).
 */
public class LivroNaoEncontradoException extends Exception {
    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}