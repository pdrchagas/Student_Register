package modelo;

/**
 * Entidade que representa um Aluno no sistema.
 * Armazena os dados pessoais e acadêmicos do estudante.
 */
public class Aluno {
    private NomePessoa nomePessoa;
    private int idade;
    private String ra;
    private String curso;

    /**
     * Construtor da classe Aluno.
     * @param nome  Nome completo do aluno.
     * @param idade Idade do aluno.
     * @param ra    Registro Acadêmico (identificador único).
     * @param curso Curso no qual o aluno está matriculado.
     */
    public Aluno(String nome, int idade, String ra, String curso) {
        this.nomePessoa = new NomePessoa(nome);
        this.idade = idade;
        this.ra = ra;
        this.curso = curso;
    }

    /** @return RA do aluno. */
    public String getRa() { return ra; }
    
    /** @return Nome completo normal. */
    public String getNome() { return nomePessoa.getNome(); }
    
    /** @return Nome em formato bibliográfico. */
    public String getNomeBiblio() { return nomePessoa.getNomeBiblio(); }
    
    /** @return Idade do aluno. */
    public int getIdade() { return idade; }
    
    /** @return Curso do aluno. */
    public String getCurso() { return curso; }
    
    /** @param nome Novo nome para o aluno. */
    public void setNome(String nome) { this.nomePessoa.setNome(nome); }
    
    /** @param idade Nova idade para o aluno. */
    public void setIdade(int idade) { this.idade = idade; }
    
    /** @param curso Novo curso para o aluno. */
    public void setCurso(String curso) { this.curso = curso; }
    
    @Override
    public String toString() {
        return "RA: " + ra + " | Nome: " + getNome() + " | Curso: " + curso;
    }
}