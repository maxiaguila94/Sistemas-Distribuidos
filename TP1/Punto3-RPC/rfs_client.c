/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "rfs.h"
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>
#include <string.h>
#include <errno.h>

#define RED     "\x1b[31m"
#define GREEN   "\x1b[32m"
#define YELLOW  "\x1b[33m"
#define BLUE    "\x1b[34m"
#define MAGENTA "\x1b[35m"
#define CYAN    "\x1b[36m"
#define RESET   "\x1b[0m"


CLIENT * rfs_1(char *host)
{

	CLIENT *clnt;
		
#ifndef	DEBUG
	clnt = clnt_create (host, RFS, RFS_VERS_1, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */

	return clnt;
}

// RFS OPEN WRAPPER
int rsf_open(char * file_name, int flags, CLIENT * clnt)
{
	int  * result_1;
	open_record rfs_open_1_arg;

	rfs_open_1_arg.file_name = file_name; /* define archivo a leer */
	rfs_open_1_arg.flags = flags; /* define permisos */

	result_1 = rfs_open_1(&rfs_open_1_arg, clnt);
	if (result_1 == (int *) NULL)
		clnt_perror (clnt, "Fallo llamada open");

	return *result_1;
}


// RFS READ WRAPPER
file_data * rfs_read(int fd, CLIENT * clnt)
{

	file_data  *result_2;
	read_record  rfs_read_1_arg;

	rfs_read_1_arg.fd = fd;
	rfs_read_1_arg.count = 1024;

	result_2 = rfs_read_1(&rfs_read_1_arg, clnt);
	if (result_2 == (file_data *) NULL)
		clnt_perror (clnt, "Fallo llamada read");

	return result_2;

}

// RFS WRITE WRAPPER
int rfs_write (write_record * rfs_write_1_arg, CLIENT * clnt)
{
	file_data  * result_3;

	result_3 = rfs_write_1(rfs_write_1_arg, clnt);
	if (result_3 == (file_data *) NULL) 
	{
		clnt_perror (clnt, "call failed");
		return -1;
	}

	return 0;

}

// RFS CLOSE WRAPPER
int rfs_close(int rd, CLIENT * clnt)
{
	int  *result_4;
	int  rfs_close_1_arg = rd;
	
	result_4 = rfs_close_1(&rfs_close_1_arg, clnt);
	if (result_4 == (int *) NULL) 
		clnt_perror (clnt, "call failed");

	return *result_4;
}


void destroy(CLIENT * clnt)
{
	#ifndef	DEBUG
		clnt_destroy (clnt);
	#endif	 /* DEBUG */
}

void printBanner ()
{
 	FILE * f;
 	char caracteres[100];
 	f = fopen("banner.txt","r");
 	
 	if (f == NULL)
 		exit(1);
	
	while (feof(f) == 0)
	{
		fgets(caracteres, 100, f);
		printf( RED "%s" RESET, caracteres);
	}
	
	fclose(f);
}

void getFileName(char * file_name)
{

	while(1){
		scanf("%s", file_name);
		if (strlen(file_name) != 0)
			break;
	}
}

int leer(CLIENT * clnt)
{
	file_data * remote_file;
	char file_name[40];
	printf("Ingrese nombre de archivo a leer\n");
	getFileName(file_name);

	int rd, n;
 
	if ((rd = rsf_open(file_name, O_RDWR, clnt))< 0)
		return -1;
	
	do {
		remote_file = rfs_read(rd, clnt);

		for (n=0; n < remote_file->file_data_len; n++)
			putchar(remote_file->file_data_val[n]);
	} while (remote_file->file_data_len != 0);

	rfs_close(rd, clnt);
	getchar();
	return 0;
}

int escribir(CLIENT * clnt)
{
	char file_name[40], remote_file_name[40];
	FILE * fd;

	printf("Ingrese nombre de archivo a escribir\n");
	getFileName(file_name);

	if ((fd = fopen(file_name, "a+"))<0)
	{
		perror("Error al abrir archivo local");
		return -1;
	}

	int rd;
	printf("Ingrese el nombre del archivo remoto en el que desea volcar el contenido:\n ");
	getFileName(remote_file_name);

	if ((rd = rsf_open(remote_file_name, O_RDWR | O_CREAT | O_TRUNC, clnt))< 0)
		return -1;

	write_record  rfs_write_1_arg;
	char buff[1024];

	rfs_write_1_arg.fd = rd;

	while(fgets(buff, 1024, fd) != 0) {
		rfs_write_1_arg.buffer = buff;
		rfs_write_1_arg.count = strlen(buff);
		if(rfs_write(&rfs_write_1_arg, clnt) < 0) {
			printf("Error al escribrir archivo\n");
			return 1;
		}
	}

	fclose(fd);
	rfs_close(rd, clnt);
	getchar();
	return 0;
}

int menu()
{
	int opcion = 0;
	system("clear");
	printf(GREEN "*****************************************************\n" RESET);
	printBanner();
	printf(GREEN "*****************************************************\n" RESET);
	printf(BLUE "Leer archivo: 1\n");
	printf("Escribir archivo: 2\n");
	printf("Salir: 3\n" RESET);
	printf("\n");

	do {
		scanf("%d", &opcion);
	} while (opcion < 1 && opcion > 3);

	if(opcion == 3)
		return -1;

	return opcion;
}


int main (int argc, char *argv[])
{
	CLIENT *clnt;
	char *host;
	int opcion, result;
	/* Se deben pasar nombre de host => argc=2 */
	if (argc < 2) 
	{
		printf ("usage: %s server_host", argv[0]);
		exit (1);
	}

	host = argv[1];

	if ((clnt = rfs_1(host)) < 0)
		exit(1);

	while(1)
	{
		if ((opcion = menu()) < 0 )
			break;	

		if (opcion == 1)
		{
			if ((result = leer(clnt))<0)
				printf("No se pudo leer el archivo\n");
		} else {
			if ((result = escribir(clnt))<0)
				printf("No se pudo escribir el archivo\n");
		}	
	}
	
	destroy(clnt);
	exit (0);

}
