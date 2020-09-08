INSERT INTO cms_user (username, password, create_time, update_time)
SELECT * FROM (SELECT 'admin1', '$2a$10$kYoVTGxwcPGyGjQqhmE.hOPCUxlKR0piLnyD71Ud9PPf8lO0Nw7sG', now() AS create_time, now() AS update_time) AS TMP
WHERE NOT EXISTS (SELECT id FROM cms_user WHERE username = 'admin1') LIMIT 1;