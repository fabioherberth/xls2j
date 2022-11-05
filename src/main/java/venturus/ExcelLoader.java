package venturus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.xml.sax.SAXException;

import venturus.exception.FileException;

public class ExcelLoader implements Loader {

    private OPCPackage xlsxPackage;
    private XSSFReader xssfReader;
    private ReadOnlySharedStringsTable sharedStringsTable;

    @Override
    public void load(final File file) {
        try {
            this.xlsxPackage = OPCPackage.open(file, PackageAccess.READ);
            this.xssfReader = new XSSFReader(this.xlsxPackage);
            this.sharedStringsTable = new ReadOnlySharedStringsTable(xlsxPackage);

            this.getSheetNames().forEach(Logger::debug);
        } catch (IOException | OpenXML4JException | SAXException e) {
            throw new FileException();
        }
    }

    @Override
    public List<String> getSheetNames() throws FileException  {
        try {
            List<String> sheets = new ArrayList<>();

            XSSFReader.SheetIterator sheetIterator = (XSSFReader.SheetIterator) this.xssfReader.getSheetsData();

            sheetIterator.forEachRemaining(e -> sheets.add(sheetIterator.getSheetName()));

            return sheets;
        } catch (InvalidFormatException | IOException e) {
            throw new FileException();
        }
    }

    @Override
    public void close() throws IOException {
        // if (this.sharedStringsTable != null) {
        //     this.sharedStringsTable.close();
        // }

        this.xlsxPackage.close();
    }
}
