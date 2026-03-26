package visao;

import controle.CadastroAlunos;

/**
 * Interface para garantir o padrao de entradas e saidas.
 */
public interface IMenu {
    void exibirMenu(CadastroAlunos cadastro);
}