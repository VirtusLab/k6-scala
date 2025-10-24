# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

k6-scala is a Scala.js facade for the k6 load testing library. It provides type-safe Scala bindings for writing k6 load testing scenarios in Scala instead of JavaScript.

## Build System

This is an sbt-based Scala.js project with cross-compilation support.

### Core Build Commands

```bash
# Build and test for all Scala versions
sbt test

# Build and test for specific Scala version
sbt "++3.5.0" test

# Format code
sbt scalafmt

# Check formatting
sbt scalafmtCheck

# Package the library
sbt package

# Publish locally for development
sbt publishLocal

# Publish local dev version (sets version to "dev")
sbt publishLocalDev
```

### Scala Versions

The project cross-compiles to: 2.12.10, 2.13.16, 3.3.6, 3.5.0

### Running Examples

Each example directory has its own workflow:

**sbt examples:**
```bash
cd examples/sbt-helloworld
sbt fastOptJS  # Compile to JavaScript
sbt runK6test  # Compile and run with k6
```

**scala-cli examples:**
```bash
cd examples/scala-cli-helloworld
scala-cli --power package --js example.scala -o example.js
k6 run example.js
```

**mill examples:**
```bash
cd examples/mill-helloworld
./mill helloworld.fastOpt
k6 run out/helloworld/fastOpt.dest/main.js
```

## Architecture

### Core Package Structure

- `k6scala/src/main/scala/org/virtuslab/scalajs/k6/` - Main facade implementations
  - `K6.scala` - Core k6 functions (check, fail, group, sleep)
  - `Checkers.scala` - Type-safe check builders
  - `http/` - HTTP client facade (Http.scala, Response.scala, Request.scala, etc.)
  - `options/` - k6 configuration options (Options.scala, Scenario.scala, Stage.scala, etc.)
  - `data/` - Data handling facades
  - `timers/` - Timer functions (setTimeout, setInterval)
  - `execution/` - Execution context information
  - `encoding/` - Encoding utilities
  - `utils/` - Utilities including duration converters
  - `package.scala` - Package-level convenience functions and type aliases

- `k6scala/src/main/scala/org/virtuslab/scalajs/converters/` - Scala/JS interop converters

### Facade Pattern

This project uses Scala.js `@js.native` and `@JSImport` annotations to create facades for k6 JavaScript APIs. The pattern is:

1. Define a private `@js.native` object with `@JSImport` for the raw JS API
2. Expose idiomatic Scala APIs through wrapper functions/classes
3. Use implicit conversions and extension methods for ergonomic Scala usage

Example:
```scala
@js.native
@JSImport("k6/http", JSImport.Namespace)
private[http] object Http extends js.Object {
  def get(url: URL, params: ParamsOpt): Response = js.native
}
```

### Test Structure

Tests use ScalaTest (3.2.19). The main test is:
- `k6scala/src/test/scala/org/virtuslab/scalajs/k6/utils/DurationConvertersSpec.scala`

Run tests with: `sbt test`

### Example Test Entry Point

k6 tests must export a `main` function as the default export:
```scala
@JSExportTopLevel(JSImport.Default)
def main(): Unit = {
  // Test code here
}
```

### Key Dependencies

- Scala.js 1.18.1
- scalajs-bundler 0.21.1
- scalatest 3.2.19 (test only)

## Code Style

- Scalafmt is configured (version 3.9.4)
- Max column width: 100
- Scala 3 dialect
- Format before committing: `sbt scalafmt`

## CI Pipeline

GitHub Actions runs on PRs and pushes to main:
1. Build and test across all Scala versions (matrix build)
2. Validate formatting with scalafmtCheck

The CI build uses: `sbt -DCI=1 "++<version>" test package`
