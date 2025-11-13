import java.time.LocalDate; // Para trabalhar com datas
import java.time.temporal.ChronoUnit; // Para calcular diferença entre datas

/**
 * Representa a transação de empréstimo de um Livro para um Leitor.
 */
public class Emprestimo {
    
    // Constante para a política de multa (ex: R$ 1.50 por dia)
    private static final double MULTA_POR_DIA = 1.50;
    
    // Constante para o prazo de empréstimo (ex: 14 dias)
    private static final int PRAZO_EMPRESTIMO_DIAS = 14;

    private Livro livro;
    private Leitor leitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal; // Fica nulo até a devolução

    public Emprestimo(Livro livro, Leitor leitor) {
        this.livro = livro;
        this.leitor = leitor;
        this.dataEmprestimo = LocalDate.now(); // Pega a data de hoje
        
        // Calcula a data de devolução prevista
        this.dataDevolucaoPrevista = this.dataEmprestimo.plusDays(PRAZO_EMPRESTIMO_DIAS);
        
        this.dataDevolucaoReal = null; // Ainda não foi devolvido
    }

    // --- Getters ---
    public Livro getLivro() {
        return livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    /**
     * Registra a devolução e retorna a multa (se houver).
     * @return O valor da multa por atraso.
     */
    public double devolver() {
        this.dataDevolucaoReal = LocalDate.now(); // Marca a data de hoje
        this.livro.devolver(); // Atualiza o status do livro
        return calcularMulta();
    }
    
    /**
     * Calcula a multa com base na data de devolução real.
     */
    public double calcularMulta() {
        // Se ainda não foi devolvido, não há multa
        if (dataDevolucaoReal == null) {
            return 0.0;
        }

        // ChronoUnit calcula o número de dias entre as datas
        long diasDeAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);

        // Se a diferença for positiva (devolveu depois do prazo), calcula a multa
        if (diasDeAtraso > 0) {
            return diasDeAtraso * MULTA_POR_DIA;
        }

        // Se devolveu no prazo ou antes
        return 0.0;
    }
    
    @Override
    public String toString() {
        String dataDevolvida = (dataDevolucaoReal != null) ? dataDevolucaoReal.toString() : "Pendente";
        
        return "Emprestimo [Livro: " + livro.getTitulo() + 
               ", Data: " + dataEmprestimo + 
               ", Devolução Prevista: " + dataDevolucaoPrevista +
               ", Devolvido em: " + dataDevolvida + "]";
    }
}