DELETE
FROM posts_tags;
DELETE
FROM posts;
DELETE
FROM tags;

INSERT INTO tags (id, name)
VALUES (1, 'funny');
INSERT INTO tags (id, name)
VALUES (2, 'serious');

INSERT INTO posts (id, title, description, created_at, updated_at, body)
VALUES (1, 'My First Post', 'This is my first post', '2020-01-01 00:00:00', '2020-01-01 00:00:00',
        'My first post content.');
INSERT INTO posts (id, title, description, created_at, updated_at, body)
VALUES (2, 'My Second Post', 'This is my second post', '2020-01-02 00:00:00', '2020-01-02 00:00:00',
        'My second post content.');

INSERT INTO posts_tags (post_id, tag_id)
VALUES (1, 1);
INSERT INTO posts_tags (post_id, tag_id)
VALUES (1, 2);
INSERT INTO posts_tags (post_id, tag_id)
VALUES (2, 2);
