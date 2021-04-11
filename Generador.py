# ..........................................
# ... Santiago Bobadilla y Juan José Beltrán
# .......................................... 

# .. Librerias

import random
import os

# ...................................................................................................................
# ........................................... Función generadora de secuencias para el algoritmo de fallos de página.
# ...................................................................................................................

# ............................... Parametros:

# ... Número de marcos de página en memoria RAM que el sistema le asigna al proceso
# ... Número de páginas del proceso
# ... Nivel de localidad
# ... Secuencia de referencias a páginas del proceso
# ... Nombre Archivo: 1. Marco de Página, 2. Página del proceso, 3. Nivel de localidad

# ............................... Resultados: 

# ... Txt con la secuencia y la configuración

def generarSecuenciasFallos (marcosPag, porcesPag, nivLocalidad, nombre):

    # .................................................................................................................. Algoritmo:

    # ... 1. Definir los marcos de página, páginas del proceso y cuanto va a ser el nivel de localidad. 
    numPaginasRAM = marcosPag
    numPaginasProceso = porcesPag
    nivelLocalidad = nivLocalidad
    secuencia = list()

    # ... 2. Con base al nivel de localidad generar dos listas. Una para los que se pueden repetir y otra para los que no. 
    puedenRepetirse = list()
    noPuedenRepetirse = list()

    # ... 3. Llenar los dos listas con las páginas del proceso de manera aleatoria. 

    # ... I. Toca llevar registro de cuando las paginas del proceso ya fueron asignadas a uno de los dos arreglos.
    marcarNumeros = {v : 0 for v in range(numPaginasProceso)}

    # ... II. Asignar cada una de las paginas hasta que esten todas.
    marcados = 0
    while (marcados != numPaginasProceso):

        # ... III. Genero una pagina alazar de las posibles.
        x_i = random.randint(0, numPaginasProceso-1)

        # ... IV. Si no ha sido asignada la asigno. 
        if marcarNumeros[x_i] == 0:

            # ... V. Se comprueba que se respete el nivel de localidad de las que van a ser repetidas. 
            if len(puedenRepetirse) < nivelLocalidad*numPaginasProceso:
                puedenRepetirse.append(x_i)
            else:
                noPuedenRepetirse.append(x_i)

            # ... VI. Se registra que ya fue asignada.
            marcarNumeros[x_i] = 1

        # ... VII. Verificar si parar o seguir.
        marcados =  sum(marcarNumeros[v] for v in marcarNumeros)

    # ... 4. Generar la secuencia hasta tener 1000 datos
    contador = 0
    while contador < 1000:

        # ... I. Generar un número aleatorio de las páginas del proceso.
        x_i = random.randint(0, numPaginasProceso-1)

        # ... II. Si el número se puede repetir se añade
        if x_i in puedenRepetirse:
            secuencia.append(x_i)
            contador = contador + 1
        else: 
            # ... III. Si el número no se puede repetir, se revisa que no este para añadirlo. ~ d.l.c se descarta
            if x_i not in secuencia:
                secuencia.append(x_i)
                contador = contador + 1

    # .................................................................................................................. TXT:

    # ... 1. Obtenemos la ruta donde esta el archivo, sin incluir el nombre del archivo
    rutaAbsoluta = os.path.abspath(__file__)[:-12]
    
    # ... 2. Obtenemos como esta separado la ruta. Para este caso es '\' 
    separador = rutaAbsoluta[-1]

    # ... 3. Definirmos el nombre del archivo. 
    if nombre == 1: 
        nombreArchivo = "RAM_" + str(numPaginasRAM) + ".txt"
    elif nombre == 2:
        nombreArchivo = "Proceso_" + str(numPaginasProceso) + ".txt"
    else:
        nombreArchivo = "Localidad_" + str(round(nivelLocalidad*100)) + ".txt"

    # ... 4. Completamos la ruta de salida del archivo
    ruta = (rutaAbsoluta.replace(separador, '/') + "data/escenarios/" + nombreArchivo)

    # ... 5. Escribimos el archivo
    try:
    
        # ... I. Abrir el archivo
        ff = open(ruta, 'w')

        # ... II. Generamos las configuraciones 
        ff.write(str(numPaginasRAM) + '\n')
        ff.write(str(numPaginasProceso) + '\n')
        ff.write(str(round(nivelLocalidad*100)) + ".0" + '\n')

        # ... III. Escribimos la secuencia
        for i in secuencia:
            ff.write(str(i) + '\n')

        # ... IV. Cerramos el archivo
        ff.close()

        # ... V. Reportamos que se genero con exito
        print('EXITO ~ Se genero ' + nombreArchivo)

    except:
        
        # ... ERROR
        print('ERROR ~ No se logro generar el archivo txt.')
    
    
# ..............................................................................
# ........................................... Generación de escenarios de prueba
# ..............................................................................

# ...  VARIACIÓN: Número de marcos de página en memoria RAM que el sistema le asigna al proceso

# ... I. Se definicio una politica donde las paginas en proceso serán el máx de Páginas en RAM que se van a evaluar. 
# ... II. Se definicio un nivel de localidad neutro: 0.5
numPaginasRAM = 100
numPaginasProceso = numPaginasRAM
nivelLocalidad = 0.5

for i in range(1, numPaginasRAM):
    generarSecuenciasFallos(i, numPaginasProceso, nivelLocalidad, 1)

print("----------------------")

# ...  VARIACIÓN: Número de páginas del proceso

# ... I. Se definio una politica donde en mínimo núm de pág de proceso era las misma de RAM y un tope arbitrario. 
# ... II. Se definicio un nivel de localidad neutro: 0.5

numPaginasRAM = 10
numPaginasProceso = 110
nivelLocalidad = 0.5

for i in range(numPaginasRAM ,numPaginasProceso):
    generarSecuenciasFallos(numPaginasRAM, i, nivelLocalidad, 2)

print("----------------------")

# ...  VARIACIÓN: Nivel de localidad

# ... I. Se definio una politica de iteración de 0.05
# ... II. Páginas arbitrarias

numPaginasRAM = 8
numPaginasProceso = 40
nivelLocalidad = list(range(5, 100, 5))

for i in nivelLocalidad:
    generarSecuenciasFallos(numPaginasRAM, numPaginasProceso, i/100, 3)


print("----------------------")