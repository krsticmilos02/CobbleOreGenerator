@echo off
REM Script to download the Gradle wrapper JAR file
REM Run this script if gradle-wrapper.jar is missing

set WRAPPER_JAR=gradle\wrapper\gradle-wrapper.jar
set WRAPPER_URL=https://raw.githubusercontent.com/gradle/gradle/v8.8.0/gradle/wrapper/gradle-wrapper.jar

echo Downloading Gradle wrapper JAR...

powershell -Command "& {Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile '%WRAPPER_JAR%'}"

if exist "%WRAPPER_JAR%" (
    echo Gradle wrapper JAR downloaded successfully!
    echo You can now run: gradlew.bat build
) else (
    echo Failed to download Gradle wrapper JAR.
    echo.
    echo Alternative methods:
    echo 1. Install Gradle and run: gradle wrapper
    echo 2. Download manually from: %WRAPPER_URL%
    echo    and place it in: %WRAPPER_JAR%
)

pause
