package armazenamento;

/**
 * Implementação de armazenamento utilizando um array estático que redimensiona dinamicamente.
 */
public class Vetor implements IArmazenador {
    private Object array[]; 
    private int qtd;

    /**
     * Construtor da classe Vetor. Inicializa o array vazio.
     */
    public Vetor(){
        setArray(null);
        setQtd(0);
    }

    private Object[] getArray() { return array; }
    public int getQtd() { return qtd; }
    private void setArray(Object[] array) { this.array = array; }
    private void setQtd(int qtd) { this.qtd = qtd; }

    @Override
    public void adicionar(Object obj){
        if (array == null){         
            setArray(new Object[1]);
            array[0] = obj; 
            setQtd(getQtd()+1);
        } else {
            Object aux[] = new Object[array.length+1];
            copiar(array, aux);
            aux[aux.length-1] = obj;
            setArray(aux);
            setQtd(getQtd()+1);
        }
    }

    @Override
    public Object remover(int i) {
        Object ret = null;
        if(buscar(i) != null){
            ret = array[i];
            array[i] = null;

            if(getQtd() > 1){
                Object aux[] = new Object[array.length-1];
                copiar(array, aux);
                setArray(aux); 
                setQtd(getQtd() - 1);
            } else {
                setArray(null); 
                setQtd(0);
            }
        }
        return ret;
    }

    @Override
    public Object buscar (int i){
        Object ret = null;
        if(array != null && (i >= 0 && i < getQtd())) {
            ret = array[i];
        }
        return ret;
    }

    @Override
    public boolean estaVazia(){ return (getQtd()==0 && getArray() == null); }

    /**
     * Método auxiliar para copiar dados entre arrays durante o redimensionamento.
     * @param origem Array de origem.
     * @param destino Array de destino.
     */
    private void copiar(Object origem[], Object destino[]){
        int i, k = 0;
        for (i = 0; i < origem.length; i++){
            if (origem[i] != null) {
                destino[k] = origem[i];
                k++;
            }
        }       
    }
}