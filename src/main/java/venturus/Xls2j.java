package venturus;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import venturus.exception.SheetNotFoundException;

public final class Xls2j {

    private static final String NO_ANNOTATION_MESSAGE = "%s will be ignored because doesn't have @Sheet annotation";
    private List<Object> sheets;

    public List<Object> parse(String filename) throws Exception {
        Loader loader = new ExcelLoader();

        try {
            loader.load(filename);

            List<Object> result = this.parseSheets(loader);

            return result;
        } finally {
            loader.close();
        }
    }

    private List<Object> parseSheets(final Loader loader) {
        List<Object> result = new ArrayList<>();

        for (final Object sheet : sheets) {
            final Class<?> sheetClass = sheet.getClass();

            if (!sheetClass.isAnnotationPresent(Sheet.class)) {
                Logger.error(String.format(NO_ANNOTATION_MESSAGE, sheetClass.getSimpleName()));
                continue;
            }

            Sheet sheetConfig = sheetClass.getAnnotation(Sheet.class);
            Logger.debug(String.format("%s -> Tipo %s", sheetConfig.value(), sheetClass.getSimpleName()));

            if (!loader.getSheetsNames().contains(sheetConfig.value())) {
                throw new SheetNotFoundException();
            }

            Object parsedSheet = parseSheet(sheetClass);
            result.add(parsedSheet);
        }

        return result;
    }

    private Object parseSheet(final Class<?> sheetClass) {
        for (final Field field : sheetClass.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);

                Logger.debug(String.format("\t%s -> %s", field.getName(), column.name()));
            }
        }

        try {
            return sheetClass.getConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            Logger.error(e.getMessage());
        }

        return null;
    }

    public Xls2j addSheet(Object sheet) {
        if (this.sheets == null) {
            this.sheets = new ArrayList<>();
        }

        sheets.add(sheet);
        return this;
    }

}
