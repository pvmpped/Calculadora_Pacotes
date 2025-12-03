@echo off
echo ====================================
echo   CALCULADORA DE PACOTES JAVA
echo ====================================
echo.

echo [1] Compilando o projeto...
javac -d bin src/entities/*.java src/services/*.java src/main/Main.java

if %errorlevel% neq 0 (
    echo.
    echo ERRO na compilacao!
    echo Verifique os arquivos Java.
    pause
    exit /b 1
)

echo [2] Executando programa...
echo ====================================
java -cp bin main.Main

echo.
echo ====================================
echo Programa encerrado.
pause