package controle;

import armazenamento.IArmazenador;
import armazenamento.ArmazenadorArray;
import modelo.Aluno;

/**
 * Controlador com as regras de negócio.
 */
public class CadastroAlunos {
    private IArmazenador armazenador;

    public CadastroAlunos(int qtde) {
        this.armazenador = new ArmazenadorArray(qtde);
    }

    public boolean inserir(Aluno aluno) {
        return armazenador.inserir(aluno);
    }

    public boolean remover(String ra) {
        return armazenador.remover(ra);
    }
    
    public boolean atualizar(Aluno aluno) {
        return armazenador.atualizar(aluno);
    }

    public Aluno buscar(String ra) {
        return armazenador.buscar(ra);
    }

    public Aluno[] listar() {
        return armazenador.listar();
    }
    
    // Regra de Negócio: Capacidade Máxima
    public boolean isCheio() {
        return armazenador.isCheio();
    }
    
    // Regra de Negócio: Matrícula Duplicada
    public boolean matriculaExiste(String ra) {
        return armazenador.buscar(ra) != null;
    }
    
    // Regra de Negócio: Idade válida (Exemplo: entre 16 e 120 anos)
    public boolean isIdadeValida(int idade) {
        return idade >= 16 && idade <= 80;
    }
}