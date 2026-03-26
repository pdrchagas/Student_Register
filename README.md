
João Lucas
12:21 (há 2 minutos)
para mim

# Sistema de Cadastro de Alunos

Este é um projeto acadêmico desenvolvido para a disciplina do curso de Ciência da Computação da PUC-SP (1º Semestre de 2026). O sistema consiste em uma aplicação Java com Interface Gráfica (GUI) focada no gerenciamento de cadastros de alunos, aplicando fortes conceitos de Programação Orientada a Objetos (POO), arquitetura MVC e separação de responsabilidades.

## Funcionalidades (Requisitos Funcionais)

- **RF01 - Inserir aluno no cadastro:** Permite cadastrar novos estudantes, com proteção contra matrículas (RA) duplicadas e validação da capacidade máxima do sistema.
- **RF02 - Remover um aluno específico:** Remoção de registros através de busca por identificação única (RA).
- **RF03 - Listar alunos cadastrados:** Exibição dinâmica de todos os estudantes cadastrados. Permite a listagem em dois formatos: Padrão ou Formato Bibliográfico (ex: `SOUZA, João Lucas`).
- **RF04 - Atualizar dados de um aluno:** Permite a alteração de dados (Nome, Idade e Curso) de um aluno já existente, buscando pela sua matrícula.

## Regras de Negócio e Validações

O sistema foi blindado ("user-friendly") para não exibir mensagens técnicas de erro ao usuário. Todas as entradas passam por tratamento rígido:
- Não é permitido cadastrar alunos com a mesma matrícula (RA).
- O RA (Registro Acadêmico) aceita exclusivamente números.
- Os campos Nome e Curso aceitam exclusivamente letras e espaços.
- A idade é validada dentro de um intervalo lógico (16 a 120 anos).
- Operações de remoção e atualização bloqueiam tentativas de alterar matrículas inexistentes.

## Arquitetura e Organização (Pacotes)

O código foi refatorado e dividido em pacotes para garantir a legibilidade, reutilização e facilidade de manutenção:

* `modelo/`: Contém as classes de domínio (`Aluno`, `Pessoa`, `Texto`, `NomePessoa`). O encapsulamento foi aplicado rigorosamente.
* `armazenamento/`: Camada de persistência de dados. Utiliza o Princípio de Inversão de Dependência via interface `IArmazenador`, permitindo que a implementação atual (`ArmazenadorArray`) possa ser trocada no futuro (por um Banco de Dados, por exemplo) sem quebrar o código.
* `controle/`: Ponte que aplica as Regras de Negócio (`CadastroAlunos`) conectando a Visão com o Armazenamento.
* `visao/`: Interface com o usuário. Utiliza a interface `IMenu` e foi implementada via `MenuGrafico` (Java Swing via `JOptionPane`), garantindo que entradas e saídas não se misturem com a lógica.

## Como executar

1. Certifique-se de ter o JDK (Java Development Kit) instalado.
2. Clone este repositório.
3. Compile os arquivos ou abra a pasta raiz em uma IDE (como BlueJ, Eclipse ou IntelliJ).
4. Execute o arquivo principal: `App.java`.

---
**Instituição:** PUC-SP - Faculdade de Ciências Exatas e Tecnologia  
**Laboratório:** LED - Laboratório de Estruturas Dinâmicas
