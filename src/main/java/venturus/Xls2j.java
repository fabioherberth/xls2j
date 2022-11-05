package venturus;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import venturus.exception.FileException;

public final class Xls2j {

    private static final String NO_ANNOTATION_MESSAGE = "%s will be ignored because doesn't have @Sheet annotation";
    private List<Class<?>> sheets;

    public Xls2j() {
        this.sheets = new ArrayList<>();
    }

    public List<Object> load(String filename) throws Exception {
        this.loadFile(filename);

        for (Class<?> sheet : sheets) {
            if (!sheet.isAnnotationPresent(Sheet.class)) {
                Logger.error(String.format(NO_ANNOTATION_MESSAGE, sheet.getSimpleName()));
                continue;
            }

            Sheet config = sheet.getAnnotation(Sheet.class);
            Logger.debug(String.format("%s -> Tipo %s", config.value(), sheet.getSimpleName()));

            for(Field field : sheet.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);

                    Logger.debug(String.format("\t%s -> %s", field.getName(), column.name()));
                }
            }
        }

        return null;
    }

    public Xls2j addSheet(Class<?> sheet) {
        sheets.add(sheet);
        return this;
    }

    private FileInputStream loadFile(String filename) {
        Logger.debug("Loading " + filename + "...");

        this.validateFileType(filename);

        File file = new File(filename);

        Loader loader = new ExcelLoader();
        loader.load(file);

        return null;
    }

    private boolean validateFileType(String filename) throws FileException {
        if (filename == null || filename.equals("")) {
            throw new FileException();
        }

        // TODO! validate extension with AllowedTypes enum
        return true;
    }

}
