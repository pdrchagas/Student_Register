package modelo;

public class Aluno {
    private NomePessoa nomePessoa;
    private int idade;
    private String ra;
    private String curso;

    public Aluno(String nome, int idade, String ra, String curso) {
        this.nomePessoa = new NomePessoa(nome);
        this.idade = idade;
        this.ra = ra;
        this.curso = curso;
    }

    public String getRa() { return ra; }
    public String getNome() { return nomePessoa.getNome(); }
    public String getNomeBiblio() { return nomePessoa.getNomeBiblio(); }
    public int getIdade() { return idade; }
    public String getCurso() { return curso; }
    
    // Métodos para atualizar os dados
    public void setNome(String nome) { this.nomePessoa.setNome(nome); }
    public void setIdade(int idade) { this.idade = idade; }
    public void setCurso(String curso) { this.curso = curso; }
    
    @Override
    public String toString() {
        return "RA: " + ra + " | Nome: " + getNome() + " | Curso: " + curso;
    }
}