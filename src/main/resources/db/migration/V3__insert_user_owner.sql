/**
 * Author:  Studios TKOH!
 * Created: Oct 2, 2025
 */

insert into `users` (name, email)
select 'Joan', 'joan_owner@gmail.com'
where not exists (select 1 from users where name = 'Joan');

insert into `user_rol` (user_id, role_id)
select 1, 1
where not exists (select 1 from user_rol where user_id = 1);