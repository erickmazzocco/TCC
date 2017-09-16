package ifes.domain;

public class Professor {

	private int cdProfessor;

	private String nome;       
    
    public int getCdProfessor() {
        return cdProfessor;
    }

    /**
     * @param cdProfessor the cdProfessor to set
     */
    public void setCdProfessor(int cdProfessor) {
        this.cdProfessor = cdProfessor;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public String toString(){
        return this.getNome();
    }
}
