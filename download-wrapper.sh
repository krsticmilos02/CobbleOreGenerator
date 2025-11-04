#!/bin/bash

# Script to download the Gradle wrapper JAR file
# Run this script if gradle-wrapper.jar is missing

WRAPPER_JAR="gradle/wrapper/gradle-wrapper.jar"
WRAPPER_URL="https://raw.githubusercontent.com/gradle/gradle/v8.8.0/gradle/wrapper/gradle-wrapper.jar"

echo "Downloading Gradle wrapper JAR..."

if command -v curl &> /dev/null; then
    curl -L -o "$WRAPPER_JAR" "$WRAPPER_URL"
elif command -v wget &> /dev/null; then
    wget -O "$WRAPPER_JAR" "$WRAPPER_URL"
else
    echo "Error: Neither curl nor wget is available."
    echo "Please install curl or wget and try again."
    echo ""
    echo "Alternative: If you have Gradle installed, run:"
    echo "  gradle wrapper"
    exit 1
fi

if [ -f "$WRAPPER_JAR" ]; then
    echo "Gradle wrapper JAR downloaded successfully!"
    echo "You can now run: ./gradlew build"
else
    echo "Failed to download Gradle wrapper JAR."
    echo ""
    echo "Alternative methods:"
    echo "1. Install Gradle and run: gradle wrapper"
    echo "2. Download manually from: $WRAPPER_URL"
    echo "   and place it in: $WRAPPER_JAR"
fi
