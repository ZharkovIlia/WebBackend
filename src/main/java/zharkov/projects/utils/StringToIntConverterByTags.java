package zharkov.projects.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class StringToIntConverterByTags {
    private @Getter static final Map<String, Integer> nameToDatabaseInt;
    private @Getter static final Map<Integer, String> databaseIntToName;

    static {
        nameToDatabaseInt = new HashMap<>();
        nameToDatabaseInt.put("Конкурсы", 0);
        nameToDatabaseInt.put("Квест", 1);
        nameToDatabaseInt.put("Ролевка", 2);
        nameToDatabaseInt.put("Детям", 3);
        nameToDatabaseInt.put("Подросткам", 4);
        nameToDatabaseInt.put("Взрослым", 5);

        databaseIntToName = new HashMap<>();
        for (Map.Entry<String, Integer> entry : nameToDatabaseInt.entrySet()) {
            databaseIntToName.put(entry.getValue(), entry.getKey());
        }
    }

    public static Integer toInt(String name) {
        return nameToDatabaseInt.get(name);
    }

    public static String toString(Integer databaseInt) {
        return databaseIntToName.get(databaseInt);
    }
}
