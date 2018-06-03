DROP TABLE IF EXISTS public.users, public.publications, public.tags_publications, public.comments_,
	public.publication_likes, public.comment_likes CASCADE;

CREATE TABLE public.users (
	user_id serial NOT NULL PRIMARY KEY,
	login text NOT NULL UNIQUE,
	email text NOT NULL UNIQUE,
	first_name text NOT NULL,
	last_name text NOT NULL,
	password_ text NOT NULL
)
WITH (
	OIDS=false
);
ALTER TABLE public.users OWNER TO samizdat;

CREATE TABLE public.publications (
	publication_id serial NOT NULL PRIMARY KEY,
	user_id integer NOT NULL REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
	name_ text NOT NULL,
	description text NOT NULL,
	time_ text,
	creation_time timestamp(6) without time zone NOT NULL,
	num_likes integer NOT NULL,
	text_ text NOT NULL,
	published boolean NOT NULL
)
WITH (
	OIDS=false
);
ALTER TABLE public.publications OWNER TO samizdat;

CREATE TABLE public.tags_publications (
	tag_id integer NOT NULL,
	publication_id integer NOT NULL REFERENCES publications (publication_id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT tags_publications_pkey PRIMARY KEY (tag_id, publication_id)
)
WITH (
	OIDS=false
);
ALTER TABLE public.tags_publications OWNER TO samizdat;

CREATE TABLE public.comments_ (
	comment_id serial NOT NULL PRIMARY KEY,
	user_id integer NOT NULL REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
	publication_id integer NOT NULL REFERENCES publications (publication_id) ON UPDATE CASCADE ON DELETE CASCADE,
	creation_time timestamp(6) without time zone NOT NULL,
	num_likes integer NOT NULL,
	text_ text NOT NULL
)
WITH (
	OIDS=false
);
ALTER TABLE public.comments_ OWNER TO samizdat;

CREATE TABLE public.publication_likes (
	user_id integer NOT NULL REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
	publication_id integer NOT NULL REFERENCES publications (publication_id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT publication_likes_pkey PRIMARY KEY (user_id, publication_id)
)
WITH (
	OIDS=false
);
ALTER TABLE public.publication_likes OWNER TO samizdat;

CREATE TABLE public.comment_likes (
	user_id integer NOT NULL REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
	comment_id integer NOT NULL REFERENCES comments_ (comment_id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT comment_likes_pkey PRIMARY KEY (user_id, comment_id)
)
WITH (
	OIDS=false
);
ALTER TABLE public.comment_likes OWNER TO samizdat;



INSERT INTO users (user_id, login, email, first_name, last_name, password_) VALUES
	(DEFAULT, 'Johnnie', 'jdoe@example.com', 'John', 'Doe', 'password');

INSERT INTO publications (publication_id, user_id, name_, description, time_, creation_time, num_likes, text_, published) VALUES
	(DEFAULT, 1, 'DRAFT', 'Announce', 'Time', CURRENT_TIMESTAMP(6) AT TIME ZONE 'UTC', 0, 'Text', FALSE),
	(DEFAULT, 1, 'PUBLISHED', 'Announce', 'Time', CURRENT_TIMESTAMP(6) AT TIME ZONE 'UTC', 0, 'Text', TRUE);

INSERT INTO tags_publications (tag_id, publication_id) VALUES
	(0, 1),
	(3, 1),
	(1, 2),
	(4, 2);
