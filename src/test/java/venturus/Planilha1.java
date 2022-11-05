package venturus;

import java.util.List;

@Sheet("Planilha 1")
public class Planilha1 {

    @Column(name = "Nome")
    private String name;

    @Column(name = "Idade")
    private String idade;

    @Column(name = "Emails")
    @Split(";")
    private List<String> emails;

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

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
