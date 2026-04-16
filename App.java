import controle.CadastroAlunos;
import armazenamento.IArmazenador;
import armazenamento.ListaArray;
import armazenamento.Vetor;
import visao.IMenu;
import visao.MenuGrafico;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        
        Object[] opcoes = {"Vetor Dinâmico ", "ListaArray"};
        int escolha = JOptionPane.showOptionDialog(null, "Qual Estrutura de Dados deseja usar?", "Configuração Inicial",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == JOptionPane.CLOSED_OPTION) System.exit(0);

        IArmazenador armazenador;
        if (escolha == 1) {
            armazenador = new ListaArray();
        } else {
            armazenador = new Vetor();
        }
        
        CadastroAlunos ca = new CadastroAlunos(armazenador);
        IMenu menu = new MenuGrafico();
        menu.exibirMenu(ca);
    }
}