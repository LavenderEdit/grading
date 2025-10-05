/**
 * Author:  Studios TKOH!
 * Created: Oct 2, 2025
 */

-- Rol por Defecto
insert into roles (name)
select 'Owner'
where not exists (select 1 from roles where name = 'Owner');

insert into roles (name)
select 'Admin'
where not exists (select 1 from roles where name = 'Admin');

insert into roles (name)
select 'Evaluator'
where not exists (select 1 from roles where name = 'Evaluator');

insert into roles (name)
select 'Worker'
where not exists (select 1 from roles where name = 'Worker');

insert into roles (name)
select 'Student'
where not exists (select 1 from roles where name = 'Student');
