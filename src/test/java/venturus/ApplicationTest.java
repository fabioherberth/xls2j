package venturus;

// import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ApplicationTest {
    @Test
    public void shouldParseTest() throws Exception {
        Xls2j parse = new Xls2j();

        parse
            .addSheet(Planilha1.class)
            .addSheet(Planilha2.class)
            .addSheet(Planilha3.class)
            .load("/tmp/file1.xlsx");
    }
}
