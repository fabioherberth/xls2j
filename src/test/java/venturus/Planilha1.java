package venturus;

@Sheet("Planilha 1")
public class Planilha1 {

    @Column(name = "Nome")
    private String name;

    @Column(name = "Idade")
    private String idade;

    @Column(name = "Email")
    private String email;

    public Planilha1() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
