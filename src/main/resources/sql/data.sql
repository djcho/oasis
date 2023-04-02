insert into department(id, parent_id, name, level, created_at, updated_at)values(1, 0, '/', 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(id, parent_id, name, level, created_at, updated_at)values(2, 1, '보안기술연구소', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(id, parent_id, name, level, created_at, updated_at)values(3, 2, '아이사인', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(id, parent_id, name, level, created_at, updated_at)values(4, 2, '와플', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(id, parent_id, name, level, created_at, updated_at)values(5, 2, '디아모', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(id, parent_id, name, level, created_at, updated_at)values(6, 3, '개발1팀', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(id, parent_id, name, level, created_at, updated_at)values(7, 3, '개발2팀', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(id, parent_id, name, level, created_at, updated_at)values(8, 3, '개발3팀', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(id, parent_id, name, level, created_at, updated_at)values(9, 3, '개발4팀', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into work_position(id, name, level, created_at, updated_at) values(1, '사원', 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_position(id, name, level, created_at, updated_at) values(2, '대리', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_position(id, name, level, created_at, updated_at) values(3, '과장', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_position(id, name, level, created_at, updated_at) values(4, '차장', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_position(id, name, level, created_at, updated_at) values(5, '부장', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_position(id, name, level, created_at, updated_at) values(6, '이사보', 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_position(id, name, level, created_at, updated_at) values(7, '이사', 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into work_duty(id, name, created_at, updated_at) values(1, '팀원', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into work_duty(id, name, created_at, updated_at) values(2, '팀장', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into schedule_type(id, name, created_at, updated_at) values(1, 'WFH', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into schedule_type(id, name, created_at, updated_at) values(2, 'DAYOFF', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into schedule_type(id, name, created_at, updated_at) values(3, 'NOONOFF', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into schedule_type(id, name, created_at, updated_at) values(4, 'MORNINGOFF', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into schedule_type(id, name, created_at, updated_at) values(5, 'ETC', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into role(id, name) values (1, 'ADMIN');
insert into role(id, name) values (2, 'MANAGER');
insert into role(id, name) values (3, 'USER');

insert into member(id, uid, name, password, department_id, work_position_id, work_duty_id, created_at, updated_at)values (1, 'admin@pentasecurity.com', '최고관리자', '$2a$10$jQDooQxrCsMBWHlj15h8pO.uC5tkj/Fonb.8GzDaiJOQQY6fyAGRu', 7, 3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into member(id, uid, name, password, department_id, work_position_id, work_duty_id, created_at, updated_at)values (2, 'djcho@pentasecurity.com', '조대준', '$2a$10$c3J3SbE0N7/3tkIMQlyKi.HTMC5sXj1wYQlEvwylr57PiEkZlQ10a', 7, 3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into member(id, uid, name, password, department_id, work_position_id, work_duty_id, created_at, updated_at)values (3, 'yesora@pentasecurity.com', '최예소라', '$2a$10$c3J3SbE0N7/3tkIMQlyKi.HTMC5sXj1wYQlEvwylr57PiEkZlQ10a', 6, 2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into member(id, uid, name, password, department_id, work_position_id, work_duty_id, created_at, updated_at)values (4, 'jwmoon@pentasecurity.com', '문장완', '$2a$10$c3J3SbE0N7/3tkIMQlyKi.HTMC5sXj1wYQlEvwylr57PiEkZlQ10a', 8, 2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into member(id, uid, name, password, department_id, work_position_id, work_duty_id, created_at, updated_at)values (5, 'yjshin@pentasecurity.com', '신예진', '$2a$10$c3J3SbE0N7/3tkIMQlyKi.HTMC5sXj1wYQlEvwylr57PiEkZlQ10a', 8, 2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into member_roles(members_id, roles_id) values (1, 1);
/*
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-01', '무제', 1, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-02', '무제', 1, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-03', '무제', 1, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-04', '무제', 1, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-05', '무제', 1, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-06', '무제', 1, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-07', '무제', 1, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-08', '무제', 1, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-09', '무제', 1, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-10', '무제', 1, 2);

insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-01', '무제', 2, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-02', '무제', 2, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-03', '무제', 3, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (14, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-04', '무제', 3, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (15, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-05', '무제', 4, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (16, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-06', '무제', 4, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (17, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-07', '무제', 4, 2);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (18, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-08', '무제', 1, 3);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (19, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-09', '무제', 1, 1);
insert into schedule (sid, created_at, updated_at, content, date, name, member_sid, schedule_type_sid)
    value (20, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '냉무', '2022-10-10', '무제', 1, 4);
*/