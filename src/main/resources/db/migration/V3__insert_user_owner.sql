/**
 * Author:  Studios TKOH!
 * Created: Oct 2, 2025
 */

INSERT INTO users (name, email, password, current_score, enabled, locked, password_changed_at, created_by, created_at, modified_by, modified_at)
SELECT 'Joan', 'joan_owner@gmail.com', '$2a$12$YUj5ZCyMuO3GWD3uptZpIunTb8As1Yqt2xR3UNu1E6Ur.gyOtfbIW', 0, 1, 0,
       UTC_TIMESTAMP(), 'system', UTC_TIMESTAMP(), 'system', UTC_TIMESTAMP()
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'joan_owner@gmail.com');

INSERT INTO user_rol (user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.name = 'Owner'
WHERE u.email = 'joan_owner@gmail.com'
  AND NOT EXISTS (
      SELECT 1
      FROM user_rol ur
      WHERE ur.user_id = u.id
        AND ur.role_id = r.id);