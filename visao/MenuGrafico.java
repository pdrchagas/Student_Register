package visao;

import controle.CadastroAlunos;
import modelo.Aluno;
import javax.swing.*;
import java.awt.*;

public class MenuGrafico implements IMenu {

    @Override
    public void exibirMenu(CadastroAlunos cadastro) {
        String textoMenu = "SISTEMA DE CADASTRO\n\n"
                         + "1 - Inserir novo aluno\n"
                         + "2 - Remover aluno\n"
                         + "3 - Listar alunos (Tabela)\n"
                         + "4 - Atualizar aluno\n"
                         + "5 - Salvar arquivo (na pasta dados)\n"
                         + "6 - Ler arquivo (da pasta dados)\n"
                         + "7 - Sair\n\n"
                         + "Escolha uma opção:";

        while (true) {
            String op = JOptionPane.showInputDialog(null, textoMenu, "Menu Principal", JOptionPane.QUESTION_MESSAGE);
            if (op == null || op.equals("7")) break;

            switch (op) {
                case "1": 
                    JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
                    JTextField ra = new JTextField();
                    JTextField nome = new JTextField();
                    JTextField idade = new JTextField();
                    JTextField curso = new JTextField();
                    
                    panel.add(new JLabel("RA (Apenas números):")); panel.add(ra);
                    panel.add(new JLabel("Nome:")); panel.add(nome);
                    panel.add(new JLabel("Idade:")); panel.add(idade);
                    panel.add(new JLabel("Curso:")); panel.add(curso);

                    boolean continuarCadastro = true;
                    while (continuarCadastro) {
                        int result = JOptionPane.showConfirmDialog(null, panel, "Novo Aluno", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                String raDigitado = ra.getText().trim();
                                if (!raDigitado.matches("\\d+")) {
                                    JOptionPane.showMessageDialog(null, "ERRO: RA deve ser apenas NÚMEROS!", "Erro", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }
                                if (cadastro.buscar(raDigitado) != null) {
                                    JOptionPane.showMessageDialog(null, "ERRO: RA já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }
                                Aluno novo = new Aluno(nome.getText().trim(), Integer.parseInt(idade.getText().trim()), raDigitado, curso.getText().trim());
                                cadastro.inserir(novo);
                                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
                                continuarCadastro = false;
                            } catch (Exception e) { 
                                JOptionPane.showMessageDialog(null, "ERRO: Verifique a idade!", "Erro", JOptionPane.ERROR_MESSAGE); 
                            }
                        } else { continuarCadastro = false; }
                    }
                    break;

                case "2":
                    String raRem = JOptionPane.showInputDialog("RA para remover:");
                    if (raRem != null && cadastro.remover(raRem)) JOptionPane.showMessageDialog(null, "Removido!");
                    else if (raRem != null) JOptionPane.showMessageDialog(null, "Não encontrado.");
                    break;

                case "3": 
                    Aluno[] lista = cadastro.listar();
                    String[] colunas = {"RA", "Nome (Biblio)", "Idade", "Curso"};
                    String[][] dados = new String[lista.length][4];
                    for (int i = 0; i < lista.length; i++) {
                        dados[i][0] = lista[i].getRa();
                        dados[i][1] = lista[i].getNomeBiblio();
                        dados[i][2] = "" + lista[i].getIdade();
                        dados[i][3] = lista[i].getCurso();
                    }
                    JTable tabela = new JTable(dados, colunas);
                    JOptionPane.showMessageDialog(null, new JScrollPane(tabela), "Lista de Alunos", JOptionPane.PLAIN_MESSAGE);
                    break;

                case "4": 
                    String raAlt = JOptionPane.showInputDialog("RA para atualizar:");
                    if (raAlt == null) break;
                    Aluno al = cadastro.buscar(raAlt);
                    if (al != null) {
                        JPanel updatePanel = new JPanel(new GridLayout(3, 2, 5, 5));
                        JTextField txtNome = new JTextField(al.getNome());
                        JTextField txtIdade = new JTextField(String.valueOf(al.getIdade()));
                        JTextField txtCurso = new JTextField(al.getCurso());
                        updatePanel.add(new JLabel("Nome:")); updatePanel.add(txtNome);
                        updatePanel.add(new JLabel("Idade:")); updatePanel.add(txtIdade);
                        updatePanel.add(new JLabel("Curso:")); updatePanel.add(txtCurso);
                        if (JOptionPane.showConfirmDialog(null, updatePanel, "Atualizar", 2) == 0) {
                            try {
                                al.setNome(txtNome.getText().trim());
                                al.setIdade(Integer.parseInt(txtIdade.getText().trim()));
                                al.setCurso(txtCurso.getText().trim());
                                JOptionPane.showMessageDialog(null, "Atualizado!");
                            } catch (Exception e) { JOptionPane.showMessageDialog(null, "Erro!"); }
                        }
                    }
                    break;

                case "5": 
                    String arqSalvar = JOptionPane.showInputDialog("Nome do arquivo (será salvo na pasta 'dados'):");
                    if (arqSalvar != null && !arqSalvar.isEmpty()) {
                        try { cadastro.salvarEmArquivo(arqSalvar); JOptionPane.showMessageDialog(null, "Salvo em dados/" + arqSalvar); }
                        catch (Exception e) { JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage()); }
                    }
                    break;

                case "6": 
                    String arqLer = JOptionPane.showInputDialog("Nome do arquivo dentro da pasta 'dados':");
                    if (arqLer != null && !arqLer.isEmpty()) {
                        try { cadastro.lerDeArquivo(arqLer); JOptionPane.showMessageDialog(null, "Lido com sucesso!"); }
                        catch (Exception e) { JOptionPane.showMessageDialog(null, "Erro: Arquivo não encontrado na pasta 'dados'."); }
                    }
                    break;
            }
        }
    }
}