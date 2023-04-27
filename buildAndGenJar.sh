#!/bin/bash
./gradlew assembleRelease --no-daemon
cd jar
./genJar.sh #ec

