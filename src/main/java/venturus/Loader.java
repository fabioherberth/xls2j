package venturus;

import java.util.List;

import venturus.exception.FileException;

public interface Loader {
    /**
     * Loads file metadata
     *
     * @param filename
     * @throws FileException
     */
    void load(final String filename) throws FileException;

    /**
     * Process the sheets across configurations
     *
     * @return
     */
    List<Object> process();

    /**
     * Closes some connections
     */
    void close();

    /**
     * Get the all sheet names
     *
     * @return
     */
    List<String> getSheetsNames();

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

