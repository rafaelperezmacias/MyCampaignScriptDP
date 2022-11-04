# Uso

## Compilar

```
javac src\models\*.java src\Mcsdp.java -d classes\
```

## Ejecutar

```
java -cp classes\ Mcsdp [ruta-archivo] [batch-size] [ruta-salida]
```

## Ejemplo

```
java -cp classes\ Mcsdp elecciones-2021.csv 2500 output
```