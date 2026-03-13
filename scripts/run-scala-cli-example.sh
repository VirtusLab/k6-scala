#!/usr/bin/env bash

set -euo pipefail

if [ "$#" -ne 1 ]; then
  echo "Usage: $0 <example_name_without_extension>"
  exit 1
fi

EXAMPLE_NAME="$1"

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

pushd "$REPO_ROOT/examples/api-examples" > /dev/null

scala-cli --power package "${EXAMPLE_NAME}.scala" \
  --js \
  --js-emit-source-maps \
  --js-module-kind esmodule \
  --js-no-opt \
  -f \
  -o "${EXAMPLE_NAME}.js"

k6 run "${EXAMPLE_NAME}.js"

popd > /dev/null

