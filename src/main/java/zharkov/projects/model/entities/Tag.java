package zharkov.projects.model.entities;

import lombok.Getter;

public enum Tag {
    COMPETITIONS("Конкурсы", 0),
    QUEST("Квест", 1),
    RPG("Ролевка", 2),
    FOR_CHILDREN("Детям", 3),
    FOR_ADOLESCENTS("Подросткам", 4),
    FOR_ADULTS("Взрослым", 5);

    private @Getter final String name;
    private @Getter final int databaseId;

    Tag(String name, int databaseId) {
        this.name = name;
        this.databaseId = databaseId;
    }
}
