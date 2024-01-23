INSERT INTO public.roles
  ( id, name, description, created_at, updated_at )
VALUES
  ( '0ee12a71-d742-4ccb-a05c-6df8d2fa811d', 'Subscriber', 'Somebody who only can view the site', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP ),
  ( '2c6dca92-5e66-45b5-8353-27acbff7d4f1', 'Author', 'Somebody who can publish and manage their own posts', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP ),
  ( 'acd20a3f-1e07-44ff-bb5e-14d04a9d7416', 'Administrator', 'Somebody who has access to all the administration features within a single site', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO public.users
  ( id, name, email, password )
VALUES
  ( '6a03d0d9-1687-45aa-8a05-1c4ccb7cef9a', 'lenhatthanh', 'admin@lenhatthanh.com', '$2a$10$D0SGK.aIzuxd/rAwcq/0n.gaYTjsN/ZYfwnAy1C4mYGjrwsTsjx4K' );

INSERT INTO public.users_roles
  ( user_id, role_id )
VALUES
  ( '6a03d0d9-1687-45aa-8a05-1c4ccb7cef9a', 'acd20a3f-1e07-44ff-bb5e-14d04a9d7416' );
