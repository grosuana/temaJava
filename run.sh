#!/bin/bash
rm -rf ./outputFolder/
rm -rf output*.png
javac *.java
java PrewittOperator "$@"
