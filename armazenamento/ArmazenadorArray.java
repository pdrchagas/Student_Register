package armazenamento;

import modelo.Aluno;

/**
 * Implementação concreta de IArmazenador usando Array em memória.
 */
public class ArmazenadorArray implements IArmazenador {
    private Aluno[] alunos;
    private int indice;

    public ArmazenadorArray(int tamanhoMaximo) {
        this.alunos = new Aluno[tamanhoMaximo];
        this.indice = 0;
    }

    @Override
    public boolean inserir(Aluno aluno) {
        if (!isCheio()) {
            alunos[indice] = aluno;
            indice++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remover(String ra) {
        for (int i = 0; i < indice; i++) {
            if (alunos[i].getRa().equalsIgnoreCase(ra)) {
                // Deslocamento para a esquerda
                for (int j = i; j < indice - 1; j++) {
                    alunos[j] = alunos[j + 1];
                }
                alunos[indice - 1] = null;
                indice--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean atualizar(Aluno alunoAtualizado) {
        for (int i = 0; i < indice; i++) {
            if (alunos[i].getRa().equalsIgnoreCase(alunoAtualizado.getRa())) {
                alunos[i] = alunoAtualizado;
                return true;
            }
        }
        return false;
    }

    @Override
    public Aluno buscar(String ra) {
        for (int i = 0; i < indice; i++) {
            if (alunos[i].getRa().equalsIgnoreCase(ra)) {
                return alunos[i];
            }
        }
        return null;
    }

    @Override
    public Aluno[] listar() {
        Aluno[] alunosCadastrados = new Aluno[indice];
        for (int i = 0; i < indice; i++) {
            alunosCadastrados[i] = alunos[i];
        }
        return alunosCadastrados;
    }

    @Override
    public boolean isCheio() {
        return indice >= alunos.length;
    }
}