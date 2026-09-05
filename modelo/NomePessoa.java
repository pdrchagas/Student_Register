package modelo;

/**
 * Classe que formata de forma inteligente o nome de uma pessoa,
 * incluindo formatação para padrão bibliográfico.
 */
public class NomePessoa {
    private Texto nome;

    /**
     * Construtor da classe NomePessoa.
     * @param nome Nome completo a ser armazenado.
     */
    public NomePessoa(String nome){
        setNome(nome);
    }

    /**
     * Retorna o nome completo formatado.
     * @return Nome completo.
     */
    public String getNome() {
        return this.nome.getTxt();
    }

    protected void setNome(String nome) {
        this.nome = new Texto(nome);
    }

    /**
     * Conta a quantidade de nomes e sobrenomes.
     * @return Quantidade de palavras.
     */
    public int getQtdePalavras(){
        return this.nome.getQtdePalavras();
    }
    
    /**
     * Retorna o nome com as letras invertidas.
     * @return Nome invertido.
     */
    public String getNomeInvertido(){
        return this.nome.inverterTexto();
    }

    /**
     * Gera o nome no formato bibliográfico (Ex: SILVA, J.).
     * @return Nome em formato de bibliografia.
     */
    public String getNomeBiblio(){
        String vts[] = this.nome.getTxt().split(" ");
        int qtd = vts.length;
        String sBib = vts[qtd-1].toUpperCase() + ", "; 
        
        for (int i=0; i < (qtd-1); i++){
            String pal = vts[i].toLowerCase(); 
            if(!verificaStr(pal)){ 
                sBib = sBib + vts[i].toUpperCase().charAt(0) + ". ";
            }
        }
        return sBib;
    }

    /**
     * Verifica preposições que não devem ser abreviadas no formato bibliográfico.
     * @param s Palavra a ser verificada.
     * @return true se for preposição, false caso contrário.
     */
    private boolean verificaStr(String s){
        final String sRet[]={"da", "de", "do", "di", "das", "dos", "e"};
        for (int i = 0; i < sRet.length; i++){
            if(sRet[i].equals(s)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return this.nome.toString();
    }
}