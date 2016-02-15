#!/bin/bash

echo "[INFO] generate model,mapper,sql.xml in target dir."

cd ..

mvn mybatis-generator:generate  -Pgenerator

cd bin
