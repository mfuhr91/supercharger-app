insert into mecanicos (id, especialidad, hora_entrada, hora_salida, nombre) values (1,'ELECTRICIDAD','09:00:00','17:00:00','Martin Palermo');
insert into mecanicos (id, especialidad, hora_entrada, hora_salida, nombre) values (2,'FRENOS','08:00:00','16:00:00','Roberto Carlos');

insert into seguros (id, nombre) values (1,'galeno');
insert into seguros (id, nombre) values (2,'mapfre');

insert into vehiculos (id, marca, modelo, nro_poliza, seguro_id) values (1,'chevrolet','corsa classic','4312431',1);
insert into vehiculos (id, marca, modelo, nro_poliza, seguro_id) values (2,'renault','clio mio','4535265',2);

insert into clientes (id, apellido, nombre, nro_doc, telefono, tipo_doc, vehiculo_id) values (1,'Di Maria','Fideo','35492311',2931034555,'DNI',2);

insert into turnos (id, asistencia, disponible, fecha, cliente_id, mecanico_id) values (1,'2022-07-09 10:00:00',false,'2022-07-09 10:00:00',1,2);
insert into turnos (id, asistencia, disponible, fecha, cliente_id, mecanico_id) values (2,null,true,'2022-07-10 09:00:00',null,1);
insert into turnos (id, asistencia, disponible, fecha, cliente_id, mecanico_id) values (3,null,true,'2022-07-11 11:00:00',null,1);

insert into agendas (id, cliente_id, mecanico_id, turno_id) values (1, 1, 2, 1);
insert into agendas (id, cliente_id, mecanico_id, turno_id) values (2, null, 1, 2);
insert into agendas (id, cliente_id, mecanico_id, turno_id) values (3, null, 1, 3);

insert into fichas_mecanicas (id, actividad, insumos, cliente_id, constancia_id, mecanico_id, turno_id) values (1,'Control de frenos','Pastillas x2',1,null,2,1);

insert into trabajos (id, especialidad, tiempo, fichaMecanica_id) values (1,'FRENOS','01:23',1);