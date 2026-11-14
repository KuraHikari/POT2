@echo off
setlocal
chcp 65001 >nul

if not exist "%~dp0src\Main.java" (
    echo Falta src\Main.java. Extrae todo el ZIP y abre POT2.bat desde esa carpeta.
    pause
    exit /b 1
)

where java >nul 2>&1
if errorlevel 1 (
    echo Instala Java 25 ^(JDK 25^) y comprueba que el comando java este disponible.
    pause
    exit /b 1
)

java -Dfile.encoding=UTF-8 "%~dp0src\Main.java"
set "resultado=%ERRORLEVEL%"
if not "%resultado%"=="0" echo No se pudo ejecutar el programa. Comprueba que tienes instalado Java 25 ^(JDK 25^).
pause
exit /b %resultado%
