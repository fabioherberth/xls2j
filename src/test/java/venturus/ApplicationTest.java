package venturus;

// import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ApplicationTest {
    @Test
    public void shouldParseTest() throws Exception {
        Xls2j parse = new Xls2j();

        parse
            .addSheet(new Planilha1())
            .addSheet(new Planilha2())
            .addSheet(new Planilha3())
            .load("/tmp/arquivo.xlsx");
    }
}
