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

void error(char *msg)
{
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[])
{
     int sockfd, newsockfd, portno, clilen;
     char buffer[256];
     struct sockaddr_in serv_addr, cli_addr;
     int n;
     if (argc < 2) {
         fprintf(stderr,"ERROR, no port provided\n");
         exit(1);
     }
     sockfd = socket(AF_INET, SOCK_STREAM, 0);
     if (sockfd < 0) 
        error("ERROR opening socket");
     bzero((char *) &serv_addr, sizeof(serv_addr));
     portno = atoi(argv[1]);
     serv_addr.sin_family = AF_INET;
     serv_addr.sin_addr.s_addr = INADDR_ANY;
     serv_addr.sin_port = htons(portno);
     if (bind(sockfd, (struct sockaddr *) &serv_addr,
              sizeof(serv_addr)) < 0) 
              error("ERROR on binding");
     listen(sockfd,5);
     clilen = sizeof(cli_addr);
     newsockfd = accept(sockfd, 
                 (struct sockaddr *) &cli_addr, 
                 &clilen);
     if (newsockfd < 0) 
          error("ERROR on accept");
     bzero(buffer,256);
     n = read(newsockfd,buffer,255);
     if (n < 0) error("ERROR reading from socket");
     printf("Here is the message: %s\n",buffer);
     
    int buffer_tokens[256];
    int i=0;

    for (char *token = strtok(buffer," "); token != NULL ; token = strtok(NULL, " ")){
        buffer_tokens[i++]=atoi(token); // convierto el token a integer
    }
    /*
    for (i=0; i<=sizeof(buffer_tokens); i++){
        printf("%d",buffer_tokens[i]);
    }
    */
    int resultado=0;
    int cant_tokens=i;

    for (i=0; i<=cant_tokens; i++){
        resultado=resultado + buffer_tokens[i]; // Realiza la suma de los tokens
    }
    printf("El resultado es:%d",resultado);
    
     n = write(newsockfd,&resultado,sizeof(int));
     if (n < 0) error("ERROR writing to socket");
     return 0; 
}
