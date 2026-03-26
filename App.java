import controle.CadastroAlunos;
import visao.IMenu;
import visao.MenuGrafico;
import javax.swing.JOptionPane;

/**
 * Classe principal que inicia o sistema.
 */
public class App {
    public static void main(String[] args) {
        String inputQtde = JOptionPane.showInputDialog(null, "Para iniciar, defina a capacidade máxima de alunos:", "Configuração Inicial", JOptionPane.QUESTION_MESSAGE);
        
        if (inputQtde == null || inputQtde.trim().isEmpty()) {
            System.exit(0);
        }

        try {
            int qtde = Integer.parseInt(inputQtde.trim());
            if (qtde <= 0) {
                JOptionPane.showMessageDialog(null, "A capacidade deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            
            CadastroAlunos ca = new CadastroAlunos(qtde);
            
            // Injeção de dependência via Interface
            IMenu menu = new MenuGrafico();
            menu.exibirMenu(ca);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: A capacidade deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}