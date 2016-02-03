@echo off
echo [INFO] Re-create the schema and provision the sample data.

cd %~dp0
cd ..

call mvn compile flyway:migrate

cd bin
pause