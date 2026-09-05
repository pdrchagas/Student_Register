package armazenamento;
import java.util.ArrayList;

/**
 * Implementação de armazenamento utilizando a classe ArrayList nativa do Java.
 */
public class ListaArray implements IArmazenador {
    private ArrayList<Object> lista;

    /**
     * Construtor da classe ListaArray.
     */
    public ListaArray(){
        setLista(new ArrayList<Object>());
    }

    private ArrayList<Object> getLista() { return lista; }
    public int getQtd() { return lista.size(); }
    private void setLista(ArrayList<Object> lista) { this.lista = lista; }

    @Override
    public void adicionar(Object obj){
        lista.add(obj);
    }

    @Override
    public Object remover(int i) {
        Object ret = null;
        if (buscar(i) != null){
            ret = lista.get(i);
            lista.remove(i);
        }
        return ret;
    }

    @Override
    public Object buscar (int i){
        Object ret = null;
        if(!lista.isEmpty()&& (i >= 0 && i < getQtd())){
            ret = lista.get(i);
        }
        return ret;
    }

    @Override
    public boolean estaVazia(){ return (lista.isEmpty()); }
}