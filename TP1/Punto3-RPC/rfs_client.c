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

void
rfs_1(char *host, char *file_name,  int opcion)
{

	CLIENT *clnt;
	int  *result_1;
	open_record  rfs_open_1_arg;
	file_data  *result_2;
	read_record  rfs_read_1_arg;
	int  *result_3;
	write_record  rfs_write_1_arg;
	int  *result_4;
	int  rfs_close_1_arg;
	int fd, n;
	const int MAX_BUFFER = 1024;
	FILE *lfd;


#ifndef	DEBUG
	clnt = clnt_create (host, RFS, RFS_VERS_1, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */

	// RFS OPEN
	rfs_open_1_arg.file_name = file_name; /* define archivo a leer */
	rfs_open_1_arg.flags = O_RDWR; /* define permisos */
	result_1 = rfs_open_1(&rfs_open_1_arg, clnt);
	if (result_1 == (int *) NULL) {
		clnt_perror (clnt, "Fallo llamada open");
	}
	fd = *result_1;
	if (fd == -1) {
		printf("Error al abrir el archivo\n"); return;
	}
	
	if (opcion == 1)
	{
		// RFS READ
		rfs_read_1_arg.fd = fd;
		rfs_read_1_arg.count = 1024;

		do {
			result_2 = rfs_read_1(&rfs_read_1_arg, clnt);
			if (result_2 == (file_data *) NULL) {
				clnt_perror (clnt, "Fallo llamada read");
			}
			for (n=0; n < result_2->file_data_len; ++n)
				putchar(result_2->file_data_val[n]);
		} while (result_2->file_data_len == rfs_read_1_arg.count);
		putchar('\n');
	} else {
		// RFS WRITE
		char nuevo_file_name[MAX_BUFFER];

		printf("Elija el archivo local en el que desea volcar el contenido:\n ");
		scanf("%s", nuevo_file_name);

		// Abre el archivo local.
		lfd = fopen(nuevo_file_name, "a");
		rfs_write_1_arg.fd=lfd;
		result_3 = rfs_write_1(&rfs_write_1_arg, clnt);
		if (result_3 == (file_data *) NULL)
			clnt_perror (clnt, "Falló llamada WRITE");

		// Cierra el archivo local.
		fclose(lfd);
	}

#ifndef	DEBUG
	clnt_destroy (clnt);
#endif	 /* DEBUG */
}

int menu(int opcion, char * host)
{
	/* nombre del host remoto */
	char file_name[40]; /* nombre del archivo a leer */
	
	if (opcion != 1 && opcion != 2)
		return -1;

	switch (opcion)
	{
		case 1: 
			printf("Ingrese nombre de archivo a leer\n");
			break;
		default:
			printf("Ingrese nombre de archivo a escribir\n");
			break;
	}
	while(1){
		scanf("%s", file_name);
        if (strlen(file_name) != 0)
		{
            rfs_1 (host, file_name, opcion);
			break;
		}
        getchar();
	}
}

int printMenu(char * host)
{
	int result, opcion = 0;
	printf("==================\n");
	printf("Remote File System\n");
	printf("==================\n");
	printf("Leer archivo: 1\n");
	printf("Escribir archivo: 2\n");
	printf("Salir: 3\n");

	do {
		scanf("%d", &opcion);
	} while (opcion < 1 && opcion > 3);


	if(opcion == 3)
		return -1;
	else 
	{
		if ((result = menu(opcion, host))< 0)
			return -1;
		return 0;
	}

}


int main (int argc, char *argv[])
{
	char *host;
	int result;
	/* Se deben pasar nombre de host y de archivo => argc=3 */
	if (argc < 2) {
		printf ("usage: %s server_host", argv[0]);
		exit (1);
	}
	host = argv[1];

	while(1)
	{
		if ((result = printMenu(host)) < 0 )
			break;
	}
	
	exit (0);
}
