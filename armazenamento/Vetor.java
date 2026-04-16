package armazenamento;

public class Vetor implements IArmazenador {
    private Object array[]; 
    private int qtd;

    public Vetor(){
        setArray(null);
        setQtd(0);
    }

    private Object[] getArray() { return array; }
    public int getQtd() { return qtd; }
    private void setArray(Object[] array) { this.array = array; }
    private void setQtd(int qtd) { this.qtd = qtd; }

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

    public Object buscar (int i){
        Object ret = null;
        if(array != null && (i >= 0 && i < getQtd())) {
            ret = array[i];
        }
        return ret;
    }

    public boolean estaVazia(){ return (getQtd()==0 && getArray() == null); }

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