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
    private ReadOnlySharedStringsTable sharedStrings;
    private List<String> sheetsNames;

    @Override
    public void load(final String filename) throws FileException {
        Logger.debug("Loading " + filename + "...");

        this.validateFileType(filename);

        try {
            this.xlsxPackage = OPCPackage.open(new File(filename), PackageAccess.READ);
            this.xssfReader = new XSSFReader(this.xlsxPackage);
            this.sharedStrings = new ReadOnlySharedStringsTable(xlsxPackage);
            this.sheetsNames = this.loadSheetsNames();
        } catch (IOException | OpenXML4JException | SAXException e) {
            throw new FileException();
        }
    }

    @Override
    public List<Object> process() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close() {
        try {
            this.xlsxPackage.close();
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public List<String> getSheetsNames() {
        return this.sheetsNames;
    }

    private boolean validateFileType(String filename) throws FileException {
        if (filename == null || filename.equals("")) {
            throw new FileException();
        }

        // TODO! validate extension with AllowedTypes enum
        return true;
    }

    private List<String> loadSheetsNames() throws FileException {
        try {
            List<String> result = new ArrayList<>();

            XSSFReader.SheetIterator sheetIterator = (XSSFReader.SheetIterator) this.xssfReader.getSheetsData();

            sheetIterator.forEachRemaining(e -> result.add(sheetIterator.getSheetName()));

            return result;
        } catch (InvalidFormatException | IOException e) {
            throw new FileException();
        }
    }
}
