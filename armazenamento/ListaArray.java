package armazenamento;
import java.util.ArrayList;

public class ListaArray implements IArmazenador {
    private ArrayList<Object> lista;

    public ListaArray(){
        setLista(new ArrayList<Object>());
    }

    private ArrayList<Object> getLista() { return lista; }
    public int getQtd() { return lista.size(); }
    private void setLista(ArrayList<Object> lista) { this.lista = lista; }

    public void adicionar(Object obj){
        lista.add(obj);
    }

    public Object remover(int i) {
        Object ret = null;
        if (buscar(i) != null){
            ret = lista.get(i);
            lista.remove(i);
        }
        return ret;
    }

    public Object buscar (int i){
        Object ret = null;
        if(!lista.isEmpty()&& (i >= 0 && i < getQtd())){
            ret = lista.get(i);
        }
        return ret;
    }

    public boolean estaVazia(){ return (lista.isEmpty()); }
}