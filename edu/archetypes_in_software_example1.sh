#!/usr/bin/env bash
set -euo pipefail

CP="openEHR_ADL.jar:./lib/*:${GROOVY_HOME:-}/lib/*"
java -cp "$CP" com.cabolabs.openehr.archetypes_in_software.Example1


#java -cp "$GROOVY_HOME/lib/*:./lib/build/libs/openEHR-ADL-0.2.jar" com.cabolabs.openehr.archetypes_in_software.Example1
