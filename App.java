import controle.CadastroAlunos;
import armazenamento.IArmazenador;
import armazenamento.ListaArray;
import armazenamento.Vetor;
import visao.IMenu;
import visao.MenuGrafico;
import javax.swing.JOptionPane;

/**
 * Classe principal que inicializa o Sistema de Cadastro de Alunos.
 * Responsável por capturar as configurações iniciais (limite e estrutura de dados)
 * e injetar as dependências no controlador.
 * * @author [Seu Nome]
 */
public class App {
    
    /**
     * Ponto de entrada da aplicação.
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        
        int limiteMaximo = 0;
        boolean limiteValido = false;

        // Loop para garantir que o usuário digite uma capacidade válida
        while (!limiteValido) {
            String inputCapacidade = JOptionPane.showInputDialog(null, "Defina a capacidade máxima da sala (maior que 0):", "Configuração Inicial", JOptionPane.QUESTION_MESSAGE);
            if (inputCapacidade == null || inputCapacidade.trim().isEmpty()) System.exit(0); 

            try {
                limiteMaximo = Integer.parseInt(inputCapacidade.trim());
                if (limiteMaximo <= 0) {
                    JOptionPane.showMessageDialog(null, "ERRO: A capacidade deve ser um número maior que ZERO!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                } else {
                    limiteValido = true; 
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ERRO: Digite apenas números inteiros!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        }

        Object[] opcoes = {"Vetor Dinâmico (Professor)", "ListaArray (ArrayList)"};
        int escolha = JOptionPane.showOptionDialog(null, "Qual Estrutura de Dados deseja usar?", "Configuração Inicial",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == JOptionPane.CLOSED_OPTION) System.exit(0);

        IArmazenador armazenador;
        if (escolha == 1) {
            armazenador = new ListaArray();
        } else {
            armazenador = new Vetor();
        }
        
        CadastroAlunos ca = new CadastroAlunos(armazenador, limiteMaximo);
        IMenu menu = new MenuGrafico();
        menu.exibirMenu(ca);
    }
}