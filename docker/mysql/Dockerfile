FROM mysql

ADD ./1-SCHEMA.sql /docker-entrypoint-initdb.d/
ADD ./2-PRIVILEGES.sql /docker-entrypoint-initdb.d/
ADD ./3-ENTRIES.sql /docker-entrypoint-initdb.d/

EXPOSE 3306


