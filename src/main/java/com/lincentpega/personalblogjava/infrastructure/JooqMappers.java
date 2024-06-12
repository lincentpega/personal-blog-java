package com.lincentpega.personalblogjava.infrastructure;

import com.lincentpega.personalblogjava.domain.post.Post;
import com.lincentpega.personalblogjava.domain.post.PostContent;
import com.lincentpega.personalblogjava.domain.post.PostMetadata;
import com.lincentpega.personalblogjava.domain.post.Tag;
import lombok.experimental.UtilityClass;
import org.jooq.Record;

import java.util.List;

import static com.lincentpega.personalblogjava.jooq.Tables.POSTS;
import static com.lincentpega.personalblogjava.jooq.Tables.TAGS;

@UtilityClass
public class JooqMappers {

    public static Tag mapToTag(Record record) {
        return new Tag(record.getValue(TAGS.ID), record.getValue(TAGS.NAME));
    }

    public static Post mapToPost(Record record, List<Tag> tags) {
        return Post.builder()
                .id(record.getValue(POSTS.ID))
                .content(new PostContent(record.getValue(POSTS.BODY)))
                .metadata(new PostMetadata(
                        record.getValue(POSTS.TITLE),
                        record.getValue(POSTS.DESCRIPTION),
                        tags,
                        record.getValue(POSTS.CREATED_AT).toInstant(),
                        record.getValue(POSTS.UPDATED_AT).toInstant()
                ))
                .build();
    }
}
