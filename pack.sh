#!/bin/bash

mkdir temp
cp -rp target/dependency-jars temp/
cp -r target/*.jar* temp/
mkdir -p temp/serenityBDD-aws-device-farm-template
cp -rp src temp/serenityBDD-aws-device-farm-template
cp pom.xml temp/serenityBDD-aws-device-farm-template
cd temp
zip -r test_package.zip *
cp test_package.zip ../target