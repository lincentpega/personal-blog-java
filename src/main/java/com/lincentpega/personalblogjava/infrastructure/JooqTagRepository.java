package com.lincentpega.personalblogjava.infrastructure;

import com.lincentpega.personalblogjava.domain.post.Tag;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.Optional;

import static com.lincentpega.personalblogjava.jooq.Tables.TAGS;

@RequiredArgsConstructor
public class JooqTagRepository implements TagRepository {

    private final DSLContext ctx;

    @Override
    public void save(Tag tag) {
        ctx.insertInto(TAGS)
                .set(TAGS.ID, tag.id())
                .set(TAGS.NAME, tag.name())
                .execute();
    }

    @Override
    public Optional<Tag> findById(Long id) {
        var record = ctx.select()
                .from(TAGS)
                .where(TAGS.ID.eq(id))
                .fetchOne();
        if (record == null) return Optional.empty();
        return Optional.of(JooqMappers.mapToTag(record));
    }

    @Override
    public boolean existsById(Long id) {
        return ctx.fetchExists(ctx.selectFrom(TAGS).where(TAGS.ID.eq(id)));
    }

    @Override
    public void deleteById(Long id) {
        ctx.deleteFrom(TAGS)
                .where(TAGS.ID.eq(id))
                .execute();
    }
}
