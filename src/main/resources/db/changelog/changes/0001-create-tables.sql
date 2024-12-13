create table if not exists tb_contact_topics (
    contact_id uuid not null,
    topic_id uuid not null
);

create table if not exists tb_message_topics (
    message_id uuid not null,
    topic_id uuid not null
);

create table if not exists tb_contacts (
    id uuid not null,
    is_blocked boolean not null,
    created_at timestamp(6) not null,
    email varchar(150) not null,
    integration_id varchar(255),
    name varchar(150) not null,
    is_unsubscribed boolean not null,
    updated_at timestamp(6) not null,
    primary key (id)
);

create table if not exists tb_messages (
    id uuid not null,
    content TEXT not null,
    created_at timestamp(6) not null,
    email_from varchar(255) not null,
    recipients_to varchar(255) not null,
    recipients_cc varchar(255) not null,
    status varchar(255) not null check (status in ('DELIVER','OPEN','CLICK','BOUNCE','COMPLAINT','REJECT','SENT')),
    subject varchar(255) not null,
    updated_at timestamp(6) not null,
    template_id uuid,
    primary key (id)
);

create table if not exists tb_templates (
    id uuid not null,
    is_active boolean not null,
    content TEXT not null,
    created_at timestamp(6) not null,
    title varchar(255) not null,
    updated_at timestamp(6) not null,
    primary key (id)
);

create table if not exists tb_topics (
    id uuid not null,
    is_active boolean not null,
    created_at timestamp(6) not null,
    name varchar(150) not null,
    updated_at timestamp(6) not null,
    primary key (id),
    unique (name)
);

alter table if exists tb_contact_topics
   add constraint FKaio1hd42tilia3dq2495dhnbo
   foreign key (topic_id)
   references tb_topics;

alter table if exists tb_contact_topics
   add constraint FK7jrvgbrhoncdwvgjvabkkihaa
   foreign key (contact_id)
   references tb_contacts;

alter table if exists tb_message_topics
   add constraint FKtio1hd42tilia3dq2495dhuyt
   foreign key (topic_id)
   references tb_topics;

alter table if exists tb_message_topics
   add constraint FK2jrvgbrhoncdwvgjvabkfreaj
   foreign key (message_id)
   references tb_messages;

alter table if exists tb_messages
   add constraint FKn711dj0qo5ti3ls3jiemeadrs
   foreign key (template_id)
   references tb_templates;