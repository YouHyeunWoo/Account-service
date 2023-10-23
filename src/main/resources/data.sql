--샘플로 생성하는 어플리케이션이므로 구동될 때 자동으로 사용자 정보가 저정되도록 하기

insert into account_user(id, name, created_at, updated_at)
values (1, 'Pororo', now(), now());
insert into account_user(id, name, created_at, updated_at)
values (2, 'Lupi', now(), now());
insert into account_user(id, name, created_at, updated_at)
values (3, 'Eddie', now(), now());

--이렇게 할 때 주의할 점
--resourses파일의 application.yml파일에
--spring 아래 jpa 아래 defer-datasource-initialization: true
--이 문장이 있어야 정상적으로 동작됨
--테이블이 생성된 이후에 데이터를 저장하도록 데이터 저장을 늦춰달라는 코드임