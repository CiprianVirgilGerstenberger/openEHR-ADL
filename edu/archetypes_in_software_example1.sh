#!/usr/bin/env bash

set -euo pipefail

CP="./lib/build/libs/openEHR-ADL-0.2.jar:/usr/local/sdkman/candidates/groovy/current/lib/*"

# Require ADL file path as first argument
if [[ $# -lt 1 ]]; then
  echo "Usage: $0 <path-to-archetype.adl>" >&2
  exit 1
fi

ADL_FILE="$1"
if [[ ! -f "$ADL_FILE" ]]; then
  echo "ERROR: ADL file not found: $ADL_FILE" >&2
  exit 2
fi

# New call: pass the ADL file path through
groovy -cp "$CP" \
    -e 'com.cabolabs.openehr.archetypes_in_software.Example1.main(args)' \
    "$ADL_FILE"


#groovy \
#  -cp "$CP" \
#  com.cabolabs.openehr.archetypes_in_software.Example1 \
#  "$ADL_FILE"

