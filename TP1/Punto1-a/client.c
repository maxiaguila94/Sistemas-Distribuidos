#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netdb.h>

void error(char *msg)
{
    perror(msg);
    exit(0);
}

int main(int argc, char *argv[])
{
    int sockfd, portno;
    struct sockaddr_in serv_addr;
    struct hostent *server;

    char buffer[256];

    if (argc < 3) {
       fprintf(stderr,"usage %s hostname port\n", argv[0]);
       exit(0);
    }

    portno = atoi(argv[2]);

    
    // creación del socket
    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) 
        error("ERROR opening socket\n");

    // gethostbyname resuelve la ip localmente a partir del archivo /etc/hosts
    if ((server = gethostbyname(argv[1])) == NULL)
        error("ERROR, no such host\n");


    bzero((char *) &serv_addr, sizeof(serv_addr));
    
    serv_addr.sin_family = AF_INET;
    
    serv_addr.sin_port = htons(portno);
    bcopy((char *)server->h_addr, 
         (char *)&serv_addr.sin_addr.s_addr,
         server->h_length);
    
    
    
    if (connect(sockfd,(struct sockaddr * )&serv_addr,sizeof(serv_addr)) < 0) 
        error("ERROR connecting");

    printf("Por favor ingrese los numeros a sumar: ");
    bzero(buffer,256);
    fgets(buffer,255,stdin);
    
    
    if ((write(sockfd,buffer,strlen(buffer))) < 0) 
         error("ERROR writing to socket");

    bzero(buffer,256);
    
    
    if ((read(sockfd,buffer,255)) < 0) 
         error("ERROR reading from socket");

    printf("Resultado: %s\n",buffer);
    return 0;
}
