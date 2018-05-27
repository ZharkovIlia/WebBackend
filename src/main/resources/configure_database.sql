CREATE TABLE public.users (
	user_id serial NOT NULL PRIMARY KEY,
	login text NOT NULL UNIQUE,
	email text NOT NULL UNIQUE
)
WITH (
	OIDS=false
);
ALTER TABLE public.users OWNER TO samizdat;

CREATE TABLE public.publications (
	publication_id serial NOT NULL PRIMARY KEY,
	description text NOT NULL,
	time_ text,
	creation_time timestamp(6) without time zone[] NOT NULL,
	num_likes integer NOT NULL,
	text_ text NOT NULL,
	type_ integer NOT NULL
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
	creation_time timestamp(6) without time zone[] NOT NULL,
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
