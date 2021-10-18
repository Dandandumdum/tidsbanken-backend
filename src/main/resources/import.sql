
INSERT INTO users (id, is_admin ,profile_pic) Values (1,false,'CAT PIC');
INSERT INTO users (id, is_admin ,profile_pic) Values (2,false,'DOG PIC');
INSERT INTO users (id, is_admin ,profile_pic) Values (3,true,'WOLF PIC');

INSERT INTO moderators (id, user_id) VALUES (1,3);

INSERT INTO ineligible_period (id, period_end, period_start, created_by) VALUES (1,'Now','Later', 1);
INSERT INTO ineligible_period (id, period_end, period_start, created_by) VALUES (2, '22','33', 2);
INSERT INTO ineligible_period (id, period_end, period_start, created_by) VALUES (3, 'Well','Long', 3);

INSERT INTO vacation_request_status (id, status) VALUES (1, 1);
INSERT INTO vacation_request_status (id, status) VALUES (2, 2);
INSERT INTO vacation_request_status (id, status) VALUES (3, 3);


INSERT INTO vacation_requests (id, period_end, period_start, title, updated, updated_timestamp, moderator_id, status_id, owner_id) VALUES (1, '12/12', '10/10','Holiday to Mexico',false, null, null, 1, 1);
INSERT INTO vacation_requests (id, period_end, period_start, title, updated, updated_timestamp, moderator_id, status_id, owner_id) VALUES (2, '1/11', '10/10','Holiday to a Desert',true, current_timestamp, 1, 3, 2);
INSERT INTO vacation_requests (id, period_end, period_start, title, updated, updated_timestamp, moderator_id, status_id, owner_id) VALUES (3, '11/10', '10/10','Holiday to Mars',true, current_timestamp, 1, 2, 3);



INSERT INTO comments (id, message, timestamp, user_id, request_id) VALUES (1, 'Cool holiday', current_timestamp , 1, 1);
INSERT INTO comments (id, message, timestamp, user_id, request_id) VALUES (2, 'Too hot holiday', current_timestamp , 2, 2);
INSERT INTO comments (id, message, timestamp, user_id, request_id) VALUES (3, 'Extreme holiday', current_timestamp , 3, 3);



