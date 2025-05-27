CREATE TABLE if not exists tb_message_topic (
    message_id uuid NOT NULL,
    topic_id uuid NOT NULL,
    PRIMARY KEY (message_id, topic_id),
    FOREIGN KEY (message_id) REFERENCES tb_messages(id),
    FOREIGN KEY (topic_id) REFERENCES tb_topics(id)
);

ALTER TABLE tb_message_topic
DROP CONSTRAINT IF EXISTS tb_message_topic_message_id_fkey;

ALTER TABLE tb_message_topic
ADD CONSTRAINT tb_message_topic_message_id_fkey
FOREIGN KEY (message_id)
REFERENCES tb_messages(id)
ON DELETE CASCADE;