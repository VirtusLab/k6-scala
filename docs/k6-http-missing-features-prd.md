# Product Requirements Document: k6/http API Completeness

## Overview

This document outlines the missing features from the k6/http module facade in k6-scala and provides a roadmap for achieving API completeness with the official k6 JavaScript API.

## Problem Statement

The current k6-scala http facade implementation is missing several critical features from the k6/http JavaScript API:
- No `batch()` function for parallel HTTP requests
- No `request()` generic method for custom HTTP verbs
- Missing `Response.json()`, `html()`, `clickLink()`, and `submitForm()` methods

These gaps limit the ability to write comprehensive load tests in Scala, particularly for:
1. **Performance testing scenarios** requiring parallel requests (batch)
2. **API testing** requiring JSON response parsing
3. **Advanced web testing** with HTML parsing and form interactions
4. **Custom HTTP methods** beyond standard REST verbs

## Goals

### Primary Goals
1. Achieve feature parity with k6/http JavaScript API
2. Maintain idiomatic Scala API design patterns
3. Preserve type safety and compile-time guarantees
4. Enable comprehensive load testing scenarios from Scala

### Non-Goals
1. Adding features not present in k6 JavaScript API
2. Breaking changes to existing API surface
3. Performance optimization (focus on feature completeness)

## Success Metrics

1. **API Coverage**: 100% of documented k6/http functions and methods implemented
2. **Type Safety**: All implementations use proper Scala.js facades (no `js.Dynamic` shortcuts)
3. **Documentation**: Each new feature has usage examples
4. **Testing**: Unit tests for core functionality (where feasible with Scala.js)

## User Stories

### Story 1: Parallel API Requests
**As a** performance tester
**I want to** send multiple HTTP requests in parallel
**So that** I can simulate realistic concurrent user behavior and reduce test execution time

**Acceptance Criteria:**
- Can call `batch()` with an array of URL strings
- Can call `batch()` with an array of request objects
- Can call `batch()` with a dictionary/map of named requests
- Returns responses in the same structure as input

### Story 2: JSON API Testing
**As a** API tester
**I want to** parse HTTP response bodies as JSON
**So that** I can validate API responses and extract dynamic values

**Acceptance Criteria:**
- Can call `response.json()` to parse full response body
- Can call `response.json(selector)` to extract specific JSON paths
- Returns properly typed Scala.js objects
- Handles parse errors gracefully

### Story 3: Custom HTTP Methods
**As a** API tester
**I want to** use custom or non-standard HTTP methods
**So that** I can test APIs that use methods like PROPFIND, MKCOL, etc.

**Acceptance Criteria:**
- Can call `request(method, url, body, params)`
- Method parameter accepts any string value
- Behaves identically to specific method functions (get, post, etc.)

### Story 4: HTML Form Testing
**As a** web application tester
**I want to** parse HTML responses and interact with forms
**So that** I can test multi-step web workflows

**Acceptance Criteria:**
- Can call `response.html()` to get Selection object
- Can query HTML elements from Selection
- Can call `response.submitForm()` to submit forms
- Can call `response.clickLink()` to follow links

## Feature Specifications

### Feature 1: batch() Function

#### API Signature
```scala
// Array of URLs (GET requests)
def batch(urls: Seq[String]): js.Array[Response]

// Array of request objects
def batch(requests: Seq[BatchRequest]): js.Array[Response]

// Map of named requests
def batch(requests: Map[String, BatchRequest]): js.Dictionary[Response]
```

#### BatchRequest Type
```scala
case class BatchRequest(
  method: HttpMethod,
  url: String,
  body: Option[String] = None,
  params: Option[Params] = None
)
```

#### Implementation Notes
- Must handle multiple input formats (array vs object)
- Preserve key names when using Map/Dictionary input
- Return type matches input structure (array vs dictionary)

### Feature 2: request() Function

#### API Signature
```scala
def request(
  method: String,
  url: URL,
  body: Option[String] = None,
  params: Option[Params] = None
): Response

// Alternative with HttpMethod enum
def request(
  method: HttpMethod,
  url: URL,
  body: Option[String] = None,
  params: Option[Params] = None
): Response
```

#### Implementation Notes
- Add native binding in `Http` object
- Expose both raw string and typed HttpMethod variants
- Consider deprecation notice if specific methods are preferred

### Feature 3: Response.json() Method

#### API Signature
```scala
@js.native
trait Response extends js.Object {
  // ... existing properties ...

  // Parse entire response body as JSON
  def json(): js.Any = js.native

  // Parse and extract using JSONPath-like selector
  def json(selector: String): js.Any = js.native
}
```

