INSERT INTO public.user (tax_number, email, first_name, identity_card_number, last_name, password, phone_number,
                         register_timestamp, user_status)
VALUES (123456, 'katerina@gmail.com', 'Katerina', 'AG45789', 'Konstantinidi', '12345', '6985647881',
        '2023-01-13 20:26:07.000000', null);

INSERT INTO public.divorce (id, application_timest, contract_details, notarial_act_number, status, submit_date,
                            lead_lawyer_id)
VALUES (DEFAULT, null, 'blah', '123', null, '2023-01-15 00:33:28.000000', 123456);

INSERT INTO public.user_divorce (divorce_id,user_id)
VALUES ('1', 123456);

INSERT INTO public."divorceStatement" (id, choice, comment, faculty, timestamp, person_id)
VALUES (1, 'ACCEPT', 'blah blah', 'SPOUSE', '2023-01-13 20:38:13.000000', 123456);

INSERT INTO public.divorce_statement ("Divorce_id", statement_id)
VALUES (1, 1);



--
--
-- INSERT INTO public.user (tax_number, email, first_name, identity_card_number, last_name, password, phone_number,
--                            register_timestamp, user_status)
-- VALUES (123456, 'katerina@gmail.com', 'Katerina', 'AG45789', 'Konstantinidi', '12345', '6985647881',
--         '2023-01-13 20:26:07.000000', null);
--
-- INSERT INTO public.divorce (id, application_timest, contract_details, notarial_act_number, status, submit_date,
--                             lead_lawyer_id)
-- VALUES (DEFAULT, null, 'blah', '123', null, '2023-01-15 00:33:28.000000', 123456);
--
-- INSERT INTO public.user_cases ("User_tax_number", cases_id)
-- VALUES (123456, '1');
-- INSERT INTO public.divorce_users ("Divorce_id", users_tax_number)
-- VALUES ('1', 123456);
--
-- INSERT INTO public."divorceStatement" (id, choice, comment, faculty, timestamp, person_id)
-- VALUES (1, 'ACCEPT', 'blah blah', 'SPOUSE', '2023-01-13 20:38:13.000000', 123456);
--
-- INSERT INTO public.divorce_statement ("Divorce_id", statement_id)
-- VALUES (1, 1);


