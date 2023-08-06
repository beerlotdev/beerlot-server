CREATE TABLE IF NOT EXISTS public.tag
(
    id bigint NOT NULL,
    description text COLLATE pg_catalog."default",
    name_en character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name_ko character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tag_pkey PRIMARY KEY (tag_id),
    CONSTRAINT uk_7ro3okj08r9p96c5a97o0ek0e UNIQUE (name_en),
    CONSTRAINT uk_imiogjl7fsi37i92146bh9fw7 UNIQUE (name_ko)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tag
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.category
(
    id bigint NOT NULL,
    parent_id bigint,
    CONSTRAINT category_pkey PRIMARY KEY (id),
    CONSTRAINT fk2y94svpmqttx80mshyny85wqr FOREIGN KEY (parent_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.category
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.category_international
(
    category_id bigint NOT NULL,
    language character varying[] COLLATE pg_catalog."default" NOT NULL,
    name character varying(15)[] COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    CONSTRAINT category_international_pkey PRIMARY KEY (category_id, language),
    CONSTRAINT category_international_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.category_international
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.beer
(
    id bigint NOT NULL DEFAULT nextval('beer_id_seq'::regclass),
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    image_url character varying(255) COLLATE pg_catalog."default" NOT NULL,
    like_count integer DEFAULT 0,
    rate double precision DEFAULT 0.0,
    review_count integer DEFAULT 0,
    volume real NOT NULL,
    category_id bigint,
    CONSTRAINT beer_pkey PRIMARY KEY (id),
    CONSTRAINT fkcgtf5842l7ptlc1xhdu9fpju0 FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.beer
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.beer_international
(
    beer_id bigint NOT NULL,
    language character varying[] COLLATE pg_catalog."default" NOT NULL,
    name character varying(20)[] COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    origin_city character varying(15)[] COLLATE pg_catalog."default",
    origin_country character varying(15)[] COLLATE pg_catalog."default",
    CONSTRAINT beer_international_pkey PRIMARY KEY (beer_id, language),
    CONSTRAINT beer_international_beer_id_fkey FOREIGN KEY (beer_id)
        REFERENCES public.beer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.beer_international
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.beer_tag
(
    beer_id bigint NOT NULL,
    tag_id bigint NOT NULL,
    CONSTRAINT beer_tag_pkey PRIMARY KEY (beer_id, tag_id),
    CONSTRAINT fk6k0m7fftnmwk4v7k9gd4g1men FOREIGN KEY (tag_id)
        REFERENCES public.tag (tag_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk793d97jc62vfrm0l91guaub8b FOREIGN KEY (beer_id)
        REFERENCES public.beer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.beer_tag
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.member
(
    id bigint NOT NULL DEFAULT nextval('member_id_seq'::regclass),
    oauth_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    image_url character varying(255) COLLATE pg_catalog."default",
    provider character varying(255) COLLATE pg_catalog."default" NOT NULL,
    roles character varying(255) COLLATE pg_catalog."default" NOT NULL,
    status_message character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT member_pkey PRIMARY KEY (id),
    CONSTRAINT uk_gc3jmn7c2abyo3wf6syln5t2i UNIQUE (username),
    CONSTRAINT uk_mbmcqelty0fbrvxp1q58dn57t UNIQUE (email)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.member
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.review
(
    id bigint NOT NULL DEFAULT nextval('review_id_seq'::regclass),
    content character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    create_at timestamp without time zone,
    image_url character varying(255) COLLATE pg_catalog."default",
    like_count integer DEFAULT 0,
    rate real NOT NULL,
    beer_id bigint,
    member_id bigint,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT review_pkey PRIMARY KEY (review_id),
    CONSTRAINT fkbwjo2uqvc7kcwiktemroulw61 FOREIGN KEY (beer_id)
        REFERENCES public.beer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkk0ccx5i4ci2wd70vegug074w1 FOREIGN KEY (member_id)
        REFERENCES public.member (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.review
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.beer_like
(
    beer_id bigint NOT NULL,
    member_id bigint NOT NULL,
    CONSTRAINT beer_like_pkey PRIMARY KEY (beer_id, member_id),
    CONSTRAINT fkhrvh9tro2cyed30wcv1pq6y3d FOREIGN KEY (member_id)
        REFERENCES public.member (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkk8iu9safejvkh4i8ck2b8c22v FOREIGN KEY (beer_id)
        REFERENCES public.beer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.beer_like
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.review_like
(
    member_id bigint NOT NULL,
    review_id bigint NOT NULL,
    CONSTRAINT review_like_pkey PRIMARY KEY (member_id, review_id),
    CONSTRAINT fk68am9vk1s1e8n1v873meqkk0k FOREIGN KEY (review_id)
        REFERENCES public.review (review_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkf19ep4u0vm5vietilw2kp9jo2 FOREIGN KEY (member_id)
        REFERENCES public.member (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.review_like
    OWNER to postgres;

INSERT INTO public.tag(
	id, description, name_en, name_ko)
	VALUES (1, '과일 껍질은 과일과 껍질 사이에 흰색 속을 포함하지 않는 감귤류 과일의 가장 바깥쪽 껍질입니다. 오렌지 껍질에는 또한 풍미가 포함되어 있어 풍미에서 얻는 것과 동일한 오렌지 풍미를 제공할 수 있습니다. 그러나 흰색 속도 포함합니다. 속은 쓴맛이 날 수 있습니다. 오렌지 제스트는 속으로 인한 쓴맛의 위험 없이 풍미를 제공합니다.', 'Orange Peel', '오렌지 필');

INSERT INTO public.category(
	id, description, name_en, name_ko, parent_id)
	VALUES (1, '이 고전적인 독일 밀 맥주 잔은 키가 크며 상단의 더 큰 구근 영역에서 하단 위의 좁은 그립 영역으로 곡선을 그리며 약간 더 넓은 하단을 향합니다. 이 모양은 더 큰 머리 모양을 지원하고 더 많은 빛이 밀 맥주의 흐린 부분을 통과하여 빛나는 효과를 만들 수 있습니다. 찬물은 빨리 식으니 드세요!', 'Weizen', '바이첸', null);

INSERT INTO public.beer(
	id, country, description, image_url, name_en, name_ko, volume, category_id)
	VALUES (1, 'US', '크림 같은 느낌을 위해 귀리로 양조하고 오렌지 껍질과 고수풀의 완벽한 조합으로 매운 맛을 냅니다. 여과되지 않은 밀 에일(Wheat Ale)은 벨기에 전통으로 향신료를 넣어 비정상적으로 부드러운 맛을 냅니다.', 'https://image_will_be_provided_later', 'Blue Moon Belgian Wheat', '블루문 벨지엄 위트', 5.4, 1);

INSERT INTO public.beer_tag(
	beer_id, tag_id)
	VALUES (1, 1);

INSERT INTO public.member(
	id, oauth_id, email, image_url, role_type, provider_type, username, status_message)
	VALUES (1, '123456789', test@email.com', 'https://image_url', 'GENERAL', 'KAKAO', '테스트유저', '1일 1맥중');

INSERT INTO public.review(
	id, content, image_url, rate, beer_id, member_id, created_at)
	VALUES (1, '이 맥주 정말 맛있어요! 향도 상큼하고 목넘김도 좋아요!', 'https://image_url', 5.0, 1, 1, now());
