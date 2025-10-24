# Technical Specification: k6/http Missing Features Implementation

## Document Control

| Field | Value |
|-------|-------|
| Version | 1.0 |
| Status | Draft |
| Last Updated | 2025-10-09 |
| Related PRD | k6-http-missing-features-prd.md |

## Table of Contents

1. [Architecture Overview](#architecture-overview)
2. [Detailed Design](#detailed-design)
3. [File Changes](#file-changes)
4. [API Specifications](#api-specifications)
5. [Testing Strategy](#testing-strategy)
6. [Migration Guide](#migration-guide)

## Architecture Overview

### Current Architecture

```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/http/
├── Http.scala              (Native JS bindings)
├── package.scala           (Scala-friendly wrappers)
├── Response.scala          (Response type)
├── Request.scala           (Request type)
├── Params.scala           (Request parameters)
└── [other types...]
```

**Pattern:**
1. `Http.scala` defines `@js.native` bindings to k6/http JavaScript module
2. `package.scala` provides idiomatic Scala wrappers with `Option` types
3. Type-safe enums/ADTs for constants (HttpMethod, ResponseType, etc.)

### Proposed Changes

```diff
k6scala/src/main/scala/org/virtuslab/scalajs/k6/http/
├── Http.scala              (ADD: batch, request methods)
├── package.scala           (ADD: batch, request wrappers)
+├── BatchRequest.scala      (NEW: batch request types)
+├── BatchResponse.scala     (NEW: batch response types)
├── Response.scala          (ADD: json, html, submitForm, clickLink methods)
+├── Selection.scala         (NEW: minimal k6/html Selection facade)
└── [other types...]
```

## Detailed Design

### 1. batch() Function Implementation

#### 1.1 Native Binding (Http.scala)

```scala
@js.native
@JSImport("k6/http", JSImport.Namespace)
private[http] object Http extends js.Object {
  // Existing methods...

  // Batch with array input
  def batch(requests: js.Array[URL | js.Object]): js.Array[Response] = js.native

  // Batch with object input (named requests)
  def batch(requests: js.Dictionary[URL | js.Object]): js.Dictionary[Response] = js.native
}
```

**Design Decision:** Use two separate native bindings for array vs dictionary input to maintain type safety.

#### 1.2 Scala Wrappers (package.scala)

```scala
package object http {
  // Existing functions...

  // Batch with sequence of URLs (simple GET requests)
  def batch(urls: Seq[String]): Seq[Response] = {
    val jsArray = js.Array(urls.map(_.asInstanceOf[URL]): _*)
    Http.batch(jsArray).toSeq
  }

  // Batch with sequence of request objects
  def batch(requests: Seq[BatchRequest]): Seq[Response] = {
    val jsArray = js.Array(requests.map(_.toJS): _*)
    Http.batch(jsArray).toSeq
  }

  // Batch with map of named requests
  def batch(requests: Map[String, BatchRequest]): Map[String, Response] = {
    val jsDict = js.Dictionary(
      requests.map { case (k, v) => k -> v.toJS.asInstanceOf[URL | js.Object] }.toSeq: _*
    )
    Http.batch(jsDict).toMap
  }

  // Batch with map of named URLs
  def batchUrls(urls: Map[String, String]): Map[String, Response] = {
    val jsDict = js.Dictionary(
      urls.map { case (k, v) => k -> v.asInstanceOf[URL] }.toSeq: _*
    )
    Http.batch(jsDict).toMap
  }
}
```

**Design Decisions:**
- Separate `batchUrls()` for Map[String, String] to avoid ambiguity
- Use `toSeq` and `toMap` conversions for idiomatic Scala return types
- Accept `Seq` instead of `List` for broader compatibility

#### 1.3 BatchRequest Type (BatchRequest.scala)

```scala
package org.virtuslab.scalajs.k6.http

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait BatchRequest extends js.Object {
  def method: HttpMethodType = js.native
  def url: URL = js.native
  def body: js.UndefOr[BodyOpt] = js.native
  def params: js.UndefOr[Params] = js.native
}

object BatchRequest {
  def apply(
    method: HttpMethod,
    url: String,
    body: Option[String] = None,
    params: Option[Params] = None
  ): BatchRequest = {
    js.Dynamic.literal(
      method = method.toJSType,
      url = url,
      body = body.orUndefined,
      params = params.orUndefined
    ).asInstanceOf[BatchRequest]
  }

  // Convenience constructors for common methods
  def get(url: String, params: Option[Params] = None): BatchRequest =
    apply(HttpMethod.GET, url, None, params)

  def post(url: String, body: Option[String] = None, params: Option[Params] = None): BatchRequest =
    apply(HttpMethod.POST, url, body, params)

  def put(url: String, body: Option[String] = None, params: Option[Params] = None): BatchRequest =
    apply(HttpMethod.PUT, url, body, params)

  def delete(url: String, body: Option[String] = None, params: Option[Params] = None): BatchRequest =
    apply(HttpMethod.DELETE, url, body, params)
}

// Extension for converting BatchRequest to JS
extension (br: BatchRequest) {
  def toJS: js.Object = br.asInstanceOf[js.Object]
}
```

**Design Decisions:**
- Scala 3 extension methods for `toJS` (use implicit class for Scala 2.x)
- Convenience constructors mirror existing http method signatures
- Immutable case-class-like API

### 2. request() Function Implementation

#### 2.1 Native Binding (Http.scala)

```scala
@js.native
@JSImport("k6/http", JSImport.Namespace)
private[http] object Http extends js.Object {
  // Existing methods...

  def request(
    method: HttpMethodType,
    url: URL,
    body: BodyOpt,
    params: ParamsOpt
  ): Response = js.native
}
```

#### 2.2 Scala Wrapper (package.scala)

```scala
package object http {
  // Existing functions...

  // Using HttpMethod enum
  def request(
    method: HttpMethod,
    url: URL,
    body: Option[String] = None,
    params: Option[Params] = None
  ): Response =
    Http.request(method.toJSType, url, body.orUndefined, params.orUndefined)

  // Using raw string for custom methods
  def request(
    method: String,
    url: URL,
    body: Option[String] = None,
    params: Option[Params] = None
  ): Response =
    Http.request(
      method.asInstanceOf[HttpMethodType],
      url,
      body.orUndefined,
      params.orUndefined
    )
}
```

**Design Decisions:**
- Provide both typed (HttpMethod) and untyped (String) variants
- String variant enables custom HTTP methods (PROPFIND, MKCOL, etc.)
- Method name matching k6 API exactly

### 3. Response.json() Method Implementation

#### 3.1 Native Binding (Response.scala)

```scala
@js.native
trait Response extends js.Object {
  // Existing properties...
  def body: String = js.native
  def status: Int = js.native
  // ... other properties ...

  // NEW: JSON parsing methods
  def json(): js.Any = js.native
  def json(selector: String): js.Any = js.native
}
```

**Design Decisions:**
- Return `js.Any` for maximum flexibility
- Support optional selector parameter for JSONPath extraction
- Users can cast to specific types: `response.json().asInstanceOf[js.Dictionary[String]]`

#### 3.2 Optional: Typed Helpers (package.scala or ResponseExtensions.scala)

```scala
// Future enhancement - not in initial implementation
object ResponseExtensions {
  extension (response: Response) {
    def jsonAs[T]: T = response.json().asInstanceOf[T]
    def jsonAs[T](selector: String): T = response.json(selector).asInstanceOf[T]
  }
}
```

### 4. Response.html() and Related Methods

#### 4.1 Selection Facade (Selection.scala)

```scala
package org.virtuslab.scalajs.k6.http

import scala.scalajs.js
import scala.scalajs.js.annotation._

// Minimal Selection facade from k6/html
@js.native
trait Selection extends js.Object {
  // Core selection methods
  def find(selector: String): Selection = js.native
  def filter(selector: String): Selection = js.native

  // Content extraction
  def text(): String = js.native
  def html(): String = js.native

  // Attribute access
  def attr(name: String): String = js.native
  def data(name: String): String = js.native

  // Tree traversal
  def children(selector: js.UndefOr[String] = js.undefined): Selection = js.native
  def parent(selector: js.UndefOr[String] = js.undefined): Selection = js.native
  def next(selector: js.UndefOr[String] = js.undefined): Selection = js.native
  def prev(selector: js.UndefOr[String] = js.undefined): Selection = js.native

  // Collection info
  def length(): Int = js.native
  def size(): Int = js.native
  def get(index: Int): js.UndefOr[js.Object] = js.native

  // Iteration
  def each(fn: js.Function2[Int, js.Object, Unit]): Selection = js.native
  def map(fn: js.Function2[Int, js.Object, js.Any]): js.Array[js.Any] = js.native
}

// Note: Full k6/html module support should be separate PR
// This is minimal facade to support Response.html()
```

**Design Decisions:**
- Keep minimal - only methods likely needed for basic HTML testing
- Match jQuery-like k6 Selection API structure
- Document that full k6/html support is future work
- Use `js.UndefOr` for optional selector parameters

#### 4.2 Response HTML Methods (Response.scala)

```scala
@js.native
trait Response extends js.Object {
  // Existing properties and json() methods...

  // NEW: HTML parsing
  def html(): Selection = js.native
  def html(selector: String): Selection = js.native

  // NEW: Form/link interaction
  def submitForm(args: js.UndefOr[js.Object] = js.undefined): Response = js.native
  def clickLink(args: js.UndefOr[js.Object] = js.undefined): Response = js.native
}
```

**Design Decisions:**
- `args` parameter as `js.Object` for flexibility (k6 API is loosely typed)
- Return type `Response` for method chaining
- Consider builder pattern for `args` in future enhancement

## File Changes

### New Files

1. **`k6scala/src/main/scala/org/virtuslab/scalajs/k6/http/BatchRequest.scala`**
   - BatchRequest trait and object
   - Convenience constructors
   - toJS extension/implicit

2. **`k6scala/src/main/scala/org/virtuslab/scalajs/k6/http/Selection.scala`**
   - Minimal Selection facade from k6/html
   - Core selection and extraction methods

### Modified Files

1. **`k6scala/src/main/scala/org/virtuslab/scalajs/k6/http/Http.scala`**
   ```diff
   @js.native
   @JSImport("k6/http", JSImport.Namespace)
   private[http] object Http extends js.Object {
     // Existing methods...
   +  def batch(requests: js.Array[URL | js.Object]): js.Array[Response] = js.native
   +  def batch(requests: js.Dictionary[URL | js.Object]): js.Dictionary[Response] = js.native
   +  def request(method: HttpMethodType, url: URL, body: BodyOpt, params: ParamsOpt): Response = js.native
   }
   ```

2. **`k6scala/src/main/scala/org/virtuslab/scalajs/k6/http/package.scala`**
   ```diff
   package object http {
     // Existing functions...
   +  // batch() overloads
   +  def batch(urls: Seq[String]): Seq[Response] = ???
   +  def batch(requests: Seq[BatchRequest]): Seq[Response] = ???
   +  def batch(requests: Map[String, BatchRequest]): Map[String, Response] = ???
   +  def batchUrls(urls: Map[String, String]): Map[String, Response] = ???
   +
   +  // request() overloads
   +  def request(method: HttpMethod, url: URL, body: Option[String], params: Option[Params]): Response = ???
   +  def request(method: String, url: URL, body: Option[String], params: Option[Params]): Response = ???
   }
   ```

3. **`k6scala/src/main/scala/org/virtuslab/scalajs/k6/http/Response.scala`**
   ```diff
   @js.native
   trait Response extends js.Object {
     // Existing properties...
   +  def json(): js.Any = js.native
   +  def json(selector: String): js.Any = js.native
   +  def html(): Selection = js.native
   +  def html(selector: String): Selection = js.native
   +  def submitForm(args: js.UndefOr[js.Object] = js.undefined): Response = js.native
   +  def clickLink(args: js.UndefOr[js.Object] = js.undefined): Response = js.native
   }
   ```

### Example Files

Create new example files:

1. **`examples/api-examples/k6-batch-requests.scala`**
   - Demonstrate batch() with URLs
   - Demonstrate batch() with BatchRequest objects
   - Demonstrate batch() with named requests (Map)

2. **`examples/api-examples/k6-json-responses.scala`**
   - Demonstrate Response.json() parsing
   - Demonstrate JSON extraction with selectors
   - Show type casting patterns

3. **`examples/api-examples/k6-html-forms.scala`**
   - Demonstrate Response.html() parsing
   - Demonstrate form submission
   - Demonstrate link clicking

## API Specifications

### batch() API

#### Syntax

```scala
// Simple batch with URLs (GET requests)
val responses: Seq[Response] = batch(Seq(
  "https://api.example.com/users",
  "https://api.example.com/posts"
))

// Batch with request objects
val responses: Seq[Response] = batch(Seq(
  BatchRequest.get("https://api.example.com/users"),
  BatchRequest.post("https://api.example.com/posts", Some("""{"title":"Test"}""")),
  BatchRequest(HttpMethod.PUT, "https://api.example.com/posts/1", Some("..."))
))

// Named batch requests
val responses: Map[String, Response] = batch(Map(
  "users" -> BatchRequest.get("https://api.example.com/users"),
  "posts" -> BatchRequest.get("https://api.example.com/posts")
))

// Access named responses
println(responses("users").status)
```

#### Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| urls | `Seq[String]` | Sequence of URLs for GET requests |
| requests | `Seq[BatchRequest]` | Sequence of request objects |
| requests | `Map[String, BatchRequest]` | Named requests |

#### Returns

| Input Type | Return Type |
|------------|-------------|
| `Seq[String]` | `Seq[Response]` |
| `Seq[BatchRequest]` | `Seq[Response]` |
| `Map[String, BatchRequest]` | `Map[String, Response]` |

### request() API

#### Syntax

```scala
// Using HttpMethod enum
val response: Response = request(
  HttpMethod.POST,
  "https://api.example.com/users",
  Some("""{"name":"John"}"""),
  Some(Params(headers = Some(Map("Content-Type" -> "application/json"))))
)

// Using custom method string
val response: Response = request(
  "PROPFIND",
  "https://webdav.example.com/files",
  None,
  Some(Params(headers = Some(Map("Depth" -> "1"))))
)
```

#### Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| method | `HttpMethod` or `String` | HTTP method |
| url | `URL` (String or HttpURL) | Target URL |
| body | `Option[String]` | Request body (optional) |
| params | `Option[Params]` | Request parameters (optional) |

#### Returns

`Response` object

### Response.json() API

#### Syntax

```scala
val response = http.get("https://api.example.com/user/1")

// Parse entire response
val userData: js.Dynamic = response.json().asInstanceOf[js.Dynamic]
println(userData.name)

// Extract with selector (JSONPath-like)
val userId: js.Any = response.json("user.id")

// Type-safe casting
val userDict: js.Dictionary[String] = response.json().asInstanceOf[js.Dictionary[String]]
```

#### Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| selector | `String` (optional) | JSONPath-like selector |

#### Returns

`js.Any` - Can be cast to specific JS types

### Response.html() API

#### Syntax

```scala
val response = http.get("https://example.com/form")

// Parse HTML
val doc: Selection = response.html()

// Find elements
val title: String = doc.find("h1").text()

// Parse with initial selector
val form: Selection = response.html("form#login")
```

#### Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| selector | `String` (optional) | Initial CSS selector |

#### Returns

`Selection` object for querying HTML

## Testing Strategy

### Unit Tests

Due to Scala.js and k6 runtime requirements, comprehensive unit testing is limited. Focus on:

1. **Compilation tests** - Ensure code compiles across all Scala versions
2. **Type safety tests** - Verify proper type inference
3. **Helper function tests** - Test pure Scala logic (converters, builders)

```scala
// Example test structure
class BatchRequestSpec extends AnyFlatSpec with Matchers {
  "BatchRequest.get" should "create GET request with correct method" in {
    val req = BatchRequest.get("https://example.com")
    // Note: Testing limited without k6 runtime
    // Focus on compilation and type checking
  }
}
```

### Integration/Manual Tests

Primary testing method - create examples and run with k6:

1. **Batch example**: `cd examples/api-examples && scala-cli package k6-batch-requests.scala && k6 run output.js`
2. **JSON example**: Test with real API endpoints
3. **HTML example**: Test with real web pages

### Test Checklist

- [ ] Compiles on Scala 2.12.10
- [ ] Compiles on Scala 2.13.16
- [ ] Compiles on Scala 3.3.6
- [ ] Compiles on Scala 3.5.0
- [ ] scalafmtCheck passes
- [ ] Example: batch with URLs works
- [ ] Example: batch with BatchRequest works
- [ ] Example: batch with Map works
- [ ] Example: request() with custom method works
- [ ] Example: response.json() parses correctly
- [ ] Example: response.html() returns Selection
- [ ] Example: response.submitForm() works
- [ ] Example: response.clickLink() works

## Migration Guide

### For Existing Users

**No Breaking Changes** - All additions are backwards compatible.

Existing code continues to work unchanged:

```scala
// Existing code - still works
val response = http.get("https://api.example.com")
check(response, Checkers("status is 200" -> Check[Response](_.status == 200)))
```

### New Capabilities

Users can now:

```scala
// NEW: Parallel requests
val responses = batch(Seq(
  "https://api.example.com/endpoint1",
  "https://api.example.com/endpoint2"
))

// NEW: JSON parsing
val data = response.json().asInstanceOf[js.Dynamic]

// NEW: Custom methods
val response = request("MKCOL", "https://webdav.example.com/newdir")
```

### Deprecations

None.

### Future Breaking Changes

None planned. If Response.json() is enhanced with typed helpers, they will be additive.

## Compatibility Matrix

| Feature | Scala 2.12 | Scala 2.13 | Scala 3.3 | Scala 3.5 |
|---------|------------|------------|-----------|-----------|
| batch() | ✅ | ✅ | ✅ | ✅ |
| request() | ✅ | ✅ | ✅ | ✅ |
| Response.json() | ✅ | ✅ | ✅ | ✅ |
| Response.html() | ✅ | ✅ | ✅ | ✅ |
| Response.submitForm() | ✅ | ✅ | ✅ | ✅ |
| Response.clickLink() | ✅ | ✅ | ✅ | ✅ |

**Notes:**
- Extension methods syntax differs between Scala 2 and 3
- Use implicit class for Scala 2.x, extension for Scala 3
- Conditional compilation with version-specific source directories if needed

## Performance Considerations

### Runtime Performance

- Native JS calls - minimal overhead
- Seq/Map conversions - acceptable for load testing use case
- No performance-critical loops in user code path

### Compile Time

- Additional overloads may slightly increase compile time
- Union types (String | js.Object) supported in Scala.js
- No significant impact expected

## Security Considerations

- No user input validation (delegated to k6 runtime)
- URL handling by k6 (no additional validation needed)
- JSON parsing by k6 (no XSS concerns in load testing context)

## Documentation Plan

1. **ScalaDoc comments** - All public APIs
2. **Usage examples** - In `/examples` directory
3. **README update** - Mention new capabilities
4. **CLAUDE.md update** - Document new API surface

## Rollout Plan

### Phase 1: Core Functions (PR #1)
- Implement batch() and request()
- Add BatchRequest type
- Create batch example
- Update CI

**Review Focus:** API design, type safety, example clarity

### Phase 2: JSON Support (PR #2)
- Add Response.json()
- Create JSON parsing example
- Document usage patterns

**Review Focus:** Type casting patterns, user ergonomics

### Phase 3: HTML Support (PR #3)
- Add Selection facade
- Add Response.html(), submitForm(), clickLink()
- Create HTML/form examples

**Review Focus:** Selection API scope, k6/html module plan

## Appendix

### A. Alternative Designs Considered

#### batch() Return Types

**Option 1: Always return Seq** (Rejected)
```scala
def batch(requests: Map[String, BatchRequest]): Seq[Response]
```
❌ Loses named request information

**Option 2: Use js.Array/js.Dictionary directly** (Rejected)
```scala
def batch(requests: Seq[BatchRequest]): js.Array[Response]
```
❌ Less idiomatic Scala

**Option 3: Separate functions** (Rejected)
```scala
def batchSeq(...)
def batchMap(...)
```
❌ Verbose, not following k6 API naming

**Selected: Method overloading with Scala collections**
```scala
def batch(requests: Seq[...]): Seq[Response]
def batch(requests: Map[String, ...]): Map[String, Response]
```
✅ Idiomatic Scala, preserves structure

#### Response.json() Return Type

**Option 1: js.Dynamic** (Rejected)
```scala
def json(): js.Dynamic
```
❌ Overly permissive, runtime errors

**Option 2: Generic type parameter** (Rejected)
```scala
def json[T](): T
```
❌ Doesn't match k6 API, requires implicit evidence

**Selected: js.Any with manual casting**
```scala
def json(): js.Any
// Usage: response.json().asInstanceOf[TargetType]
```
✅ Matches k6 API, explicit casting, maximum flexibility

### B. Scala 2/3 Compatibility Notes

#### Extension Methods

**Scala 3:**
```scala
extension (br: BatchRequest) {
  def toJS: js.Object = br.asInstanceOf[js.Object]
}
```

**Scala 2:**
```scala
implicit class BatchRequestOps(val br: BatchRequest) extends AnyVal {
  def toJS: js.Object = br.asInstanceOf[js.Object]
}
```

**Strategy:** Use cross-version source directories or conditional compilation if needed.

### C. k6 API References

- [k6 batch()](https://grafana.com/docs/k6/latest/javascript-api/k6-http/batch/)
- [k6 request()](https://grafana.com/docs/k6/latest/javascript-api/k6-http/request/)
- [k6 Response](https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/)
- [k6 Selection](https://grafana.com/docs/k6/latest/javascript-api/k6-html/selection/)
