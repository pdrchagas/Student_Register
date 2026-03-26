package modelo;

/**
 * Classe base para representar uma pessoa.
 */
public abstract class Pessoa {
    protected NomePessoa nomePessoa;
    protected int idade;

    public Pessoa(String nome, int idade) {
        this.nomePessoa = new NomePessoa(nome);
        this.idade = idade;
    }

    public String getNome() {
        return nomePessoa.getValor();
    }

    public void setNome(String nome) {
        this.nomePessoa.setValor(nome);
    }

    public String getNomeBiblio() {
        return nomePessoa.getNomeBiblio();
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}