package armazenamento;

public interface IArmazenador { 
    public void adicionar(Object a);
    public Object remover(int i);
    public boolean estaVazia();
    public Object buscar (int i);
}