
/**
 * Escreva uma descrição da classe Livro aqui.
 * Essa classe representará os livros que comporão o acervo da biblioteca
 * @author Paulo Bernardino Farias Junior 
 * @version 1.0.0 13.11.2025
 */
import java.util.UUID;

public class Livro
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private String id;
    private String titulo;
    private String autor;
    private boolean disponivel;

    /**
     * Construtor para objetos da classe Livro
     * Para criar um novo livro
     */
    public Livro(String titulo, String autor)
    {
        // inicializa variáveis de instância
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    /**
     * Métodos para acesso aos atributos privados
     */
    public String getId()
    {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public boolean isDisponivel() {
        return disponivel; 
    }
    
    // Métodos
    
    /** 
     * 
     *Quando o livro for emprestado, deixar indisponível
     */
    public void emprestar() {
        //valida se o livro está disponível
        if(this.disponivel) { 
            this.disponivel = false;
        } else {
            System.out.println("Erro: O livro '" + titulo + "' já está emprestado.");
        }
        }
        /**
     * Marca o livro como disponível (devolvido).
     */
    public void devolver() {
        if (!this.disponivel) {
            this.disponivel = true;
        } else {
            System.out.println("Aviso: O livro '" + titulo + "' já estava disponível.");
        }
    }

    /**
     * Retorna uma representação em texto do livro (para testes e logs).
     */
    @Override
    public String toString() {
        return "Livro [ID=" + id + ", Titulo=" + titulo + ", Autor=" + autor + ", Disponível=" + disponivel + "]";
    }
    }