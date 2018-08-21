/* A simple server in the internet domain using TCP
   The port number is passed as an argument */
#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <strings.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>

void error(char *msg)
{
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[])
{
    
    /*
    sockfd: file descriptor del socket del server
    newsockfd: file descriptor del socket para un cliente
    portno: puerto de servicio
    clien: variable cliente
    
    */
    socklen_t clilen;
    int sockfd, newsockfd, portno;
    char buffer[256];
    struct sockaddr_in serv_addr, cli_addr; 

    if (argc < 2) 
    {
         fprintf(stderr,"ERROR, no port provided\n");
         exit(1);
     }
    
    // creamos el socket
    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) 
        error("ERROR opening socket");

    //  inicialización de la estructura serv_addr
    bzero((char *) &serv_addr, sizeof(serv_addr));
     
    //  puerto
    portno = atoi(argv[1]);

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(portno);
     
    if (bind(sockfd, (struct sockaddr *) &serv_addr,
              sizeof(serv_addr)) < 0) 
              error("ERROR on binding");
     
    listen(sockfd,5);
     
    clilen = sizeof(cli_addr);
     
    if ((newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, 
            &clilen)) < 0) 
            error("ERROR on accept");

    bzero(buffer,256);
     
    if ((read(newsockfd,buffer,255)) < 0) 
        error("ERROR reading from socket");
     
    printf("Here is the message: %s\n",buffer);
     
    int buffer_tokens[256];
    int i=0;
    int resultado=0;
    int cant_tokens=i;
    char response[256];

    bzero(buffer_tokens,256);

    for (char *token = strtok(buffer," "); token != NULL ; token = strtok(NULL, " ")){
        buffer_tokens[i++]=atoi(token); // convierto el token a integer
    }

    
    for (i=0; i<=cant_tokens; i++)
    {
        resultado=resultado + buffer_tokens[i]; // Realiza la suma de los tokens
    }
    
    printf("El resultado es:%d",resultado);
    sprintf(response, "%d", resultado);


    if ((write(newsockfd, &response, sizeof(response))) < 0) 
        error("ERROR writing to socket");
     
    return 0; 
}
