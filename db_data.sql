insert into mecanicos (id, especialidad, hora_entrada, hora_salida, nombre)
values  (1, 'ELECTRICIDAD', '09:00:00', '17:00:00', 'Martin Palermo'),
        (2, 'FRENOS', '08:00:00', '16:00:00', 'Roberto Carlos');

insert into seguros (id, nombre)
values  (1, 'galeno'),
        (2, 'mapfre');

insert into vehiculos (id, marca, modelo, nro_poliza, seguro_id)
values  (1, 'chevrolet', 'corsa classic', 4312431, 1),
        (2, 'renault', 'clio mio', 4535265, 2);

insert into clientes (id, apellido, nombre, nro_doc, telefono, tipo_doc, vehiculo_id)
values  (1, 'Di Maria', 'Fideo', '35492311', 29310345, 'DNI', 2);

insert into turnos (id, asistencia, disponible, fecha, cliente_id, mecanico_id)
values  (1, null, true, '2022-07-09 10:00:00', null, 2),
        (2, '2022-07-10 18:02:50.723662', false, '2022-07-10 09:00:00', 1, 1),
        (4, null, true, '2022-07-10 08:00:00', null, 2),
        (5, '2022-07-10 17:54:35.871781', false, '2022-07-10 10:00:00', 1, 2),
        (6, null, true, '2022-07-10 12:00:00', null, 2),
        (7, null, true, '2022-07-10 14:00:00', null, 2),
        (8, null, true, '2022-07-11 08:00:00', null, 2),
        (9, null, true, '2022-07-11 10:00:00', null, 2),
        (10, null, true, '2022-07-11 12:00:00', null, 2),
        (11, null, true, '2022-07-11 14:00:00', null, 2),
        (12, null, true, '2022-07-12 08:00:00', null, 2),
        (13, null, true, '2022-07-12 10:00:00', null, 2),
        (14, null, true, '2022-07-12 12:00:00', null, 2),
        (15, null, true, '2022-07-12 14:00:00', null, 2);

insert into agendas (id, cliente_id, mecanico_id, turno_id)
values  (1, null, 2, 1),
        (2, null, 1, 2),
        (4, null, 2, 4),
        (5, null, 2, 5),
        (6, null, 2, 6),
        (7, null, 2, 7),
        (8, null, 2, 8),
        (9, null, 2, 9),
        (10, null, 2, 10),
        (11, null, 2, 11),
        (12, null, 2, 12),
        (13, null, 2, 13),
        (14, null, 2, 14),
        (15, null, 2, 15);

insert into fichas_mecanicas (id, actividad, insumos, cliente_id, constancia_id, mecanico_id, turno_id)
values  (7, '', '', 1, 0, 2, 5),
        (8, '', '', 1, 0, 1, 2);