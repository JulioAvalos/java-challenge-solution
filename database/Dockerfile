
# Database 
FROM mysql:8

ENV MYSQL_DATABASE=moviesdb 
ENV MYSQL_ROOT_PASSWORD=toor

ADD ./scripts/ /docker-entrypoint-initdb.d/

EXPOSE 3306