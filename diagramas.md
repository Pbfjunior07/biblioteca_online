classDiagram
    direction RL

    class Biblioteca {
        -List~Livro~ acervo
        -List~Usuario~ usuarios
        -List~Emprestimo~ emprestimosAtivos
        +adicionarLivro(Livro livro)
        +removerLivro(Livro livro)
        +cadastrarUsuario(Usuario usuario)
        +buscarLivro(String titulo) Livro
        +realizarEmprestimo(Livro livro, Leitor leitor)
        +realizarDevolucao(Emprestimo emprestimo)
    }

    class Usuario {
        <<abstract>>
        -String id
        -String nome
        +String getNome()
    }

    class Leitor {
        -List~Emprestimo~ historicoEmprestimos
        +consultarHistorico()
    }

    class Administrador {
        +gerenciarAcervo()
    }

    class Livro {
        -String id
        -String titulo
        -String autor
        -boolean disponibilidade
        +emprestar()
        +devolver()
        +isDisponivel() boolean
    }

    class Emprestimo {
        -LocalDate dataEmprestimo
        -LocalDate dataDevolucaoPrevista
        -LocalDate dataDevolucaoReal
        -Livro livro
        -Leitor leitor
        +calcularMulta() double
    }

    ' --- Relacionamentos ---
    Usuario <|-- Leitor : "Herda"
    Usuario <|-- Administrador : "Herda"

    Biblioteca "1" o-- "0..*" Livro : "Gerencia (Acervo)"
    Biblioteca "1" o-- "0..*" Usuario : "Gerencia (Usuários)"
    Biblioteca "1" ..> "0..*" Emprestimo : "Gerencia (Empréstimos)"

    Emprestimo "1" -- "1" Livro : "Refere-se a"
    Emprestimo "1" -- "1" Leitor : "Realizado por"