# Scala.js facade for k6 (`k6-scala`)

[![CI](https://github.com/VirtusLab/k6-scala/actions/workflows/ci.yml/badge.svg)](https://github.com/VirtusLab/k6-scala/actions/workflows/ci.yml)
[![Maven Central](https://img.shields.io/maven-central/v/org.virtuslab/k6-scala_3)](https://search.maven.org/artifact/org.virtuslab/k6-scala_3)

Type-safe [Scala.js](https://www.scala-js.org/) bindings for writing [k6](https://k6.io/) load tests in Scala instead of JavaScript.

---

## Features

- **Typed facades** for core k6 modules (`k6/http`, `k6/metrics`, `k6/html`, gRPC, WebSockets, browser, and more)
- **Cross-built** for Scala 2.12, 2.13, and 3.x
- **Examples** for sbt, Scala CLI, Mill, and Gradle
- **`k6-jslib`** — optional artifact with ES-module facades over the [k6 jslib](https://jslib.k6.io/) CDN (e.g. `k6-utils` 1.2.0, httpx session, k6chaijs-style assertions)

---

## Installation

**sbt** (Scala.js):

```scala
libraryDependencies += "org.virtuslab" %%% "k6-scala" % "<version>"
```

**Optional jslib helpers** (ES module output; pair with `k6-scala`):

```scala
libraryDependencies += "org.virtuslab" %%% "k6-jslib" % "<version>"
```

Use the same `<version>` for both modules. For local development, `sbt publishLocalDev` publishes version `dev` for use in Scala CLI examples.

---

## Getting started

### 1. Prerequisites

- JDK 17+ (CI uses Temurin 21)
- [k6](https://k6.io/docs/get-started/installation/) on your `PATH`
- A Scala.js toolchain (sbt, Scala CLI, Mill, …)

### 2. Minimal script (Scala CLI)

From the repository root after `sbt publishLocalDev`:

```bash
scala-cli --power package examples/scala-cli-helloworld/example.scala \
  --js --js-emit-source-maps \
  --js-module-kind commonjs -f --js-no-opt \
  -o example.js
k6 run example.js
```

Use **`--js-module-kind commonjs`** for scripts that rely on k6 modules that only expose a **default export** (for example `k6/secrets`). Most other facades work with **ES modules** as well.

### 3. Default export

k6 expects your script to expose a default-exported `main` (and optionally `options`). In Scala.js:

```scala
import scala.scalajs.js.annotation._

@JSExportTopLevel(JSImport.Default)
def main(): Unit = {
  /* scenario */
}
```

See [`examples/`](examples/) and [`examples/api-examples/`](examples/api-examples/) for full samples.

---

## API coverage

The tables link to the official k6 JavaScript API docs. Status **Supported** means this repository provides Scala.js facades or pure Scala helpers intended for use with the matching k6 release line.

### Core `k6scala` artifact (`org.virtuslab::k6-scala`)

| Scala / topic                               | k6 JS API (reference)                                                                                       | Notes                                                        |
| ------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| Core (`check`, `fail`, `group`, `sleep`, …) | [k6 module](https://grafana.com/docs/k6/latest/javascript-api/k6/)                                          | `org.virtuslab.scalajs.k6`                                   |
| HTTP                                        | [k6/http](https://grafana.com/docs/k6/latest/javascript-api/k6-http/)                                       | Methods, `Params`, `Response`, cookies, batch, `FormData`, … |
| Options & scenarios                         | [Options](https://grafana.com/docs/k6/latest/using-k6/k6-options/reference/)                                | `Options`, `Scenario`, executors, thresholds                 |
| Execution                                   | [k6/execution](https://grafana.com/docs/k6/latest/javascript-api/k6-execution/)                             |                                                              |
| Data                                        | [k6/data](https://grafana.com/docs/k6/latest/javascript-api/k6-data/)                                       | `SharedArray`                                                |
| Encoding                                    | [k6/encoding](https://grafana.com/docs/k6/latest/javascript-api/k6-encoding/)                               | Base64                                                       |
| Timers                                      | [k6/timers](https://grafana.com/docs/k6/latest/javascript-api/k6-timers/)                                   |                                                              |
| Metrics                                     | [k6/metrics](https://grafana.com/docs/k6/latest/javascript-api/k6-metrics/)                                 | Counter, Gauge, Rate, Trend                                  |
| Crypto                                      | [k6/crypto](https://grafana.com/docs/k6/latest/javascript-api/k6-crypto/)                                   | Hash, HMAC, `Hasher`                                         |
| Web Crypto                                  | [Web Crypto API](https://developer.mozilla.org/en-US/docs/Web/API/Web_Crypto_API)                           | `subtle`, `getRandomValues`, …                               |
| WebSocket (legacy)                          | [k6/ws](https://grafana.com/docs/k6/latest/javascript-api/k6-ws/)                                           | `connect`, `Socket`                                          |
| WebSocket (experimental)                    | [k6/experimental/websockets](https://grafana.com/docs/k6/latest/javascript-api/k6-experimental/websockets/) | Browser-like API                                             |
| gRPC                                        | [k6/net/grpc](https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/)                               | Client, streaming                                            |
| HTML                                        | [k6/html](https://grafana.com/docs/k6/latest/javascript-api/k6-html/)                                       | `parseHTML`, `Selection`, `Element`                          |
| Browser                                     | [k6/browser](https://grafana.com/docs/k6/latest/javascript-api/k6-browser/)                                 | `browser`, `Page`, `Locator`, …                              |
| Secrets                                     | [k6/secrets](https://grafana.com/docs/k6/latest/javascript-api/k6-secrets/)                                 | **Requires CommonJS** module kind (see below)                |

### `k6-jslib` artifact (`org.virtuslab::k6-jslib`)

Facades are compiled as **ES modules** and use URL imports from [jslib.k6.io](https://jslib.k6.io/) (pinned to **k6-utils 1.2.0** unless noted in source).

| Area                  | k6 / jslib reference                                                          | Notes                          |
| --------------------- | ----------------------------------------------------------------------------- | ------------------------------ |
| Random & string utils | [k6-utils](https://jslib.k6.io/k6-utils/1.2.0/)                               | `RandomUtils`, `StringUtils`   |
| HTTP session (httpx)  | [jslib httpx](https://grafana.com/docs/k6/latest/javascript-api/jslib/httpx/) | `Session`                      |
| BDD-style assertions  | [k6chaijs](https://grafana.com/docs/k6/latest/javascript-api/jslib/k6chaijs/) | `ChaiJS`, `expect`, `describe` |

---

## Known limitations

### `k6/secrets` and CommonJS

The `k6/secrets` facade is only reliable when Scala.js emits **CommonJS**. k6’s ESM path does not always expose `default` on namespace imports the way Scala.js expects. See [examples/api-examples/k6-secrets.scala](examples/api-examples/k6-secrets.scala) and [CLAUDE.md](CLAUDE.md).

### gRPC streaming example

[`k6-grpc-streaming.scala`](examples/api-examples/k6-grpc-streaming.scala) expects a running gRPC server (e.g. k6’s RouteGuide demo). It is **not** run in CI by default.

---

## Documentation

- **User guide**: this README and [CONTRIBUTING.md](CONTRIBUTING.md)
- **ScalaDoc**: run `sbt k6scala/doc` and `sbt k6-jslib/doc`, then open `target/scala-3.x.x/api/index.html` under each project

---

## Development

```bash
sbt +test                    # test all Scala versions
sbt scalafmtCheckAll         # formatting
sbt "k6scala/doc" "k6-jslib/doc"
```

---

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

---

## License

Apache License 2.0 — see [LICENSE](LICENSE).

---

## Acknowledgments

- [k6](https://k6.io/) — load testing
- [Scala.js](https://www.scala-js.org/) — Scala to JavaScript
