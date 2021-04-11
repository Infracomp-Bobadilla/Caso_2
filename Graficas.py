
# ..........................................
# ... Santiago Bobadilla y Juan José Beltrán
# .......................................... 

# ... Librerias

import os
import matplotlib.pyplot as plt

# ... Leer Archivo 

# ... 1. Obtenemos la ruta donde esta el archivo, sin incluir el nombre del archivo
rutaAbsoluta = os.path.abspath(__file__)[:-11]
    
# ... 2. Obtenemos como esta separado la ruta. Para este caso es '\' 
separador = rutaAbsoluta[-1]

# ... 3. Definirmos la ruta completa. 
ruta = (rutaAbsoluta.replace(separador, '/') + "data/resultados.txt")

# ... 4. Abrir y leer archivo

# .. I. Tres diccionarios que se van a llenar
resultadosRAM = dict()
resultadosProceso = dict()
resultadosLocalidad = dict()

# .. II. Abrir archivo
archivo = open(ruta, 'r')

# .. II. Leer cada linea
for linea in archivo:
    
    # .. III. Cada linea tiene el archivo y las fallas de ese. Registro las fallas inmediatamente. 
    actual = linea.rstrip('\n').split(",")
    fallas = int(actual[1])

    # .. IV. Dependiendo del archivo guardo el valor. 
    # .. V. Para cada uno extraigo la propiedad del archivo (eje x) y lo registro

    if "RAM" in actual[0]:
        
        if "." not in actual[0][4:6]:
            esc = int(actual[0][4:6])
        else: 
            esc = int(actual[0][4:5])

        resultadosRAM[esc] = fallas

    elif "Proceso" in actual[0]:

        if "." not in actual[0][8:11]:
            esc = int(actual[0][8:11])
        else:
            esc = int(actual[0][8:10])

        resultadosProceso[esc] = fallas

    elif "Localidad" in actual[0]:
        
        if "." not in actual[0][10:12]:
            esc = int(actual[0][10:12])
        else: 
            esc = int(actual[0][10:11])

        resultadosLocalidad[esc] = fallas

# ................................................ GRAFICAS

# .. 1. Generar una lista para las x y otras para las y
# .. 2. Llenarlas con base al diccionario cargado ordenado por llave
# .. 3. Poner titulos y graficar

# ...................................................
# ......... Número de marcos de página en memoria RAM
# ...................................................

xRam = []
yRam = []

for k,v in sorted(resultadosRAM.items()):
    xRam.append(k)
    yRam.append(v)

plt.xlabel("Número de marcos de página en memoria RAM", size = 16,)
plt.ylabel("Número de Fallas", size = 16)
plt.title("Número de marcos de página en memoria RAM vs Número de Fallas (Nivel Localidad 0.5)", 
          fontdict={'family': 'serif', 
                    'color' : 'darkblue',
                    'weight': 'bold',
                    'size': 18})
plt.plot(xRam, yRam)
plt.show()   

# .......................................
# ......... Número de páginas del proceso
# .......................................

xProceso = []
yProceso = []

for k,v in sorted(resultadosProceso.items()):
    xProceso.append(k)
    yProceso.append(v)

plt.xlabel("Número de páginas del proceso", size = 16,)
plt.ylabel("Número de Fallas", size = 16)
plt.title("Número de páginas del proceso vs Número de Fallas (Nivel Localidad 0.5)", 
          fontdict={'family': 'serif', 
                    'color' : 'darkblue',
                    'weight': 'bold',
                    'size': 18})
plt.plot(xProceso, yProceso)
plt.show()  

# ...................................................
# ......... Número de marcos de página en memoria RAM
# ...................................................

xLocalidad = []
yLocalidad = []

for k,v in sorted(resultadosLocalidad.items()):
    xLocalidad.append(k)
    yLocalidad.append(v)

plt.xlabel("Nivel de localidad", size = 16,)
plt.ylabel("Número de Fallas", size = 16)
plt.title("Nivel de localidad vs Número de Fallas (RAM = 8, PROCESO = 40)", 
          fontdict={'family': 'serif', 
                    'color' : 'darkblue',
                    'weight': 'bold',
                    'size': 18})
plt.plot(xLocalidad, yLocalidad)
plt.show()  

