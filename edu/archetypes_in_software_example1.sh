#!/usr/bin/env bash
set -euo pipefail

CP="./lib/build/libs/openEHR-ADL-0.2.jar:/usr/local/sdkman/candidates/groovy/current/lib/*"
java -cp "$CP" com.cabolabs.openehr.archetypes_in_software.Example1

