
insert into department(sid, parent_sid, name, level, created_at, updated_at)values(1, 0, '/', 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(sid, parent_sid, name, level, created_at, updated_at)values(2, 1, '보안기술연구소', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(sid, parent_sid, name, level, created_at, updated_at)values(3, 2, '아이사인', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(sid, parent_sid, name, level, created_at, updated_at)values(4, 2, '와플', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(sid, parent_sid, name, level, created_at, updated_at)values(5, 2, '디아모', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(sid, parent_sid, name, level, created_at, updated_at)values(6, 3, '개발1팀', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(sid, parent_sid, name, level, created_at, updated_at)values(7, 3, '개발2팀', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(sid, parent_sid, name, level, created_at, updated_at)values(8, 3, '개발3팀', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into department(sid, parent_sid, name, level, created_at, updated_at)values(9, 3, '개발4팀', 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

insert into work_position(sid, name, level) values(1, '사원', 0);
insert into work_position(sid, name, level) values(2, '대리', 1);
insert into work_position(sid, name, level) values(3, '과장', 2);
insert into work_position(sid, name, level) values(4, '차장', 3);
insert into work_position(sid, name, level) values(5, '부장', 4);
insert into work_position(sid, name, level) values(6, '이사보', 5);
insert into work_position(sid, name, level) values(7, '이사', 6);

insert into work_duty(sid, name) values(1, '팀원');
insert into work_duty(sid, name) values(2, '팀장');

insert into schedule_type(sid, name) values(1, 'WFH');
insert into schedule_type(sid, name) values(2, 'DAYOFF');
insert into schedule_type(sid, name) values(3, 'NOONOFF');
insert into schedule_type(sid, name) values(4, 'MORNINGOFF');
insert into schedule_type(sid, name) values(5, 'ETC');

insert into member(sid, id, member_role, name, password, department_sid, work_position_sid, work_duty_sid, created_at, updated_at)values (1, 'djcho@pentasecurity.com', 'ADMIN', '조대준', 'jkljkl..', 7, 3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into member(sid, id, member_role, name, password, department_sid, work_position_sid, work_duty_sid, created_at, updated_at)values (2, 'yesora@pentasecurity.com', 'ADMIN', '최예소라', 'jkljkl..', 6, 2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into member(sid, id, member_role, name, password, department_sid, work_position_sid, work_duty_sid, created_at, updated_at)values (3, 'jwmoon@pentasecurity.com', 'ADMIN', '문장완', 'jkljkl..', 8, 2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into member(sid, id, member_role, name, password, department_sid, work_position_sid, work_duty_sid, created_at, updated_at)values (4, 'yjshin@pentasecurity.com', 'ADMIN', '신예진', 'jkljkl..', 8, 2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

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
