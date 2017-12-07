--
-- PostgreSQL database dump
--

-- Dumped from database version 10.0
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

ALTER TABLE ONLY public.product_movements DROP CONSTRAINT fk_product_movement_user;
ALTER TABLE ONLY public.product_movements DROP CONSTRAINT fk_product_movement_product;
ALTER TABLE ONLY public.products DROP CONSTRAINT fk_product_creator;
ALTER TABLE ONLY public.order_outs DROP CONSTRAINT fk_order_out_order;
ALTER TABLE ONLY public.order_ins DROP CONSTRAINT fk_order_in_order;
ALTER TABLE ONLY public.orders DROP CONSTRAINT fk_order_handler;
ALTER TABLE ONLY public.orders DROP CONSTRAINT fk_order_creator;
ALTER TABLE ONLY public.line_items DROP CONSTRAINT fk_line_item_product;
ALTER TABLE ONLY public.line_items DROP CONSTRAINT fk_line_item_order;
ALTER TABLE ONLY public.line_item_allocations DROP CONSTRAINT fk_line_item_location_movement;
ALTER TABLE ONLY public.line_item_allocations DROP CONSTRAINT fk_line_item_location_line_item;
DROP INDEX public.ixfk_order_handler;
DROP INDEX public.ixfk_order_creator;
ALTER TABLE ONLY public.users DROP CONSTRAINT pk_user;
ALTER TABLE ONLY public.product_movements DROP CONSTRAINT pk_product_movement;
ALTER TABLE ONLY public.products DROP CONSTRAINT pk_product;
ALTER TABLE ONLY public.order_ins DROP CONSTRAINT pk_order_in;
ALTER TABLE ONLY public.order_outs DROP CONSTRAINT pk_order_id;
ALTER TABLE ONLY public.orders DROP CONSTRAINT pk_order;
ALTER TABLE ONLY public.line_items DROP CONSTRAINT pk_line_item;
DROP TABLE public.users;
DROP TABLE public.products;
DROP SEQUENCE public.product_movements_id_seq;
DROP TABLE public.product_movements;
DROP TABLE public.orders;
DROP TABLE public.order_outs;
DROP TABLE public.order_ins;
DROP SEQUENCE public.line_items_id_seq;
DROP TABLE public.line_items;
DROP TABLE public.line_item_allocations;
DROP EXTENSION plpgsql;
DROP SCHEMA public;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: line_item_allocations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE line_item_allocations (
    product_movement_id integer NOT NULL,
    line_item_id integer NOT NULL
);


--
-- Name: line_items; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE line_items (
    id integer DEFAULT nextval(('"line_items_id_seq"'::text)::regclass) NOT NULL,
    quantity integer NOT NULL,
    product_id integer NOT NULL,
    order_id integer NOT NULL
);


--
-- Name: line_items_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE line_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_ins; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE order_ins (
    id integer NOT NULL,
    supplier_name character varying(250) NOT NULL,
    expected_delivery date
);


--
-- Name: order_outs; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE order_outs (
    order_id integer NOT NULL
);


--
-- Name: orders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE orders (
    handled_at timestamp without time zone,
    created_at timestamp without time zone NOT NULL,
    id integer NOT NULL,
    state character varying(15) NOT NULL,
    creator_id integer NOT NULL,
    handler_id integer
);


--
-- Name: product_movements; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE product_movements (
    id integer DEFAULT nextval(('"product_movements_id_seq"'::text)::regclass) NOT NULL,
    at timestamp without time zone NOT NULL,
    quantity integer NOT NULL,
    missing boolean NOT NULL,
    product_id integer NOT NULL,
    location character varying(10) NOT NULL,
    user_id integer NOT NULL
);


