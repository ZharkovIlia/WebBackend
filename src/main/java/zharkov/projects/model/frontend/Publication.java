package zharkov.projects.model.frontend;

import lombok.Getter;
import lombok.Setter;
import zharkov.projects.model.entities.PublicationEntity;

import java.util.ArrayList;
import java.util.List;

public class Publication {
    private @Getter @Setter Integer id;
    private @Getter @Setter String name;
    private @Getter @Setter List<String> tags;
    private @Getter @Setter String time;
    private @Getter @Setter String announce;
    private @Getter @Setter String author;
    private @Getter @Setter String img = null;
    private @Getter @Setter Integer currentRate;
    private @Getter @Setter boolean published;

    public Publication(PublicationEntity entity) {
        id = entity.getPublicationId();
        name = entity.getName();
        time = entity.getTime();
        announce = entity.getDescription();
        author = entity.getUserByUserId().getFullName();
        currentRate = entity.getNumLikes();
        tags = new ArrayList<>();
        published = entity.getPublished();
    }
}
