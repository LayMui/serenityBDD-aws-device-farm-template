## Instructions
### Dependencies and Prerequisite
```
Java 11    
Maven 3.6.X or above 
```
### Add Serenity dependencies to the pom.xml
```
https://mvnrepository.com/artifact/net.serenity-bdd/serenity-core
https://mvnrepository.com/artifact/net.serenity-bdd/serenity-junit
https://mvnrepository.com/artifact/net.serenity-bdd/serenity-screenplay
https://mvnrepository.com/artifact/net.serenity-bdd/serenity-rest-assured
```

mvn clean install -DskipTests=true
```
### serenity.sh script
The serenity.sh script is created to simplify the test execution:      
Run ./serenity.sh with arguments 
To run the test
```
./serenity.sh [run_test] [local|dev|staging] [iOS|android|iOS_Simulator|android_emulator]
``` 
run_tests: To run the tests    
the second arg is the environment to run the test
The third and last argument is platform to run the test
iOS: on ios real device
android: on andriod real device
iOS_Simulator: ios simulator
andrioid_emulator: android emulator
Note: 
1. Change the appium.app in the serenity properties accordingly  
2. Since appium is started separately in aws device farm, when you run it locally  
you will need to start appium first before running the test  

3. Add a @app to the specific test scenarios you want to test
## AWS Device Farm (this is using java command which is replaced by using maven, see serenity.yml)
https://docs.aws.amazon.com/devicefarm/latest/developerguide/test-types-appium.html#test-types-appium-prepare
Replace the following lines at aws device farm yml file
 - java -Dappium.screenshots.dir=$DEVICEFARM_SCREENSHOT_PATH org.testng.TestNG -junit -testjar *-tests.jar -d $DEVICEFARM_LOG_DIR/test-output -verbose 10
with 
- mkdir temp_jar
- unzip *-tests.jar -d temp_jar
- export CLASSPATH=$CLASSPATH:$DEVICEFARM_TEST_PACKAGE_PATH/temp_jar
- java -Dcucumber.filter.tags=@test -Dcucumber.features=classpath:features -Dcucumber.glue=classpath:demo.stepdefinitions -Dwebdriver.driver=appium -Dappium.screenshots.dir=$DEVICEFARM_SCREENSHOT_PATH -Dappium.autoAcceptAlerts=true -Dappium.platformName=iOS -Dappium.app=$DEVICEFARM_APP_PATH org.testng.TestNG -junit -testjar *-tests.jar -d $DEVICEFARM_LOG_DIR/test-output -verbose 10

4. serenity.yml is the test spec yml file configured/setup to run on AWS device farm environment
it's a customised off from the default test spec file

5. If you are using java command 
You need to create the assembly/zip.yml, 
(not required if you use maven command)

6. using maven command on aws device farm means you have to download the dependencies configured in pom.xml

7. run the pack.sh to package the test artifact to upload on aws device farm

