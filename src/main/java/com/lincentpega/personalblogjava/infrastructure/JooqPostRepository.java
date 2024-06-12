package com.lincentpega.personalblogjava.infrastructure;

import com.lincentpega.personalblogjava.domain.post.Post;
import com.lincentpega.personalblogjava.domain.post.Tag;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.lincentpega.personalblogjava.jooq.Tables.*;

@RequiredArgsConstructor
public class JooqPostRepository implements PostRepository {

    private final DSLContext ctx;
    private final TagRepository tagRepository;

    @Override
    @Transactional
    public void save(Post post) {
        ctx.insertInto(POSTS)
                .set(POSTS.ID, post.id())
                .set(POSTS.BODY, post.content().body())
                .set(POSTS.TITLE, post.metadata().title())
                .set(POSTS.DESCRIPTION, post.metadata().description())
                .set(POSTS.CREATED_AT, post.metadata().createdAt().atOffset(ZoneOffset.UTC))
                .set(POSTS.UPDATED_AT, post.metadata().updatedAt().atOffset(ZoneOffset.UTC))
                .execute();

        for (Tag tag : post.metadata().tags()) {
            ctx.insertInto(POSTS_TAGS)
                    .set(POSTS_TAGS.POST_ID, post.id())
                    .set(POSTS_TAGS.TAG_ID, tag.id())
                    .execute();
        }
    }

    @Override
    public Optional<Post> findById(Long id) {
        return ctx.select()
                .from(POSTS)
                .where(POSTS.ID.eq(id))
                .fetchOptional().map(r -> JooqMappers.mapToPost(r, getPostTags(id)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ctx.deleteFrom(POSTS_TAGS)
                .where(POSTS_TAGS.POST_ID.eq(id))
                .execute();
        ctx.deleteFrom(POSTS)
                .where(POSTS.ID.eq(id))
                .execute();
    }

    private List<Tag> getPostTags(Long postId) {
        var tagIds = ctx.select()
                .from(POSTS_TAGS)
                .where(POSTS_TAGS.POST_ID.eq(postId))
                .fetch()
                .map(record -> record.getValue(POSTS_TAGS.TAG_ID));
        return tagIds.stream()
                .map(id -> tagRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }
}
