@echo off
setlocal
chcp 65001 >nul

where java >nul 2>&1
if errorlevel 1 (
    echo Instala Java 25 ^(JDK 25^) y comprueba que el comando java este disponible.
    pause
    exit /b 1
)

java -Dfile.encoding=UTF-8 "%~dp0src\Main.java"
set "resultado=%ERRORLEVEL%"
if not "%resultado%"=="0" echo No se pudo ejecutar el programa. Comprueba que tienes Java 25 y que has extraido todo el ZIP.
pause
exit /b %resultado%
