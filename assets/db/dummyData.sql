--Tool: https://www.mockaroo.com

-- Users: 1-9 LAWYER, 10-29 SPOUSE, 30-31 NOTARY
-- Divorce: 5,7 ACCEPTED


--Layers
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('1', 'kdegogay1@redcross.org', true, 'Kenyon', 'WY980950', 'De Gogay', 'nKfOU2T', '8222214093', '2022-06-14 10:13:50', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('2', 'dpoge2@jalbum.net', true, 'Danie', 'OS199776', 'Poge', 'S7trB0ghnzk9', '4236148132', '2021-08-17 23:40:03', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('3', 'navieson9@apple.com', true, 'Nicole', 'RO997187', 'Avieson', 'e8Va5224ZB', '6328159732', '2023-02-19 16:57:37', 'PENDING_REGISTRATION');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('4', 'momoylane4@techcrunch.com', true, 'Malvina', 'IX052152', 'O''Moylane', 'cnmOWvgMi', '2248052514', '2021-10-04 08:27:07', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('5', 'rshirrell5@google.de', true, 'Raine', 'DM341482', 'Shirrell', 'MVgNbNp', '5059630666', '2022-07-06 13:20:01', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('6', 'atoope6@g.co', true, 'Angie', 'NA511439', 'Toope', 'bPPYZC', '5675699448', '2022-04-08 18:22:41', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('7', 'lroskam7@xinhuanet.com', true, 'Lise', 'RB405155', 'Roskam', 'pMYpW3b7', '5371615211', '2021-11-08 07:33:00', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('8', 'jcohan8@microsoft.com', true, 'Junie', 'XB166318', 'Cohan', 'hYA0Ga', '6883542267', '2022-02-27 02:34:06', 'PENDING_APPROVAL');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('9', 'aharpura@cdbaby.com', true, 'Ameline', 'TF159217', 'Harpur', 'RaNI9DC0C', '2384469776', '2022-10-17 21:12:49', 'DISABLED');
UPDATE public."user"
SET role = 'LAWYER'
WHERE tax_number = 3;

UPDATE public."user"
SET role = 'LAWYER'
WHERE tax_number = 9;

UPDATE public."user"
SET role = 'LAWYER'
WHERE tax_number = 5;

UPDATE public."user"
SET role = 'LAWYER'
WHERE tax_number = 7;

UPDATE public."user"
SET role = 'LAWYER'
WHERE tax_number = 4;

UPDATE public."user"
SET role = 'LAWYER'
WHERE tax_number = 8;

UPDATE public."user"
SET role = 'LAWYER'
WHERE tax_number = 2;

UPDATE public."user"
SET role = 'LAWYER'
WHERE tax_number = 1;

UPDATE public."user"
SET role = 'LAWYER'
WHERE tax_number = 6;




--Spouses
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('10', 'maubert7@woothemes.com', true, 'Mair', 'MM493344', 'Aubert', 'bZZXfMRJ', '7286368203', '2022-02-07 06:04:41', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('11', 'vattryde0@live.com', true, 'Vanya', 'LT173436', 'Attryde', 'tvYSHzPqPK', '1434001227', '2023-01-28 16:09:47', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('12', 'lottley7@netscape.com', true, 'Leshia', 'IW359985', 'Ottley', '9Hr6FznqVI', '5945612560', '2021-08-16 21:29:39', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('13', 'wmccurry3@123-reg.co.uk', true, 'Winna', 'YG263572', 'McCurry', 'Ytw7yl', '1518832649', '2021-11-11 13:28:30', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('14', 'aliffe6@linkedin.com', true, 'Alicia', 'RP062273', 'Liffe', 'ZKVyoy9CNy', '9449669195', '2022-04-23 14:14:25', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('15', 'snorewood0@shareasale.com', true, 'Saree', 'ZN372848', 'Norewood', 'vhCyBoKDR48', '1426437589', '2022-01-20 03:27:37', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('16', 'slusted5@cpanel.net', true, 'Salli', 'VE488283', 'Lusted', 'hwiPXc', '9943331691', '2021-08-18 21:22:40', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('17', 'jscawn2@icio.us', true, 'Josefina', 'XB092388', 'Scawn', 'pqOnW3w', '8495374947', '2021-12-30 14:46:47', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('18', 'rdiggons4@spotify.com', true, 'Rosetta', 'QI004110', 'Diggons', 'irG3OA3oAf0C', '9123497617', '2022-02-05 08:02:17', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('19', 'kettels1@ucsd.edu', true, 'Kip', 'PO294645', 'Ettels', '4ovPUvH', '6044693405', '2022-03-12 23:50:33', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('20', 'gclail2@samsung.com', true, 'Gillan', 'SO934433', 'Clail', 'ZZrkIlc2XT7', '8779545281', '2022-06-26 00:11:17', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('21', 'boldale8@mlb.com', true, 'Bat', 'CK375563', 'Oldale', 'EittuXPBy', '2893377871', '2021-11-15 06:17:45', 'PENDING_APPROVAL');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('22', 'nmessham9@is.gd', true, 'Neil', 'FQ714838', 'Messham', '4oPmhE7TK', '4954550251', '2021-10-06 18:49:31', 'PENDING_REGISTRATION');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('23', 'ostedallc@example.com', true, 'Omar', 'BG727881', 'Stedall', 'xVZfEifBAMVM', '2965855229', '2021-08-13 18:53:19', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('24', 'fhaggithd@ihg.com', true, 'Francklyn', 'RT468229', 'Haggith', 'Mm2qL82cS', '3153965247', '2022-02-26 00:11:47', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('25', 'asorsbye@cpanel.net', true, 'Ava', 'BE451139', 'Sorsby', 'GueJjpg', '7188719058', '2022-12-31 02:36:42', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('26', 'tstanbridgef@pen.io', true, 'Tristan', 'AF215842', 'Stanbridge', 'YWPaAvrUC0', '7078568331', '2022-11-02 22:46:41', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('27', 'lscedallg@dailymotion.com', true, 'Lillis', 'VN116965', 'Scedall', 'YACaiQeEDV', '5301954996', '2022-10-31 12:01:27', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('28', 'mlimberth@archive.org', true, 'Merwin', 'FT201317', 'Limbert', '88c6zEo', '1458962274', '2022-09-09 21:29:14', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('29', 'mjemmetti@newsvine.com', true, 'Merwyn', 'EK950202', 'Jemmett', 'mNEjpCOIV', '1385931056', '2022-03-12 14:36:55', 'ENABLED');

-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (10, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (11, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (12, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (13, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (14, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (15, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (16, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (17, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (18, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (19, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (20, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (21, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (22, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (23, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (24, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (25, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (26, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (27, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (28, 'SPOUSE');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (29, 'SPOUSE');
UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 12;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 13;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 28;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 20;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 21;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 10;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 11;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 18;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 14;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 15;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 29;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 22;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 23;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 16;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 17;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 26;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 27;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 19;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 24;

UPDATE public."user"
SET role = 'SPOUSE'
WHERE tax_number = 25;



--Notaries
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('30', 'hhuishb@xing.com', true, 'Helenelizabeth', 'KK161286', 'Huish', 'QBIFJIcniL', '7432731931', '2022-03-02 07:28:00', 'ENABLED');
insert into public."user" (tax_number, email, enabled, first_name, identity_card_number, last_name, password, phone_number, register_timestamp, user_status) values ('31', 'bcancellor7@t-online.de', true, 'Barde', 'MI214899', 'Cancellor', 'F8BT7SS', '9737435205', '2022-02-15 12:42:50', 'ENABLED');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (30, 'NOTARY');
-- INSERT INTO public.user_roles ("User_tax_number", roles)VALUES (31, 'NOTARY');
UPDATE public."user"
SET role = 'NOTARY'
WHERE tax_number = 30;

UPDATE public."user"
SET role = 'NOTARY'
WHERE tax_number = 31;


--Divorce
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, status,lead_lawyer_id)VALUES (1, '2021-08-13 18:53:19', '2021-08-13 18:53:19','platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt ante vel ipsum', 'CANCELLED', 1);
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, status,lead_lawyer_id)VALUES (2, '2021-08-13 18:53:19',  null,'dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor', 'DRAFT', 1);
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, status,lead_lawyer_id)VALUES (3, '2021-08-13 18:53:19', null,'justo eu massa donec dapibus duis at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero convallis eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia', 'DRAFT', 1);
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, status,lead_lawyer_id)VALUES (4, '2021-08-13 18:53:19', '2021-08-13 18:53:19','rhoncus sed vestibulum sit amet cursus id turpis integer aliquet massa id lobortis convallis tortor risus dapibus augue vel accumsan tellus nisi eu orci mauris lacinia sapien quis libero nullam sit amet turpis elementum ligula vehicula consequat morbi a ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio', 'CANCELLED', 1);
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, notarial_act_number, status,lead_lawyer_id) VALUES (5, '2021-08-13 18:53:19', '2021-08-13 18:53:19','mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a','VTEL - 6381', 'COMPLETED', 1);
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, status,lead_lawyer_id)VALUES (6, '2021-08-13 18:53:19', null,'non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum', 'PENDING', 1);
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, notarial_act_number, status,lead_lawyer_id) VALUES (7, '2021-08-13 18:53:19', '2021-08-13 18:53:19','consequat lectus in est risus auctor sed tristique in tempus sit amet sem fusce consequat nulla nisl nunc nisl duis bibendum felis sed interdum venenatis turpis enim blandit mi in porttitor pede justo eu massa donec dapibus duis at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit','XWIY - 6684', 'COMPLETED', 1);
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, status,lead_lawyer_id)VALUES (8, '2021-08-13 18:53:19', '2021-08-13 18:53:19','sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie','CANCELLED', 1);
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, status,lead_lawyer_id)VALUES (9, '2021-08-13 18:53:19', null,'consequat morbi a ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis', 'PENDING', 1);
INSERT INTO public.divorce (id, application_timestamp, close_timestamp, contract_details, status,lead_lawyer_id)VALUES (10, '2021-08-13 18:53:19', null,'orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui', 'PENDING', 1);

--Connect lawyers to divorce cases
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 1);
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 2);
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 3);
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 4);
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 5);
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 6);
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 7);
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 8);
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 9);
INSERT INTO public.user_divorce (user_id, divorce_id)VALUES (1, 10);

--Divorce statements
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (1, 10, 'SPOUSE_ONE', 'WAITING', '2021-08-13 18:53:19', 'praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim in tempor turpis nec euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (2, 11, 'SPOUSE_ONE', 'WAITING', '2021-08-13 18:53:19', 'ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (3, 12, 'SPOUSE_ONE', 'OBJECTED', '2021-08-13 18:53:19', 'ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu sed augue aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque quisque porta volutpat erat quisque erat eros viverra eget congue eget semper rutrum nulla nunc purus phasellus in felis donec');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (4, 13, 'SPOUSE_ONE', 'REJECTED', '2021-08-13 18:53:19', 'nisi volutpat eleifend donec ut dolor morbi vel lectus in quam fringilla rhoncus mauris enim leo rhoncus sed vestibulum sit amet cursus id turpis integer aliquet massa id lobortis convallis tortor risus dapibus augue vel accumsan tellus nisi eu orci mauris lacinia sapien quis libero nullam sit amet turpis elementum ligula vehicula consequat morbi a ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae mauris viverra');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (5, 14, 'SPOUSE_ONE', 'ACCEPTED', '2021-08-13 18:53:19', 'lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in libero ut');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (6, 15, 'SPOUSE_ONE', 'ACCEPTED', '2021-08-13 18:53:19', 'in est risus auctor sed tristique in tempus sit amet sem fusce consequat nulla nisl nunc nisl duis bibendum felis sed interdum venenatis turpis enim blandit mi in porttitor pede justo eu massa donec dapibus duis at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (7, 16, 'SPOUSE_ONE', 'ACCEPTED', '2021-08-13 18:53:19', 'suspendisse ornare consequat lectus in est risus auctor sed tristique in tempus sit amet sem fusce consequat nulla nisl nunc nisl duis bibendum felis sed interdum venenatis turpis enim blandit mi in porttitor pede justo eu massa donec dapibus duis at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (8, 17, 'SPOUSE_ONE', 'PENDING', '2021-08-13 18:53:19', 'turpis nec euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (9, 18, 'SPOUSE_ONE', 'ACCEPTED', '2021-08-13 18:53:19', 'in quis justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (10, 19, 'SPOUSE_ONE', 'ACCEPTED', '2021-08-13 18:53:19', 'posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer');
--Spouse two
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (1, 20, 'SPOUSE_TWO', 'WAITING', '2021-08-13 18:53:19', 'platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero convallis eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu sed augue aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (2, 21, 'SPOUSE_TWO', 'WAITING', '2021-08-13 18:53:19', 'diam cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae mauris viverra diam vitae quam suspendisse potenti nullam porttitor lacus at turpis donec posuere metus vitae ipsum aliquam non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu felis fusce posuere felis sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras mi pede malesuada in imperdiet et commodo');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (3, 22, 'SPOUSE_TWO', 'ACCEPTED', '2021-08-13 18:53:19', 'pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim in tempor turpis nec euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (4, 23, 'SPOUSE_TWO', 'OBJECTED', '2021-08-13 18:53:19', 'sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (5, 24, 'SPOUSE_TWO', 'ACCEPTED', '2021-08-13 18:53:19', 'velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero convallis eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien dignissim');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (6, 25, 'SPOUSE_TWO', 'PENDING', '2021-08-13 18:53:19', 'orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in libero ut');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (7, 26, 'SPOUSE_TWO', 'ACCEPTED', '2021-08-13 18:53:19', 'vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim in tempor turpis nec euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (8, 27, 'SPOUSE_TWO', 'REJECTED', '2021-08-13 18:53:19', 'luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at nunc commodo placerat praesent blandit nam nulla integer pede justo lacinia eget tincidunt eget tempus vel pede morbi porttitor lorem id ligula suspendisse ornare consequat lectus in est risus');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (9, 28, 'SPOUSE_TWO', 'OBJECTED', '2021-08-13 18:53:19', 'dapibus duis at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero convallis eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu sed augue aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque quisque porta volutpat erat quisque erat eros viverra eget');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (10, 29, 'SPOUSE_TWO', 'ACCEPTED', '2021-08-13 18:53:19', 'dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer');
--Notaries
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (1, 31, 'NOTARY', 'WAITING', '2021-08-13 18:53:19', null);
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (2, 31, 'NOTARY', 'WAITING', '2021-08-13 18:53:19', null);
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (3, 30, 'NOTARY', 'WAITING', '2021-08-13 18:53:19', null);
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (4, 30, 'NOTARY', 'WAITING', '2021-08-13 18:53:19', null);
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (5, 31, 'NOTARY', 'ACCEPTED', '2021-08-13 18:53:19', null);
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (6, 30, 'NOTARY', 'WAITING', '2021-08-13 18:53:19', 'EC09970');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (7, 30, 'NOTARY', 'ACCEPTED', '2021-08-13 18:53:19', 'YL24936');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (8, 31, 'NOTARY', 'WAITING', '2021-08-13 18:53:19', 'JM37584');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (9, 31, 'NOTARY', 'WAITING', '2021-08-13 18:53:19', 'KS00037');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (10, 31, 'NOTARY', 'PENDING', '2021-08-13 18:53:19', 'JO77075');
--Lawyer_two
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (1, 2, 'LAWYER_TWO', 'WAITING', '2021-08-13 18:53:19', 'hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (2, 3, 'LAWYER_TWO', 'WAITING', '2021-08-13 18:53:19', 'turpis eget elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero convallis eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam id ornare imperdiet sapien urna');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (3, 4, 'LAWYER_TWO', 'OBJECTED', '2021-08-13 18:53:19', 'phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (4, 5, 'LAWYER_TWO', 'OBJECTED', '2021-08-13 18:53:19', 'cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in leo maecenas');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (5, 6, 'LAWYER_TWO', 'ACCEPTED', '2021-08-13 18:53:19', 'eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu sed augue aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque quisque porta volutpat erat quisque erat eros viverra eget congue eget semper rutrum nulla nunc purus phasellus in felis donec semper sapien a libero nam dui proin leo odio porttitor id consequat in consequat ut nulla sed accumsan felis ut at dolor quis odio consequat varius integer ac leo pellentesque ultrices mattis');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (6, 7, 'LAWYER_TWO', 'OBJECTED', '2021-08-13 18:53:19', 'sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (7, 8, 'LAWYER_TWO', 'ACCEPTED', '2021-08-13 18:53:19', 'posuere metus vitae ipsum aliquam non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu felis fusce posuere felis sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (8, 9, 'LAWYER_TWO', 'PENDING', '2021-08-13 18:53:19', 'turpis donec posuere metus vitae ipsum aliquam non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu felis fusce posuere felis sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (9, 2, 'LAWYER_TWO', 'OBJECTED', '2021-08-13 18:53:19', 'consequat morbi a ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae mauris viverra diam vitae quam suspendisse potenti nullam porttitor lacus at turpis donec posuere metus vitae ipsum aliquam non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu felis fusce posuere felis sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient');
insert into public."divorceStatement" (divorce_id, person_id, faculty, choice, timestamp, comment) values (10, 3, 'LAWYER_TWO', 'OBJECTED', '2021-08-13 18:53:19', 'eget orci vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim in tempor turpis nec euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a odio in');

--User - divorce table
--Spouse
insert into public.user_divorce (user_id, divorce_id) values (10, 1);
insert into public.user_divorce (user_id, divorce_id) values (11, 2);
insert into public.user_divorce (user_id, divorce_id) values (12, 3);
insert into public.user_divorce (user_id, divorce_id) values (13, 4);
insert into public.user_divorce (user_id, divorce_id) values (14, 5);
insert into public.user_divorce (user_id, divorce_id) values (15, 6);
insert into public.user_divorce (user_id, divorce_id) values (16, 7);
insert into public.user_divorce (user_id, divorce_id) values (17, 8);
insert into public.user_divorce (user_id, divorce_id) values (18, 9);
insert into public.user_divorce (user_id, divorce_id) values (19, 10);
insert into public.user_divorce (user_id, divorce_id) values (20, 1);
insert into public.user_divorce (user_id, divorce_id) values (21, 2);
insert into public.user_divorce (user_id, divorce_id) values (22, 3);
insert into public.user_divorce (user_id, divorce_id) values (23, 4);
insert into public.user_divorce (user_id, divorce_id) values (24, 5);
insert into public.user_divorce (user_id, divorce_id) values (25, 6);
insert into public.user_divorce (user_id, divorce_id) values (26, 7);
insert into public.user_divorce (user_id, divorce_id) values (27, 8);
insert into public.user_divorce (user_id, divorce_id) values (28, 9);
insert into public.user_divorce (user_id, divorce_id) values (29, 10);
--Notaries
insert into public.user_divorce (user_id, divorce_id) values (31, 1);
insert into public.user_divorce (user_id, divorce_id) values (31, 2);
insert into public.user_divorce (user_id, divorce_id) values (31, 5);
insert into public.user_divorce (user_id, divorce_id) values (30, 3);
insert into public.user_divorce (user_id, divorce_id) values (30, 4);
insert into public.user_divorce (user_id, divorce_id) values (30, 6);
insert into public.user_divorce (user_id, divorce_id) values (30, 7);
insert into public.user_divorce (user_id, divorce_id) values (31, 8);
insert into public.user_divorce (user_id, divorce_id) values (31, 9);
insert into public.user_divorce (user_id, divorce_id) values (31, 10);

--Password updates
UPDATE public."user"
SET password = '$2a$10$ZhPV2rX.B8Y91XE92ZmfNeosKtIlk4w6AvRmzcMY2tkPmGhVCn7oi'