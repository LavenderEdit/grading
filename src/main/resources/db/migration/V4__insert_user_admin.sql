/**
 * Author:  Studios TKOH!
 * Created: Oct 2, 2025
 */

insert into `users` (name, email)
select 'Vidal', 'vidal_admin@gmail.com'
where not exists (select 1 from users where name = 'Vidal');

insert into `user_rol` (user_id, role_id)
select 2, 2
where not exists (select 1 from user_rol where user_id = 2);