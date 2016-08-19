-----------------------------------------	
-- Accounts
-----------------------------------------
INSERT INTO cm_account (id, creation_time, modification_time,  first_name, last_name, password, profile_img, username) 
VALUES ('2c980ec9568ff26e01568ff344100000', '2016-08-15 19:09', '2016-08-15 19:09', 'Thomas', 'Kim', 'freebirds', NULL, 'habuma');
INSERT INTO cm_account (id, creation_time, modification_time,  first_name, last_name, password, profile_img, username) 
VALUES ('2c980ec9568ff26e01568ff344100001', '2016-08-15 19:09', '2016-08-15 19:09', 'Dave', 'Gamache', 'melbourne', NULL, 'kdonald');
INSERT INTO cm_account (id, creation_time, modification_time,  first_name, last_name, password, profile_img, username) 
VALUES ('2c980ec9568ff26e01568ff344100002', '2016-08-15 19:09', '2016-08-15 19:09', 'Jacon ', 'Thornton', 'atlanta', NULL, 'rclarkson');

-----------------------------------------	
-- Friends
-----------------------------------------	
INSERT INTO cm_friend (requester_id, acceptant_id, accepted, accepted_time, requested_time) 
	VALUES ('2c980ec9568ff26e01568ff344100000', '2c980ec9568ff26e01568ff344100001', true, '2016-08-15 23:35', '2016-08-15 23:35');
	
INSERT INTO cm_friend (requester_id, acceptant_id, accepted, accepted_time, requested_time) 
	VALUES ('2c980ec9568ff26e01568ff344100000', '2c980ec9568ff26e01568ff344100002', true, '2016-08-15 23:35', '2016-08-15 23:35');
-----------------------------------------	
-- Profile
-----------------------------------------	

INSERT INTO cm_profile (id, creation_time, modification_time,  profile_img, profile_bg, text ) 
VALUES ('2c980ec9568ff26e01568ff344100000', '2016-08-15 19:09', '2016-08-15 19:09',  'images2.jpeg', 'notes-vintage-cat-symbols.jpg', 
	'my eyes I decieded that I will let you know this secret.');
INSERT INTO cm_profile (id, creation_time, modification_time,  profile_img, profile_bg, text ) 
VALUES ('2c980ec9568ff26e01568ff344100001', '2016-08-15 19:09', '2016-08-15 19:09',  'co2.jpg', 'coffee.jpg', 
	'Son song blue.');
INSERT INTO cm_profile (id, creation_time, modification_time,  profile_img, profile_bg, text ) 
VALUES ('2c980ec9568ff26e01568ff344100002', '2016-08-15 19:09', '2016-08-15 19:09',  'images13.jpeg', 'notes-vintage-cat-symbols.jpg', 
	'Tell me.');
	
-----------------------------------------	
-- Articles
-----------------------------------------	

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content) 
VALUES (1, '2016-08-15 23:34', '2016-08-15 23:34', NULL, '2c980ec9568ff26e01568ff344100000', 'Test 01');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content) 
VALUES (2, '2016-08-15 23:35', '2016-08-15 23:35', NULL, '2c980ec9568ff26e01568ff344100000', 'Test 02');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content) 
VALUES (3, '2016-08-15 23:35', '2016-08-15 23:35', NULL, '2c980ec9568ff26e01568ff344100000', 'Test 03');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content) 
VALUES (4, '2016-08-15 23:35', '2016-08-15 23:35',  NULL, '2c980ec9568ff26e01568ff344100000', 'Test 04');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content)  
VALUES (5, '2016-08-15 23:35', '2016-08-15 23:35', NULL, '2c980ec9568ff26e01568ff344100000', 'Test 05');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content) 
VALUES (6, '2016-08-15 23:35', '2016-08-15 23:35', NULL, '2c980ec9568ff26e01568ff344100000', 'Test 06');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content) 
VALUES (7, '2016-08-15 23:35', '2016-08-15 23:35', NULL, '2c980ec9568ff26e01568ff344100000', 'Test 07');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content) 
VALUES (8, '2016-08-15 23:35', '2016-08-15 23:35', NULL, '2c980ec9568ff26e01568ff344100001', 'Test 08');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content) 
VALUES (9, '2016-08-15 23:35', '2016-08-15 23:35', 'deer-ohdear-medium.jpg', '2c980ec9568ff26e01568ff344100001',
	'Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content) 
VALUES (10, '2016-08-15 23:35', '2016-08-15 23:35', NULL, '2c980ec9568ff26e01568ff344100002',
	'Donec id elit non mi porta gravida at eget metus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Donec ullamcorper nulla non metus auctor fringilla. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Sed posuere consectetur est at lobortis.');

INSERT INTO da_article (id, creation_time, modification_time, image_url, writer_id,  content)  
VALUES (11, '2016-08-15 23:35', '2016-08-15 23:35', 'bg_1024.jpg', '2c980ec9568ff26e01568ff344100000',  
	'Aenean lacinia bibendum nulla sed consectetur. Vestibulum id ligula porta felis euismod semper. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');



--INSERT INTO CM_ID_GEN (KEY_NAME,next_val) values('article_id',100);