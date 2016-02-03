#!/bin/bash

echo "[INFO] Re-create the schema and provision the sample data."

cd ..

call mvn compile flyway:migrate

cd bin
