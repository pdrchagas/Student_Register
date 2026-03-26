package visao;

import controle.CadastroAlunos;
import modelo.Aluno;
import javax.swing.JOptionPane;

/**
 * Implementação da interface gráfica (User Friendly e Proteção de Entradas).
 */
public class MenuGrafico implements IMenu {

    @Override
    public void exibirMenu(CadastroAlunos cadastro) {
        String textoMenu = "SISTEMA DE CADASTRO\n\n"
                         + "1 - Inserir novo aluno\n"
                         + "2 - Remover aluno\n"
                         + "3 - Listar alunos\n"
                         + "4 - Atualizar aluno\n"
                         + "5 - Sair\n\n"
                         + "Selecione a opção:";

        boolean executando = true;

        while (executando) {
            String opcaoStr = JOptionPane.showInputDialog(null, textoMenu, "Menu Principal", JOptionPane.QUESTION_MESSAGE);

            if (opcaoStr == null) break;

            switch (opcaoStr) {
                case "1": // RF01 - INSERIR
                    if (cadastro.isCheio()) {
                        exibirErro("Não é possível inserir. O cadastro atingiu a capacidade máxima!");
                        break;
                    }

                    String ra = lerApenasNumeros("Digite a matrícula (RA) do aluno:");
                    if (ra == null) break;
                    
                    if (cadastro.matriculaExiste(ra)) {
                        exibirErro("Matrícula já cadastrada! Não é permitido duplicatas.");
                        break;
                    }

                    String nome = lerApenasLetras("Digite o nome completo do aluno:", false);
                    if (nome == null) break;

                    int idade = lerInteiro("Digite a idade do aluno:");
                    if (idade == -1) break; 
                    
                    if (!cadastro.isIdadeValida(idade)) {
                        exibirErro("Idade inválida. A idade deve estar entre 16 e 80 anos.");
                        break;
                    }

                    String curso = lerApenasLetras("Digite o curso do aluno:", false);
                    if (curso == null) break;

                    Aluno novoAluno = new Aluno(nome, idade, ra, curso);
                    if (cadastro.inserir(novoAluno)) {
                        exibirSucesso("Aluno inserido com sucesso!");
                    }
                    break;

                case "2": // RF02 - REMOVER
                    String raRemover = lerApenasNumeros("Digite o RA do aluno que deseja remover:");
                    if (raRemover == null) break;

                    if (!cadastro.matriculaExiste(raRemover)) {
                        exibirErro("Operação cancelada: Matrícula inexistente.");
                        break;
                    }

                    if (cadastro.remover(raRemover)) {
                        exibirSucesso("Aluno removido com sucesso!");
                    }
                    break;

                case "3": // RF03 - LISTAR
                    Aluno[] lista = cadastro.listar();
                    if (lista.length == 0) {
                        exibirSucesso("Nenhum aluno cadastrado no momento.");
                        break;
                    }

                    String[] opcoesListagem = {"Formato Normal", "Formato Bibliografia"};
                    int escolha = JOptionPane.showOptionDialog(null, "Como deseja listar os alunos?", 
                            "Formato de Listagem", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
                            null, opcoesListagem, opcoesListagem[0]);
                    
                    if (escolha == JOptionPane.CLOSED_OPTION) break;

                    StringBuilder sb = new StringBuilder();
                    if (escolha == 1) {
                        sb.append("LISTA DE ALUNOS (Formato Bibliografia):\n\n");
                    } else {
                        sb.append("LISTA DE ALUNOS (Formato Padrão):\n\n");
                    }

                    for (Aluno a : lista) {
                        if (escolha == 1) { // Bibliografia
                            sb.append("RA: ").append(a.getRa())
                              .append(" | Aluno: ").append(a.getNomeBiblio()) // Puxa o método bibliográfico
                              .append(" | Idade: ").append(a.getIdade())
                              .append(" | Curso: ").append(a.getCurso()).append("\n");
                        } else { // Normal
                            sb.append(a.toString()).append("\n"); // Puxa o toString original
                        }
                    }
                    JOptionPane.showMessageDialog(null, sb.toString(), "Listagem", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case "4": // RF04 - ATUALIZAR
                    String raAtualizar = lerApenasNumeros("Digite o RA do aluno que deseja atualizar:");
                    if (raAtualizar == null) break;

                    Aluno alunoExistente = cadastro.buscar(raAtualizar);
                    if (alunoExistente == null) {
                        exibirErro("Operação cancelada: Matrícula inexistente.");
                        break;
                    }

                    // AQUI: Validação de letras com permiteVazio = true (para não obrigar a mudar o nome)
                    String novoNome = lerApenasLetras("Digite o novo nome (ou deixe vazio para manter '" + alunoExistente.getNome() + "'):", true);
                    if (novoNome == null) break; // Cancelou
                    if (!novoNome.isEmpty()) alunoExistente.setNome(novoNome);

                    int novaIdade = lerInteiro("Digite a nova idade (ou cancele para manter " + alunoExistente.getIdade() + "):");
                    if (novaIdade != -1) {
                        if (cadastro.isIdadeValida(novaIdade)) {
                            alunoExistente.setIdade(novaIdade);
                        } else {
                            exibirErro("Idade inválida. Operação abortada.");
                            break;
                        }
                    }

                    // AQUI: Validação de letras com permiteVazio = true
                    String novoCurso = lerApenasLetras("Digite o novo curso (ou deixe vazio para manter '" + alunoExistente.getCurso() + "'):", true);
                    if (novoCurso == null) break; // Cancelou
                    if (!novoCurso.isEmpty()) alunoExistente.setCurso(novoCurso);

                    cadastro.atualizar(alunoExistente);
                    exibirSucesso("Dados do aluno atualizados com sucesso!");
                    break;

                case "5":
                    executando = false;
                    break;

                default:
                    exibirErro("Opção inválida. Digite um número de 1 a 5.");
            }
        }
    }


    /**
     * Garante que o usuário digite APENAS letras e espaços.
     * @param permiteVazio se true, permite que o usuário avance sem digitar nada (útil para atualizar).
     */
    private String lerApenasLetras(String mensagem, boolean permiteVazio) {
        while (true) {
            String input = JOptionPane.showInputDialog(mensagem);
            
            if (input == null) return null; // Cancelou
            
            input = input.trim();
            
            if (input.isEmpty()) {
                if (permiteVazio) {
                    return ""; 
                } else {
                    exibirErro("Este campo não pode ficar vazio.");
                    continue; 
                }
            }
            
            // Regex para letras (com acentos) e espaços
            if (input.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
                return input;
            } else {
                exibirErro("Entrada inválida! Este campo deve conter APENAS letras. Números ou símbolos não são permitidos.");
            }
        }
    }

    /**
     * Garante que o usuário digite APENAS números.
     */
    private String lerApenasNumeros(String mensagem) {
        while (true) {
            String input = JOptionPane.showInputDialog(mensagem);
            
            // Se clicar em cancelar ou fechar a janela
            if (input == null || input.trim().isEmpty()) return null; 
            
            input = input.trim();
            
            // Verifica se a string contém APENAS dígitos de 0 a 9
            if (input.matches("^[0-9]+$")) {
                return input;
            } else {
                exibirErro("Entrada inválida! O RA deve conter APENAS números. Nenhuma letra ou símbolo é permitido.");
            }
        }
    }

    private String lerString(String mensagem) {
        String input = JOptionPane.showInputDialog(mensagem);
        if (input == null || input.trim().isEmpty()) return null;
        return input.trim();
    }

    private int lerInteiro(String mensagem) {
        String input = JOptionPane.showInputDialog(mensagem);
        if (input == null || input.trim().isEmpty()) return -1;
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            exibirErro("Entrada inválida! Você deve digitar um número inteiro válido.");
            return -1;
        }
    }

    private void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Atenção / Erro", JOptionPane.WARNING_MESSAGE);
    }

    private void exibirSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}