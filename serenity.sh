#!/bin/bash

display_usage() {
  echo "This script must be run with at least 3 arguments if task is run_tests"
 	echo -e "Usage:" $0 "[run_tests] [local|dev|prod] [iOS|android|iOS_simulator|android_emulator]"
	}

if [  $# -lt 1 ]
then
		display_usage
		exit 1
fi

TASK=$1
ENV=$2
PLATFORM=$3


case $TASK in
    run_tests)
        if [  $# -lt 3 ]
        then
		      display_usage
		      exit 1
        fi

        mvn -U -DskipTests=true clean install
        case "$PLATFORM" in
          iOS)
            mvn clean verify -Dwebdriver.driver=appium -Dcucumber.filter.tags="@app" -Denvironment=$ENV -Dproperties=serenity.ios.real.devices.properties
            ;;
          iOS_simulator)
            mvn clean verify -Dwebdriver.driver=appium -Dcucumber.filter.tags="@app" -Denvironment=$ENV -Dproperties=serenity.ios.properties
            ;;
          android)
            mvn clean verify -Dwebdriver.driver=appium  -Dcucumber.filter.tags="@app" -Denvironment=$ENV -Dproperties=serenity.android.real.devices.properties
            ;;
          android_emulator)
            mvn clean verify -Dwebdriver.driver=appium  -Dcucumber.filter.tags="@app" -Denvironment=$ENV -Dproperties=serenity.properties
            ;;
          *)
          display_usage
          exit 1
          ;;
        esac
        ;;
      exit 1
      ;;
esac