#### Implementation Notes
- Returns `js.Any` for maximum flexibility
- Users can cast to specific types as needed
- May add typed helper methods in future enhancement

### Feature 4: Response.html() Method

#### API Signature
```scala
@js.native
trait Response extends js.Object {
  // ... existing properties ...

  // Parse response body as HTML, returns Selection
  def html(): Selection = js.native
  def html(selector: String): Selection = js.native
}

// Selection facade (from k6/html)
@js.native
trait Selection extends js.Object {
  def find(selector: String): Selection = js.native
  def text(): String = js.native
  def attr(name: String): String = js.native
  // ... other Selection methods ...
}
```

#### Implementation Notes
- Requires defining `Selection` facade from k6/html module
- Selection API is extensive - start with core methods
- Consider separate PR for full k6/html module support

### Feature 5: Response Form/Link Methods

#### API Signature
```scala
@js.native
trait Response extends js.Object {
  // ... existing properties ...

  def submitForm(args: js.UndefOr[js.Object] = js.undefined): Response = js.native
  def clickLink(args: js.UndefOr[js.Object] = js.undefined): Response = js.native
}
```

#### Implementation Notes
- `args` parameter is flexible configuration object
- Consider Scala-friendly builder pattern in future
- Document expected args structure

## Technical Considerations

### Scala Version Compatibility
- Must work across all supported Scala versions (2.12.10, 2.13.16, 3.3.6, 3.5.0)
- Avoid Scala 3-only syntax in implementation
- Use compatible Scala.js patterns

### Type Safety vs Flexibility Trade-offs
- `batch()` input types: Use union types or overloads?
- `Response.json()` return type: `js.Any` vs typed alternatives?
- Consider both compile-time safety and runtime flexibility

### Breaking Changes Assessment
- All proposed changes are additive (new methods/functions)
- No modifications to existing signatures
- **Risk Level: Low** - No breaking changes expected

### Testing Strategy
- Unit tests for Scala helper functions
- Integration tests require k6 runtime (document manual testing)
- Add usage examples in `/examples` directory

## Dependencies

### External Dependencies
- No new external dependencies required
- All features use existing Scala.js and k6 APIs

### Internal Dependencies
- `Selection` facade may require partial k6/html module implementation
- Consider scope: full k6/html or just types needed for Response methods?

## Implementation Phases

### Phase 1: Core HTTP Functions (High Priority)
- Implement `batch()` function
- Implement `request()` function
- Add usage examples

**Estimated Effort:** 2-3 days
**Risk:** Low

### Phase 2: JSON Response Parsing (High Priority)
- Add `Response.json()` method
- Update Response.scala facade
- Add API testing example

**Estimated Effort:** 1 day
**Risk:** Low

### Phase 3: HTML/Form Methods (Medium Priority)
- Define minimal `Selection` facade
- Add `Response.html()` method
- Add `Response.submitForm()` and `clickLink()` methods
- Add web testing example

**Estimated Effort:** 3-4 days
**Risk:** Medium (requires k6/html types)

## Open Questions

1. **batch() overloading**: Should we use separate method names for different input types, or rely on overloading?
   - Recommendation: Use overloading with clear type signatures

2. **Selection scope**: How much of k6/html Selection API should we implement?
   - Recommendation: Start with minimum needed for Response methods, expand in separate PR

3. **Example placement**: Should examples be in existing directories or new ones?
   - Recommendation: Add to `/examples/api-examples` with descriptive names

4. **Documentation**: Where should API documentation live?
   - Recommendation: ScalaDoc comments + examples (consider docs site in future)

## Success Criteria

### Definition of Done
- [ ] All functions/methods implemented with proper facades
- [ ] Code compiles across all Scala versions (CI passes)
- [ ] At least one usage example per feature
- [ ] Code follows existing patterns and scalafmt rules
- [ ] PR description includes testing instructions

### Acceptance Testing
1. Manual testing with k6 runtime for each feature
2. Verify examples run successfully with `k6 run`
3. Verify type safety (no `js.Dynamic` casts in user code)
4. Cross-Scala version compilation check

## Future Enhancements

1. **Full k6/html module support** - Complete Selection API facade
2. **Typed JSON responses** - Helper methods with type parameters
3. **Batch request builder** - Fluent API for constructing batch requests
4. **Streaming responses** - Support for large response bodies
5. **WebSocket support** - k6/ws module facade

## References

- [k6 HTTP Documentation](https://grafana.com/docs/k6/latest/javascript-api/k6-http/)
- [k6 batch() Documentation](https://grafana.com/docs/k6/latest/javascript-api/k6-http/batch/)
- [k6 Response Documentation](https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/)
- [Scala.js Interop Guide](https://www.scala-js.org/doc/interoperability/)
