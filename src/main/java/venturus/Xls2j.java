package venturus;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Xls2j {

    private static final String NO_ANNOTATION_MESSAGE = "%s will be ignored because doesn't have @Sheet annotation";
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".xls", ".xlsx", ".xlsm");
    private List<Class<?>> sheets;

    public Xls2j() {
        this.sheets = new ArrayList<>();
    }

    public Object load(String filename) throws Exception {
        System.out.println("Loading " + filename + "...");
        File file = new File(filename);

        this.validateFile(filename);

        for (Class<?> sheet : sheets) {
            if (!sheet.isAnnotationPresent(Sheet.class)) {
                System.err.println(String.format(NO_ANNOTATION_MESSAGE, sheet.getSimpleName()));
                continue;
            }

            Sheet config = sheet.getAnnotation(Sheet.class);
            System.out.println(String.format("%s -> Tipo %s", config.value(), sheet.getSimpleName()));

            for(Field field : sheet.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);

                    System.out.println(String.format("\t%s -> %s", field.getName(), column.name()));
                }
            }
        }

        return null;
    }

    public Xls2j addSheet(Class<?> sheet) {
        sheets.add(sheet);
        return this;
    }

    private boolean validateFile(String filename) throws Exception {
        if (filename == null || filename.equals("")) {
            throw new Exception("Invalid file name");
        }

        // TODO! validate extension with ALLOWED_EXTENSIONS
        return true;
    }

}
