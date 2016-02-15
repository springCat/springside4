@echo off
echo [INFO] Re-create the schema and provision the sample data.

cd %~dp0
cd ..

mvn mybatis-generator:generate  -Pgenerator

cd bin
pause