# Scala.js Facade for k6 (`k6-scala`)

[![Build Status](placeholder-for-build-badge)](placeholder-for-build-url)
[![Maven Central](placeholder-for-maven-badge)](placeholder-for-maven-url)

A type-safe Scala.js facade for the [k6 load testing tool](https://k6.io/).
This library enables writing k6 load tests using Scala.js.

---

## Features

- **Type-safe API**: Full Scala.js facades for core k6 modules
- **Build Tool Support**: Integration with sbt, scala-cli, and mill
- **Production Ready**: Used in real-world load testing scenarios

## Quick Start

Add the dependency to your project:

```scala
libraryDependencies += "com.example" %%% "k6-scala" % "latest.version"
```

### Supported k6 Modules

- ✓ [`k6`](https://k6.io/docs/javascript-api/k6/) - Core module
- ✓ [`k6/http`](https://k6.io/docs/javascript-api/k6-http/) - HTTP client
- ✓ [`k6/data`](https://k6.io/docs/javascript-api/k6-data/) - Data handling
- ✓ [`k6/options`](https://k6.io/docs/using-k6/k6-options/) - Test configuration
- ✓ [`k6/timers`](https://k6.io/docs/using-k6/k6-timers/) - Timing utilities

### Basic Examples

See the `examples/` directory for complete examples using different build tools:
- [sbt example](examples/sbt-helloworld)
- [scala-cli example](examples/scala-cli-helloworld)
- [mill example](examples/mill-helloworld)
- [k6 api example](examples/api-examples)

### Build Tool Integration
Examples showing how to use `k6-scala` with:
- [sbt](https://www.scala-sbt.org/)
- [scala-cli](https://scala-cli.virtuslab.org/)
- [mill](https://com-lihaoyi.github.io/mill/)
- [gradle](https://gradle.org/)

### CI Setup
- Basic GitHub Actions workflows:
  - Build checks
  - Facade validation
  - Example project tests

---
## Development Status

This is a beta release. While the core functionality is stable, some features might change as we gather user feedback.

### Planned Features
- Additional k6 module support
- Enhanced type safety
- More comprehensive examples

## Contributing

Contributions are welcome! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

## License

[License information]

## Acknowledgments

- [k6](https://k6.io/) - The excellent load testing tool
- [Scala.js](https://www.scala-js.org/) - The Scala to JavaScript compiler
