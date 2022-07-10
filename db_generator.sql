create table if not exists constancias
(
    id          bigint auto_increment
        primary key,
    conformidad bit          not null,
    motivo      varchar(255) null
);

create table if not exists mecanicos
(
    id           bigint auto_increment
        primary key,
    especialidad varchar(255) null,
    hora_entrada time         null,
    hora_salida  time         null,
    nombre       varchar(255) null
);

create table if not exists seguros
(
    id     bigint auto_increment
        primary key,
    nombre varchar(255) null
);

create table if not exists trabajos
(
    id           bigint auto_increment
        primary key,
    especialidad varchar(255) null,
    tiempo       time         null
);

create table if not exists vehiculos
(
    id         bigint auto_increment
        primary key,
    marca      varchar(255) null,
    modelo     varchar(255) null,
    nro_poliza bigint       null,
    seguro_id  bigint       null,
    constraint fk_vehiculos_seguro_id
        foreign key (seguro_id) references seguros (id)
);

create table if not exists clientes
(
    id          bigint auto_increment
        primary key,
    apellido    varchar(255) null,
    nombre      varchar(255) null,
    nro_doc     varchar(255) null,
    telefono    int          null,
    tipo_doc    varchar(255) null,
    vehiculo_id bigint       null,
    constraint fk_clientes_vehiculo_id
        foreign key (vehiculo_id) references vehiculos (id)
);

create table if not exists turnos
(
    id          bigint auto_increment
        primary key,
    asistencia  datetime(6)      null,
    disponible  bit default b'1' not null,
    fecha       datetime(6)      null,
    cliente_id  bigint           null,
    mecanico_id bigint           null,
    constraint fk_turnos_cliente_id
        foreign key (cliente_id) references clientes (id),
    constraint fk_turnos_mecanico_id
        foreign key (mecanico_id) references mecanicos (id)
);

create table if not exists agendas
(
    id          bigint auto_increment
        primary key,
    cliente_id  bigint null,
    mecanico_id bigint null,
    turno_id    bigint null,
    constraint fk_agendas_cliente_id
        foreign key (cliente_id) references clientes (id),
    constraint fk_agendas_mecanico_id
        foreign key (mecanico_id) references mecanicos (id),
    constraint fk_agendas_turno_id
        foreign key (turno_id) references turnos (id)
);

create table if not exists fichas_mecanicas
(
    id            bigint auto_increment
        primary key,
    actividad     varchar(255) null,
    insumos       varchar(255) null,
    cliente_id    bigint       null,
    constancia_id bigint       null,
    mecanico_id   bigint       null,
    turno_id      bigint       null,
    constraint fk_fichas_mecanicas_cliente_id
        foreign key (cliente_id) references clientes (id),
    constraint fk_fichas_mecanicas_mecanicos_id
        foreign key (mecanico_id) references mecanicos (id),
    constraint fk_fichas_mecanicas_turno_id
        foreign key (turno_id) references turnos (id)
);

