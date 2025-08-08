-- drop relations --

drop table if exists films_actors;

drop table if exists films_genres;

drop table if exists films_directors;

-- drop entities --

drop table if exists genres;

drop table if exists films;

drop table if exists directors;

drop table if exists actors;

-- tables creation --

create table films
(
    id     serial primary key not null,
    name   varchar(255)       not null,
    year   integer            not null check ( year >= 1888 and year <= extract(year from current_date)::integer),
    rating float8             not null check ( rating >= 0 and rating <= 10),
    stock  integer            not null check ( stock >= 0 )
);

create table genres
(
    id   serial primary key not null,
    name varchar(64)        not null unique
);

create table directors
(
    id   serial primary key not null,
    name varchar(64)        not null
);

create table actors
(
    id   serial primary key not null,
    name varchar(64)        not null
);

-- relation creation--

create table films_genres
(
    film_id  serial references films (id),
    genre_id serial references genres (id)
);

create table films_directors
(
    film_id     serial references films (id),
    director_id serial references directors (id)
);

create table films_actors
(
    film_id  serial references films (id),
    actor_id serial references actors (id)
);

-- entities insertion --

insert into films(name, year, rating, stock)
values ('Семь', 1995, 8.3, 100_000),
       ('Апокалипсис сегодня', 1979, 8.1, 250_000),
       ('Скотт Пилигрим Против Всех', 2010, 7.3, 80_000),
       ('Побег из Шоушенка', 1994, 9.1, 500_000),
       ('Форрест Гамп', 1994, 8.9, 400_000),
       ('Назад в Будущее', 1985, 8.6, 70_000);

insert into genres(name)
values ('Триллер'),
       ('Боевик'),
       ('Комедия'),
       ('Драма'),
       ('Научная фантастика');

insert into directors(name)
values ('Дэвид Финчер'),
       ('Фрэнсис Форд Коппола'),
       ('Эдгар Райт'),
       ('Фрэнк Дарабонт'),
       ('Роберт Земекис');

insert into actors(name)
values ('Брэд Питт'),
       ('Морган Фримен'),

       ('Марлон Брандо'),
       ('Роберт Дюваль'),
       ('Мартин Шин'),

       ('Майкл Сера'),
       ('Мэри Элизабет Уинстэд'),
       ('Киран Калкин'),
       ('Крис Эванс'),

       ('Тим Роббинс'),

       ('Том Хэнкс'),
       ('Робин Райт'),

       ('Майкл Джей Фокс'),
       ('Кристофер Ллойд');

-- relation insertion--

insert into films_genres(film_id, genre_id)
values (1, 1), -- Семь
       (1, 4), -- Семь

       (2, 2), -- Апокалипсис Сегодня
       (2, 4), -- Апокалипсис Сегодня

       (3, 2), -- Скотт Пилигрим Против Всех
       (3, 3), -- Скотт Пилигрим Против Всех

       (4, 4), -- Побег из Шоушенка

       (5, 3), -- Форрест Гамп
       (5, 4), -- Форрест Гамп

       (6, 5), -- Назад в Будущее
       (6, 3); -- Назад в Будущее

insert into films_directors(film_id, director_id)
values (1, 1),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 5);

insert into films_actors(film_id, actor_id)
values (1, 1),  -- Семь
       (1, 2),  -- Семь

       (2, 3),  -- Апокалипсис Сегодня
       (2, 4),  -- Апокалипсис Сегодня
       (2, 5),  -- Апокалипсис Сегодня

       (3, 6),  -- Скотт Пилигрим Против Всех
       (3, 7),  -- Скотт Пилигрим Против Всех
       (3, 8),  -- Скотт Пилигрим Против Всех
       (3, 9),  -- Скотт Пилигрим Против Всех

       (4, 10), -- Побег из Шоушенка
       (4, 2),  -- Побег из Шоушенка

       (5, 11), -- Форрест Гамп
       (5, 12), -- Форрест Гамп

       (6, 13), -- Назад в Будущее
       (6, 14); -- Назад в Будущее