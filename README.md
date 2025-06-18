# Scala.js Facade for k6 (`k6-scala`)

This is the **k6-scala**, a Scala.js facade for the [k6 load testing library](https://k6.io/).  
Created as part of a blog post project to explore load testing from Scala, this initial release focuses on core usability and integration.

---

## What's Included

### 🔹 Scala.js Facades for k6 Modules
- [`k6`](https://k6.io/docs/javascript-api/k6/)
- [`k6/http`](https://k6.io/docs/javascript-api/k6-http/)
- [`k6/data`](https://k6.io/docs/javascript-api/k6-data/)
- [`k6/options`](https://k6.io/docs/using-k6/options/)

### Basic Examples
- Load testing scenarios adapted from the official [k6.io examples](https://grafana.com/docs/k6/latest/examples/)
- Written in idiomatic Scala with `scalajs-facade`
- Covers common usage patterns

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

## Notes

- This is an **early-stage** version meant for exploration and feedback.
- The facades are **not exhaustive** and currently focus on key functionality.
- Contributions and suggestions are very welcome!

---

## Related
- [k6 documentation](https://k6.io/docs/)
- [Scala.js documentation](https://www.scala-js.org/doc/)
