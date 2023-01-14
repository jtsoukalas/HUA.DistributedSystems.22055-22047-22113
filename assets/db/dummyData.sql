INSERT INTO public."user" (tax_number, email, first_name, identity_card_number, last_name, password, phone_number,
                           register_timestamp, user_status)
VALUES (123456, 'katerina@gmail.com', 'Katerina', 'AG45789', 'Konstantinidi', '12345', '6985647881',
        '2023-01-13 20:26:07.000000', null);

INSERT INTO public."divorceStatement" (id, agreement, comment, faculty, timestamp, person_id)
VALUES (1, 'true', 'blah blah', 'Spouse', '2023-01-13 20:38:13.000000', 123456);

INSERT INTO public.divorce (id, application_timest, contract_details, notarial_act_number, status, submit_date,
                            tax_number, divorce_id)
VALUES (DEFAULT, '2023-01-13 20:40:10.000000', 'blah blah contract details', '4587', null, '2023-01-13 20:42:00.000000',
        123456, null);

