#!/usr/bin/env sh
# Simple Maven fallback script.
# Tries to use system 'mvn' if available, forwarding all arguments.
# This is a fallback for environments where the Maven Wrapper jar is not present or cannot be downloaded.
if command -v mvn >/dev/null 2>&1; then
  exec mvn "$@"
else
  echo "Error: 'mvn' command not found. Please install Apache Maven or use ./mvnw with Java available." >&2
  exit 1
fi
