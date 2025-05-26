ALTER TABLE tb_message_topic
DROP CONSTRAINT tb_message_topic_message_id_fkey;

ALTER TABLE tb_message_topic
ADD CONSTRAINT tb_message_topic_message_id_fkey
FOREIGN KEY (message_id)
REFERENCES tb_messages(id)
ON DELETE CASCADE;
