package visao;
import controle.CadastroAlunos;

/**
 * Interface que define o contrato para a camada de visualização com o usuário.
 */
public interface IMenu {
    /**
     * Inicia a exibição do menu interativo. 
     * @param cadastro Controlador da aplicação contendo regras de negócio.
     */
    void exibirMenu(CadastroAlunos cadastro);
}