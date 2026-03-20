## Contributing to k6-scala

Thank you for your interest in contributing to `k6-scala` – a Scala.js facade for the k6
load testing tool.

Contributions of all kinds are welcome: bug reports, documentation improvements,
new facades, examples, and tooling.

### How to Get Started

- **Discuss larger changes**: For non-trivial changes (new modules, behavior changes),
  please open a GitHub issue first to align on direction.
- **Small fixes**: For small documentation or code fixes, feel free to open a pull request
  directly.

### Development Setup

- **Prerequisites**:
  - Java (suitable for running sbt)
  - `sbt`
  - `k6` installed and available on your `PATH` (for running example scripts)

- **Clone the repository**:

```bash
git clone https://github.com/VirtusLab/k6-scala.git
cd k6-scala
```

- **Compile and test**:

```bash
sbt test
```

- **Format and lint**:

```bash
sbt scalafmtAll
```

### Running Examples

Examples live in the `examples/` directory. Typical flows:

- **sbt hello world**:

```bash
cd examples/sbt-helloworld
sbt fastOptJS
sbt runK6test
```

- **scala-cli hello world**:

```bash
cd examples/scala-cli-helloworld
scala-cli --power package --js example.scala -o example.js
k6 run example.js
```

- **mill hello world**:

```bash
cd examples/mill-helloworld
./mill helloworld.fastOpt
k6 run out/helloworld/fastOpt.dest/main.js
```

API-level examples are under `examples/api-examples/` and are wired into CI. See the
README for the list of available examples and how to run them.

### Coding Standards

- Follow the existing Scala style (Scalafmt is enforced).
- Prefer pure, small, composable functions and immutable data structures.
- Keep public APIs minimal and aligned with the k6 JavaScript API.

Before submitting a PR, please ensure:

- `sbt test` passes
- `sbt scalafmtAll` has been run

### License

By contributing to this project, you agree that your contributions will be licensed
under the Apache License, Version 2.0, as specified in the `LICENSE` file.
