package venturus;

@Sheet("Planilha 2")
public class Planilha2 {

    @Column(name = "Cor")
    private String cor;

    @Column(name = "hex")
    private String hex;

    public Planilha2() {
        super();
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

}
