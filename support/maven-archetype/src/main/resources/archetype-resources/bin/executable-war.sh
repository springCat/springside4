#!/bin/bash

echo "[INFO] Packaging a executable war."

cd ..

set MAVEN_OPTS=%MAVEN_OPTS%

mvn clean package -Pstandalone

echo "[INFO] Executable war had been packaged as target/xxx.standalone.war"

cd bin
