package armazenamento;

/**
 * Interface que define o contrato (Tipo Abstrato de Dados) para as estruturas de armazenamento.
 */
public interface IArmazenador { 
    /**
     * Adiciona um objeto à estrutura.
     * @param a Objeto a ser adicionado.
     */
    public void adicionar(Object a);
    
    /**
     * Remove e retorna o objeto no índice especificado.
     * @param i Índice do objeto.
     * @return O objeto removido ou null se não encontrar.
     */
    public Object remover(int i);
    
    /**
     * Verifica se a estrutura está vazia.
     * @return true se vazia, false caso contrário.
     */
    public boolean estaVazia();
    
    /**
     * Retorna o objeto no índice especificado sem removê-lo.
     * @param i Índice do objeto.
     * @return O objeto ou null se não encontrar.
     */
    public Object buscar (int i);
}