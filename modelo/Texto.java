package modelo;

/**
 * Classe responsável pelo tratamento e manipulação de cadeias de caracteres.
 */
public class Texto {
    private String txt;

    /**
     * Construtor da classe Texto.
     * @param txt Texto original a ser manipulado.
     */
    Texto(String txt){
        setTxt(txt);
        limpaEspacosExcedentes();
    }

    private void setTxt(String t){ this.txt = t; }
    
    /**
     * Retorna o texto armazenado.
     * @return O texto atual.
     */
    public String getTxt(){ return this.txt; }

    /**
     * Inverte a ordem dos caracteres da string.
     * @return O texto invertido.
     */
    public String inverterTexto(){
        String txtInvertido = "";
        if(!(this.txt == null || this.txt.equals("") )){
            for (int i=this.txt.length()-1; i >= 0; i--){
                txtInvertido = txtInvertido + this.txt.charAt(i);
            }
        } else {
            return null;
        }
        return txtInvertido;
    }

    /**
     * Conta a quantidade de palavras contidas no texto.
     * @return Número de palavras.
     */
    public int getQtdePalavras(){
        return (getTxt().split(" ").length);
    }

    /**
     * Remove espaços duplicados entre as palavras e nas bordas do texto.
     */
    private void limpaEspacosExcedentes(){
        setTxt(this.txt.trim());
        String s = "";
        for (int i=0; i < this.txt.length(); i++){
            s = s + this.txt.charAt(i); 
            if(this.txt.charAt(i) == ' '){
                while(i + 1 < this.txt.length() && this.txt.charAt(i+1) == ' '){ 
                    i++;
                }
            }
        }
        setTxt(s);
    }

    @Override
    public String toString(){
        return(getTxt());
    }
}