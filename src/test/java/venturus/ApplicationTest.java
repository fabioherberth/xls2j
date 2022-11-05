package venturus;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ApplicationTest {
    @Test
    public void shouldParseTest() throws Exception {
        Xls2j parser = new Xls2j();

        List<Object> sheets = parser
                .addSheet(new Planilha1()) // OK
                .addSheet(new Planilha2()) // OK
                .addSheet(new Planilha3()) // Dont parse because object doesn't have @Sheet annotation
                .parse("/tmp/arquivo.xlsx");

        assertEquals(2, sheets.size());
    }
}
