echo off
set JAVA_HOME=C:\Program Files\Java\jdk-15
set PATH=%PATH%;C:\Program Files\Java\jdk-15\lib

echo on
gradlew.bat %1 %2
