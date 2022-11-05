package venturus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import venturus.exception.SheetNotFoundException;

public final class Xls2j {

    private static final String NO_ANNOTATION_MESSAGE = "%s will be ignored because doesn't have @Sheet annotation";
    private List<Object> sheets;

    public Xls2j() {
        this.sheets = new ArrayList<>();
    }

    public List<Object> load(String filename) throws Exception {
        Loader loader = new ExcelLoader();

        try {
            loader.load(filename);

            List<Object> result = this.parse(loader);

            return result;
        } finally {
            loader.close();
        }
    }

    private List<Object> parse(final Loader loader) {
        List<Object> result = new ArrayList<>();

        for (final Object sheet : sheets) {
            final Class<?> cls = sheet.getClass();

            if (!cls.isAnnotationPresent(Sheet.class)) {
                Logger.error(String.format(NO_ANNOTATION_MESSAGE, cls.getSimpleName()));
                continue;
            }

            Sheet config = cls.getAnnotation(Sheet.class);
            Logger.debug(String.format("%s -> Tipo %s", config.value(), cls.getSimpleName()));

            if (!loader.getSheetsNames().contains(config.value())) {
                throw new SheetNotFoundException();
            }

            for (final Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);

                    Logger.debug(String.format("\t%s -> %s", field.getName(), column.name()));
                }
            }
        }

        return result;
    }

    public Xls2j addSheet(Object sheet) {
        sheets.add(sheet);
        return this;
    }

}
