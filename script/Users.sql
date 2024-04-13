CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_doctor BOOLEAN NOT NULL,
    specialization character varying(100),
    med_lic_num character(8),
    doctor_id INT
)

INSERT INTO public.users(
	id, first_name, last_name, email, password, is_doctor)
	VALUES (1, 'Mock', 'User', 'mockuser@email.com', '$2a$10$nw6TwpZ6cF1dDRFbEs8Gsu6nHWORyXsQ7K3dBeqEbi4EfnsiAR/8y', false);