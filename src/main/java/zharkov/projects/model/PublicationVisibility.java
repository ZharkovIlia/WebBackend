package zharkov.projects.model;

import lombok.Getter;

public enum PublicationVisibility {
    DRAFT(0),
    PUBLISHED(1);

    private @Getter int databaseInteger;

    PublicationVisibility(int databaseInteger) {
        this.databaseInteger = databaseInteger;
    }
}
