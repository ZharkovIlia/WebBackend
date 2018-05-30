package zharkov.projects.utils;

import zharkov.projects.model.Tags;

public class StringToIntConverterByTags {
    public static Integer toInt(String name) {
        return Tags.getNameToDatabaseInt().get(name);
    }

    public static String toString(Integer databaseInt) {
        return Tags.getDatabaseIntToName().get(databaseInt);
    }
}
