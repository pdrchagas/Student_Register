package controle;

import armazenamento.IArmazenador;
import modelo.Aluno;
import java.io.*;

/**
 * Classe controladora do sistema.
 * Gerencia as regras de negócio, os limites da sala e operações de persistência em arquivo.
 */
public class CadastroAlunos {
    private IArmazenador armazenador;
    private int capacidadeMax;

    /**
     * Construtor do Controlador.
     * @param armazenador   Estrutura de dados a ser utilizada.
     * @param capacidadeMax Limite máximo de alunos permitidos na sala.
     */
    public CadastroAlunos(IArmazenador armazenador, int capacidadeMax) {
        this.armazenador = armazenador;
        this.capacidadeMax = capacidadeMax;
    }

    /**
     * Verifica se a sala atingiu a capacidade máxima.
     * @return true se a sala atingiu a capacidade, false caso contrário.
     */
    public boolean isCheio() {
        return listar().length >= capacidadeMax;
    }

    /**
     * Método auxiliar para encontrar a posição do aluno na estrutura de dados pelo RA.
     * @param ra Registro Acadêmico.
     * @return O índice onde o aluno está armazenado, ou -1 se não encontrar.
     */
    private int acharIndicePorRa(String ra) {
        int i = 0;
        while (true) {
            Object obj = armazenador.buscar(i);
            if (obj == null) break;
            if (((Aluno) obj).getRa().equalsIgnoreCase(ra)) return i;
            i++;
        }
        return -1;
    }

    /**
     * Insere um novo aluno no sistema.
     * @param a Objeto Aluno a ser inserido.
     * @return true se inserido, false se a sala estiver cheia.
     */
    public boolean inserir(Aluno a) { 
        if (!isCheio()) {
            armazenador.adicionar(a); 
            return true;
        }
        return false;
    }

    /**
     * Remove um aluno baseado no RA.
     * @param ra Registro Acadêmico do aluno a ser removido.
     * @return true se removido, false se não encontrado.
     */
    public boolean remover(String ra) {
        int idx = acharIndicePorRa(ra);
        if (idx != -1) {
            return armazenador.remover(idx) != null;
        }
        return false;
    }

    /**
     * Busca um aluno pelo RA.
     * @param ra O RA do aluno.
     * @return O objeto Aluno encontrado, ou null se não achar.
     */
    public Aluno buscar(String ra) {
        int idx = acharIndicePorRa(ra);
        if (idx != -1) {
            return (Aluno) armazenador.buscar(idx);
        }
        return null;
    }

    /**
     * Retorna um array com todos alunos cadastrados.
     * @return Array de Alunos.
     */
    public Aluno[] listar() {
        int cont = 0;
        while(armazenador.buscar(cont) != null) cont++;
        
        Aluno[] lista = new Aluno[cont];
        for (int i = 0; i < cont; i++) {
            lista[i] = (Aluno) armazenador.buscar(i);
        }
        return lista;
    }

    /**
     * Salva registros da memória em um arquivo texto na pasta 'dados/'.
     * @param nome Nome do arquivo.
     * @throws IOException Erro de disco.
     */
    public void salvarEmArquivo(String nome) throws IOException {
        File diretorio = new File("dados");
        if (!diretorio.exists()) diretorio.mkdir();

        BufferedWriter writer = new BufferedWriter(new FileWriter("dados/" + nome));
        for (Aluno a : listar()) {
            writer.write(a.getRa() + ";" + a.getNome() + ";" + a.getIdade() + ";" + a.getCurso());
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Lê alunos a partir de um arquivo de texto na pasta 'dados/'.
     * @param nome Nome do arquivo.
     * @throws IOException Erro de disco ou inexistência.
     */
    public void lerDeArquivo(String nome) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("dados/" + nome));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] p = linha.split(";");
            if (p.length == 4 && buscar(p[0]) == null && !isCheio()) {
                inserir(new Aluno(p[1], Integer.parseInt(p[2]), p[0], p[3]));
            }
        }
        reader.close();
    }
}