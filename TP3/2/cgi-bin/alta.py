#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import cgi
import csv

form = cgi.FieldStorage()
nombre = form.getvalue('nombre')
legajo = form.getvalue('legajo')
sexo = form.getvalue('sexo')
edad = form.getvalue('edad')
password = form.getvalue('password')
with open('/tmp/bd.csv', 'a') as csvfile:
    fieldnames = ['nombre', 'legajo', 'sexo', 'edad', 'password']
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames) #DictWiter es un escritor normal como writer pero que asigna diccionarios
    writer.writerow({'nombre': nombre, 'legajo': legajo, 'sexo':sexo, 'edad':edad, 'password':password})

return_str = """Content-type: text/html\n\n
<html><head><title>CGI</title></head>
<body>
<h1>hola mundo</h1>
</body>
</html>""" 
print(return_str)

