package venturus;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.xml.sax.SAXException;

import venturus.exception.FileException;

public interface Loader extends Closeable {
    void load(final File file) throws FileException;
    List<String> getSheetNames() throws FileException;

    // List<SheetParser.Line> getRowsFromSheet(String name);
    // List<String> getFileSheetNames();
    // List<String> getAllSheetNamesFromFile();
    // Map<FileConfig.TabConfig, List<Object>> getMapSheetData();
    // Map<Integer, Object> getMapLineBeanVO();
    // List<FileErrorDetail> getErrorFiles();
    // ExcelReaderHandler setRegexFilterSheet(String patternRegexFilterSheet);
    // ExcelReaderHandler setReaderConfig(ReaderConfig readerConfig);
    // void toParse(Map.Entry<FileConfig.TabConfig, FileConfig.TabDataConfig> entryTab);
    // void parseByParts(Map.Entry<FileConfig.TabConfig, FileConfig.TabDataConfig> entry, Map<Integer, String> mapCellHeader,
    //         List<SheetParser.Line> partOfTheData, boolean isFristPart);
    // Map<Integer, String> getSpreadsheetHeader(List<SheetParser.Line> lines, int lineNumHeader);
    // SheetParser.Line getHeaderLineBy(String sheetName);
    // List<SheetParser.Line> getSheetLinesBy(String sheetName);
    // void process() throws ParserConfigurationException, SAXException, IOException, OpenXML4JException;
    // void processTheSheet(String sheetName) throws IOException, InvalidFormatException, ParserConfigurationException, SAXException;
}

