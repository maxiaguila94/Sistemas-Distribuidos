# correr con

# docker build -t tp3sd .

# docker run --name serversd -dit \
#     -p 8080:80 \
#     -v $PWD/html:/usr/local/apache2/htdocs \
#     -v $PWD/cgi-bin:/usr/local/apache2/cgi-bin \
#      tp3sd

# luego el contenedor se puede detener con
# docker stop serversd

# y reestartear con 
# docker start serversd

# para usar la misma imagen en otros proyectos hay que borrar el contenedor
# docker rm serversd

# y luego volver a correr la misma imagen


FROM httpd

COPY ./httpd.conf /usr/local/apache2/conf/httpd.conf

RUN apt-get update && apt-get install -y \
    --no-install-recommends \ 
    python3-pip

RUN chmod 755 /usr/local/apache2/cgi-bin/*

# imagen base https://hub.docker.com/_/httpd/