--
-- Name: product_movements_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE product_movements_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE products (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    created_by integer NOT NULL
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE users (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    blocked_at timestamp without time zone,
    pin character varying(50) NOT NULL,
    is_admin boolean NOT NULL
);


--
-- Data for Name: line_item_allocations; Type: TABLE DATA; Schema: public; Owner: -
--

COPY line_item_allocations (product_movement_id, line_item_id) FROM stdin;
16	25
17	25
18	26
\.


--
-- Data for Name: line_items; Type: TABLE DATA; Schema: public; Owner: -
--

COPY line_items (id, quantity, product_id, order_id) FROM stdin;
23	5	1	1
24	100	2	1
25	3	1	2
26	2	3	2
\.


--
-- Data for Name: order_ins; Type: TABLE DATA; Schema: public; Owner: -
--

COPY order_ins (id, supplier_name, expected_delivery) FROM stdin;
1	CTU Faculty of Mass Destruction Technology Transfer Corporation Ltd.	\N
2	Black Mesa Research Institute	\N
\.


--
-- Data for Name: order_outs; Type: TABLE DATA; Schema: public; Owner: -
--

COPY order_outs (order_id) FROM stdin;
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: -
--

COPY orders (handled_at, created_at, id, state, creator_id, handler_id) FROM stdin;
\N	2017-12-04 22:06:31.413	1	OPEN	3	\N
2017-12-04 22:08:55.019	2017-12-04 22:07:16.179	2	CLOSED	3	3
\.


--
-- Data for Name: product_movements; Type: TABLE DATA; Schema: public; Owner: -
--

COPY product_movements (id, at, quantity, missing, product_id, location, user_id) FROM stdin;
16	2017-12-04 22:08:54.982	1	f	1	A2	3
17	2017-12-04 22:08:55.014	2	f	1	A1	3
18	2017-12-04 22:08:55.016	2	f	3	B1	3
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: -
--

COPY products (id, name, created_by) FROM stdin;
1	World-Destroyer v2	3
2	Nanobot Cloud	1
3	Suitcase Antimatter bomb	2
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY users (id, name, created_at, blocked_at, pin, is_admin) FROM stdin;
1	Kanye West	2017-12-02 13:10:59.908464	\N	1234	t
2	Blocked Test	2017-12-02 15:51:50.629862	2017-12-02 15:51:50.629862	8181	t
3	Robo	2017-12-02 16:07:17.339676	\N	1984	t
\.


--
-- Name: line_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('line_items_id_seq', 26, true);


--
-- Name: product_movements_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('product_movements_id_seq', 18, true);


--
-- Name: line_items pk_line_item; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY line_items
    ADD CONSTRAINT pk_line_item PRIMARY KEY (id);


--
-- Name: orders pk_order; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT pk_order PRIMARY KEY (id);


--
-- Name: order_outs pk_order_id; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_outs
    ADD CONSTRAINT pk_order_id PRIMARY KEY (order_id);


--
-- Name: order_ins pk_order_in; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_ins
    ADD CONSTRAINT pk_order_in PRIMARY KEY (id);


--
-- Name: products pk_product; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY products
    ADD CONSTRAINT pk_product PRIMARY KEY (id);


--
-- Name: product_movements pk_product_movement; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY product_movements
    ADD CONSTRAINT pk_product_movement PRIMARY KEY (id);


--
-- Name: users pk_user; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_user PRIMARY KEY (id);


--
-- Name: ixfk_order_creator; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX ixfk_order_creator ON orders USING btree (creator_id);


--
-- Name: ixfk_order_handler; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX ixfk_order_handler ON orders USING btree (handler_id);


--
-- Name: line_item_allocations fk_line_item_location_line_item; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY line_item_allocations
    ADD CONSTRAINT fk_line_item_location_line_item FOREIGN KEY (line_item_id) REFERENCES line_items(id);


--
-- Name: line_item_allocations fk_line_item_location_movement; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY line_item_allocations
    ADD CONSTRAINT fk_line_item_location_movement FOREIGN KEY (product_movement_id) REFERENCES product_movements(id);


--
-- Name: line_items fk_line_item_order; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY line_items
    ADD CONSTRAINT fk_line_item_order FOREIGN KEY (order_id) REFERENCES orders(id);


--
-- Name: line_items fk_line_item_product; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY line_items
    ADD CONSTRAINT fk_line_item_product FOREIGN KEY (product_id) REFERENCES products(id);


--
-- Name: orders fk_order_creator; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_order_creator FOREIGN KEY (creator_id) REFERENCES users(id);


--
-- Name: orders fk_order_handler; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_order_handler FOREIGN KEY (handler_id) REFERENCES users(id);


--
-- Name: order_ins fk_order_in_order; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_ins
    ADD CONSTRAINT fk_order_in_order FOREIGN KEY (id) REFERENCES orders(id);


--
-- Name: order_outs fk_order_out_order; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY order_outs
    ADD CONSTRAINT fk_order_out_order FOREIGN KEY (order_id) REFERENCES orders(id);


--
-- Name: products fk_product_creator; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_product_creator FOREIGN KEY (created_by) REFERENCES users(id);


--
-- Name: product_movements fk_product_movement_product; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY product_movements
    ADD CONSTRAINT fk_product_movement_product FOREIGN KEY (product_id) REFERENCES products(id);


--
-- Name: product_movements fk_product_movement_user; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY product_movements
    ADD CONSTRAINT fk_product_movement_user FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

