#!/usr/bin/env bash
set -euo pipefail

CP="$GROOVY_HOME/lib/*:./lib/build/libs/openEHR-ADL-0.2.jar"
java -cp "$CP" com.cabolabs.openehr.archetypes_in_software.Example1
