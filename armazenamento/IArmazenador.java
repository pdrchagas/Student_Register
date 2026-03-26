package armazenamento;

import modelo.Aluno;

/**
 * Interface que define o contrato para o armazenamento de alunos.
 */
public interface IArmazenador {
    boolean inserir(Aluno aluno);
    boolean remover(String ra);
    boolean atualizar(Aluno alunoAtualizado);
    Aluno buscar(String ra);
    Aluno[] listar();
    boolean isCheio();
}