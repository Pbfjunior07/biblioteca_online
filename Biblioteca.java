
import java.util.List;
import java.util.Optional; 
import java.util.ArrayList;

/**
 * Classe central que gerencia todo o sistema da biblioteca.
 * Controla o acervo de livros, o cadastro de usuários e as operações
 * de empréstimo e devolução.
 */
public class Biblioteca {

    // --- Atributos (Encapsulados) ---
    private List<Livro> acervo;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimosAtivos;

    /**
     * Construtor da Biblioteca.
     * Inicializa as listas vazias.
     */
    public Biblioteca() {
        this.acervo = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimosAtivos = new ArrayList<>();
        System.out.println("Sistema de Biblioteca Online iniciado.");
    }

    // --- Métodos de Gerenciamento ---

    public void adicionarLivro(Livro livro) {
        this.acervo.add(livro);
        System.out.println("Livro adicionado ao acervo: " + livro.getTitulo());
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        System.out.println("Usuário cadastrado: " + usuario.getNome());
    }
    
    /**
     * Lista todos os livros no acervo e seu status.
     */
    public void consultarAcervo() {
        System.out.println("\n---- Acervo da Biblioteca ----");
        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
            return;
        }
        for (Livro livro : acervo) {
            System.out.println(livro); // Usa o toString() do Livro
        }
    }

    // --- Métodos de Busca (Helpers) ---

    /**
     * Busca um livro no acervo pelo título.
     * @param titulo O título exato do livro.
     * @return O objeto Livro.
     * @throws LivroNaoEncontradoException Se o livro não for achado.
     */
    public Livro buscarLivroPorTitulo(String titulo) throws LivroNaoEncontradoException {
        // Itera pela lista 'acervo'
        for (Livro livro : acervo) {
            // Compara os títulos ignorando maiúsculas/minúsculas
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro; // Encontrou!
            }
        }
        // Se o loop terminar e não encontrar, lança a exceção
        throw new LivroNaoEncontradoException("O livro com o título '" + titulo + "' não foi encontrado.");
    }
    
    /**
     * Busca um leitor na lista de usuários pelo ID.
     */
    public Leitor buscarLeitorPorId(String id) {
        for (Usuario usuario : usuarios) {
            // Verifica se o usuário é do tipo Leitor E se o ID bate
            if (usuario instanceof Leitor && usuario.getId().equals(id)) {
                return (Leitor) usuario; // Faz o "cast" para Leitor
            }
        }
        return null; // Retorna nulo se não achar
    }

    // --- Lógica Principal: Empréstimo e Devolução ---

    /**
     * Orquestra a operação de empréstimo.
     * @param tituloDoLivro O título do livro a ser emprestado.
     * @param leitor O leitor que está pegando o livro.
     */
    public void realizarEmprestimo(String tituloDoLivro, Leitor leitor) {
        try {
            // 1. Encontrar o livro
            Livro livro = buscarLivroPorTitulo(tituloDoLivro);
            
            // 2. Verificar a disponibilidade
            if (!livro.isDisponivel()) {
                throw new LivroIndisponivelException("O livro '" + livro.getTitulo() + "' não está disponível.");
            }
            
            // 3. Processar o empréstimo
            livro.emprestar(); // Marca o livro como indisponível
            Emprestimo novoEmprestimo = new Emprestimo(livro, leitor);
            this.emprestimosAtivos.add(novoEmprestimo);
            leitor.adicionarEmprestimoAoHistorico(novoEmprestimo); // Adiciona ao histórico pessoal
            
            System.out.println("\n>>> Empréstimo realizado com sucesso!");
            System.out.println(">>> Livro: " + livro.getTitulo());
            System.out.println(">>> Leitor: " + leitor.getNome());
            System.out.println(">>> Devolver até: " + novoEmprestimo.getDataDevolucaoPrevista());

        } catch (LivroNaoEncontradoException | LivroIndisponivelException e) {
            // Captura as exceções que nós criamos e exibe a mensagem de erro
            System.out.println("\n*** Falha no empréstimo: " + e.getMessage() + " ***");
        }
    }
    
    /**
     * Orquestra a operação de devolução.
     * @param tituloDoLivro O título do livro a ser devolvido.
     * @param leitor O leitor que está devolvendo.
     */
    public void realizarDevolucao(String tituloDoLivro, Leitor leitor) {
        // Procura o empréstimo ativo para este livro e este leitor
        Emprestimo emprestimoParaEncerrar = null;
        for (Emprestimo emp : emprestimosAtivos) {
            if (emp.getLivro().getTitulo().equalsIgnoreCase(tituloDoLivro) && 
                emp.getLeitor().equals(leitor)) {
                emprestimoParaEncerrar = emp;
                break;
            }
        }
        
        // Se encontrou o empréstimo
        if (emprestimoParaEncerrar != null) {
            double multa = emprestimoParaEncerrar.devolver(); // Isso já atualiza o livro internamente
            
            // Remove da lista de empréstimos *ativos*
            this.emprestimosAtivos.remove(emprestimoParaEncerrar);
            
            System.out.println("\n<<< Devolução realizada com sucesso!");
            System.out.println("<<< Livro: " + tituloDoLivro);
            if (multa > 0) {
                System.out.printf("<<< MULTA POR ATRASO: R$ %.2f\n", multa);
            } else {
                System.out.println("<<< Devolvido no prazo.");
            }
            
        } else {
            System.out.println("\n*** Falha na devolução: Empréstimo não localizado para este livro e leitor. ***");
        }
    }
}