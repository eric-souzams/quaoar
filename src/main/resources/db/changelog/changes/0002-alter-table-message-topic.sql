ALTER TABLE tb_message_topic
    ADD CONSTRAINT fk_message
    FOREIGN KEY (message_id) REFERENCES tb_messages(id)
    ON DELETE CASCADE;
