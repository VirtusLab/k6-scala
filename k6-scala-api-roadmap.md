# k6-Scala API Completion Roadmap

## Overview

This roadmap outlines the implementation phases to achieve complete k6 JavaScript API parity in the k6-scala project. The phases are organized by priority, complexity, and user demand.

## Current Status

✅ **Already Implemented:**
- Core k6 functions (check, fail, group, randomSeed, sleep)
- k6/http (basic HTTP methods, cookies, file uploads)
- k6/execution (complete implementation)
- k6/encoding (base64 operations)
- k6/timers (timer functions)
- k6/data (basic SharedArray)
- Test options (comprehensive configuration)

## Phase 1: Complete k6/http Module 🚀
**Priority:** Critical | **Effort:** 1-2 weeks | **Status:** Spec Complete

### Implementation Items
- [ ] `batch()` function for parallel requests
- [ ] `request()` generic method for custom HTTP verbs  
- [ ] `Response.json()` and `Response.html()` methods
- [ ] `Response.submitForm()` and `Response.clickLink()` methods
- [ ] Enhanced body format support (FormData, ArrayBuffer)

### Reference Documentation
- ✅ **Detailed spec:** `docs/k6-http-missing-features-spec.md`
- ✅ **Requirements:** `docs/k6-http-missing-features-prd.md`
- 📚 **k6 API:** https://grafana.com/docs/k6/latest/javascript-api/k6-http/

### Implementation Files
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/http/
├── Http.scala              (ADD: batch, request methods)
├── package.scala           (ADD: batch, request wrappers) 
├── BatchRequest.scala      (NEW: batch request types)
├── Selection.scala         (NEW: minimal k6/html facade)
├── Response.scala          (ADD: json, html, form methods)
```

### Testing Strategy
- Unit tests for BatchRequest constructors
- Integration examples in `examples/api-examples/`
- Manual k6 runtime testing

---

## Phase 2: Essential Testing Modules 📊
**Priority:** High | **Effort:** 2-3 weeks | **Dependencies:** Phase 1

### k6/metrics - Custom Performance Metrics
**Why Critical:** Essential for comprehensive performance testing

#### Implementation Items
- [ ] `Counter` class for event counting
- [ ] `Gauge` class for current values
- [ ] `Rate` class for success/failure rates  
- [ ] `Trend` class for timing metrics
- [ ] Custom metric creation and tracking

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/metrics/
├── Metrics.scala           (Native JS imports)
├── Counter.scala           (Counter metric type)
├── Gauge.scala             (Gauge metric type)
├── Rate.scala              (Rate metric type)
├── Trend.scala             (Trend metric type)
├── package.scala           (Scala wrappers)
```

#### API Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/k6-metrics/

---

### k6/crypto - Cryptographic Operations  
**Why Important:** Common for authentication, signing, hashing

#### Implementation Items
- [ ] Hash functions (md5, sha1, sha256, sha384, sha512)
- [ ] HMAC operations
- [ ] Random bytes generation
- [ ] Hash and HMAC result types

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/crypto/
├── Crypto.scala            (Native JS imports)
├── Hasher.scala            (Hash function bindings)
├── package.scala           (Scala-friendly API)
├── HashAlgorithm.scala     (Algorithm enum)
```

#### API Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/k6-crypto/

---

### Global crypto - Web Crypto API
**Why Important:** Standard cryptographic primitives

#### Implementation Items
- [ ] `crypto.getRandomValues()`
- [ ] `crypto.randomUUID()`
- [ ] `crypto.subtle` (SubtleCrypto interface)
- [ ] Key generation, encryption, signing operations

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/webcrypto/
├── WebCrypto.scala         (Global crypto object)
├── SubtleCrypto.scala      (Subtle crypto operations)
├── CryptoKey.scala         (Key types)
├── package.scala           (Scala wrappers)
```

#### API Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/crypto/

---

### Enhanced k6/data Module
**Why Useful:** Better data handling for tests

#### Implementation Items
- [ ] Complete SharedArray constructor variants
- [ ] Proper k6/data module structure
- [ ] Data loading utilities

#### Files to Enhance
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/data/
├── SharedArray.scala       (ENHANCE: proper constructors)
├── Data.scala              (NEW: k6/data native imports)
├── package.scala           (NEW: Scala wrappers)
```

---

## Phase 3: Protocol Support Modules 🌐
**Priority:** Medium-High | **Effort:** 3-4 weeks | **Dependencies:** Phase 2

### k6/ws - WebSocket Support
**Why Important:** WebSocket testing is increasingly common

#### Implementation Items
- [ ] `ws.connect()` function
- [ ] `Socket` class with event handlers
- [ ] WebSocket message sending/receiving
- [ ] Connection lifecycle management
- [ ] Error handling and timeouts

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/ws/
├── WebSocket.scala         (Native JS imports)
├── Socket.scala            (Socket class facade)
├── SocketEvent.scala       (Event types)
├── package.scala           (Scala-friendly API)
```

#### API Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/k6-ws/

---

### k6/net/grpc - gRPC Protocol Support
**Why Important:** Growing adoption of gRPC APIs

#### Implementation Items  
- [ ] `grpc.connect()` and client creation
- [ ] `grpc.invoke()` for unary calls
- [ ] `grpc.load()` for proto definitions
- [ ] Stream handling (client/server/bidirectional)
- [ ] Metadata and error handling

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/grpc/
├── GRPC.scala              (Native JS imports)
├── Client.scala            (gRPC client facade)
├── Stream.scala            (Stream types)
├── package.scala           (Scala wrappers)
```

#### API Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/

---

## Phase 4: Advanced Testing Capabilities 🎭
**Priority:** Medium | **Effort:** 4-5 weeks | **Dependencies:** Phase 3

### k6/browser - Browser Automation
**Why Important:** End-to-end testing capabilities

#### Implementation Items
- [ ] `browser.newContext()` and `browser.newPage()`
- [ ] Page navigation (`page.goto()`, `page.reload()`)
- [ ] Element interaction (`page.click()`, `page.fill()`)
- [ ] Selectors and waiting (`page.locator()`, `page.waitForSelector()`)
- [ ] Screenshots and PDFs (`page.screenshot()`, `page.pdf()`)
- [ ] Browser metrics collection

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/browser/
├── Browser.scala           (Browser object)
├── BrowserContext.scala    (Context class)
├── Page.scala              (Page class)  
├── Locator.scala           (Element locator)
├── ElementHandle.scala     (Element handle)
├── package.scala           (Scala API)
```

#### API Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/k6-browser/

---

### k6/html - HTML Parsing and Manipulation
**Why Useful:** Advanced HTML testing capabilities

#### Implementation Items
- [ ] `parseHTML()` function
- [ ] Complete `Selection` class (jQuery-like API)
- [ ] DOM traversal methods
- [ ] Element content extraction
- [ ] Attribute manipulation

#### Files to Create  
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/html/
├── HTML.scala              (Native JS imports)
├── Selection.scala         (Complete Selection API)
├── Element.scala           (Individual elements)
├── package.scala           (Scala wrappers)
```

#### API Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/k6-html/

---

### k6/secrets - Secret Management
**Why Important:** Secure handling of sensitive data

#### Implementation Items
- [ ] `Secret` class for secret values
- [ ] Secret value access methods
- [ ] Integration with k6 secret stores

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/secrets/
├── Secrets.scala           (Native JS imports) 
├── Secret.scala            (Secret class)
├── package.scala           (Scala wrappers)
```

#### API Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/k6-secrets/

---

## Phase 5: Ecosystem and Utilities 🛠️
**Priority:** Low-Medium | **Effort:** 3-4 weeks | **Dependencies:** Phase 4

### jslib/utils - Common Utilities
**Why Useful:** Frequently needed utility functions

#### Implementation Items
- [ ] Random generators (`randomIntBetween`, `randomItem`, `randomString`)
- [ ] String utilities (`findBetween`, `normalizeToKebabCase`)
- [ ] Validation functions (`isURL`)
- [ ] Additional check and sleep utilities

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/jslib/utils/
├── Utils.scala             (Native utilities)
├── RandomUtils.scala       (Random generators)
├── StringUtils.scala       (String operations)
├── package.scala           (Scala API)
```

#### Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/jslib/utils/

---

### jslib/httpx - Enhanced HTTP Session Handling
**Why Useful:** Advanced HTTP testing patterns

#### Implementation Items
- [ ] Session management with automatic cookies
- [ ] Request/response middleware
- [ ] Authentication helpers
- [ ] Advanced request configuration

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/jslib/httpx/
├── HTTPx.scala             (Session class)
├── Session.scala           (HTTP session)
├── Middleware.scala        (Request middleware)
├── package.scala           (Scala API)
```

#### Reference  
📚 https://grafana.com/docs/k6/latest/javascript-api/jslib/httpx/

---

### jslib/k6chaijs - BDD Assertions
**Why Useful:** Alternative testing style

#### Implementation Items
- [ ] `expect()` assertion functions
- [ ] Chai-style assertion API
- [ ] Custom matcher support

#### Files to Create
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/jslib/chaijs/
├── ChaiJS.scala            (Expect functions)
├── Assertion.scala         (Assertion API)
├── Matchers.scala          (Custom matchers)
├── package.scala           (Scala API)
```

#### Reference
📚 https://grafana.com/docs/k6/latest/javascript-api/jslib/k6chaijs/

---

### Additional jslib Modules

#### jslib/testing - Advanced Testing Framework
- [ ] `describe()` and `it()` test structure
- [ ] Playwright-inspired assertions
- [ ] Mock functions and spies

#### jslib/aws - AWS Services Integration
- [ ] S3, SecretsManager, SystemsManager clients
- [ ] SQS, EventBridge, KMS, Kinesis clients
- [ ] AWS authentication handling

#### jslib/http-instrumentation-* - Observability
- [ ] Pyroscope baggage headers
- [ ] Tempo tracing instrumentation
- [ ] Performance monitoring integration

---

## Implementation Guidelines

### Project Structure Pattern
Each module should follow this structure:
```
k6scala/src/main/scala/org/virtuslab/scalajs/k6/{module}/
├── {Module}.scala          (Native @JSImport bindings)
├── package.scala           (Scala-friendly wrappers)
├── {Types}.scala           (Supporting types/traits)
└── examples/
    └── {module}-example.scala
```

### Development Process
1. **Research Phase**: Study k6 JavaScript API documentation
2. **Design Phase**: Create native facades and Scala wrappers
3. **Implementation Phase**: Write code following existing patterns
4. **Testing Phase**: Create examples and test with k6 runtime
5. **Documentation Phase**: Update README and create usage examples

### Quality Standards
- ✅ Cross-Scala version compatibility (2.12, 2.13, 3.3, 3.5)
- ✅ Type-safe facades (avoid `js.Dynamic` where possible)
- ✅ Idiomatic Scala APIs with `Option` types
- ✅ Comprehensive ScalaDoc documentation
- ✅ Working examples for each feature
- ✅ Consistent formatting (scalafmt)

### Testing Strategy
- **Unit Tests**: For pure Scala logic and helpers
- **Compilation Tests**: Ensure cross-version compatibility
- **Integration Tests**: Manual testing with k6 runtime
- **Example Tests**: Verify examples work with real k6

---

## Success Metrics

### Phase Completion Criteria
- [ ] All APIs implemented with proper facades
- [ ] Code compiles across all supported Scala versions
- [ ] At least one working example per major feature
- [ ] Documentation updated (README, ScalaDoc)
- [ ] No regression in existing functionality

### Final Success Metrics
- **API Coverage**: 95%+ of k6 JavaScript API implemented
- **Type Safety**: <5% usage of `js.Dynamic` in public APIs
- **Documentation**: 100% of public APIs have ScalaDoc
- **Examples**: 100% of modules have working examples
- **Testing**: 90%+ code paths covered by compilation tests

---

## Resource Requirements

### Development Effort Estimate
- **Phase 1**: 1-2 weeks (already spec'd)
- **Phase 2**: 2-3 weeks  
- **Phase 3**: 3-4 weeks
- **Phase 4**: 4-5 weeks
- **Phase 5**: 3-4 weeks
- **Total**: ~4-5 months with 1 developer

### Skills Required
- Scala and Scala.js expertise
- k6 performance testing knowledge  
- JavaScript interop experience
- API design experience

### Infrastructure Needs
- CI/CD pipeline for cross-version testing
- k6 runtime environment for integration testing
- Documentation hosting (GitHub Pages or similar)

---

## Risk Assessment

### High Risk Items
- **k6/browser**: Complex API surface, frequent k6 updates
- **jslib modules**: External dependencies, maintenance overhead

### Medium Risk Items
- **k6/grpc**: Protocol complexity, streaming APIs
- **k6/html**: Large API surface (Selection methods)

### Low Risk Items
- **k6/metrics**: Well-defined, stable API
- **k6/crypto**: Standard cryptographic operations
- **jslib/utils**: Simple utility functions

### Mitigation Strategies
- Start with high-priority, low-risk modules
- Implement minimal viable facades first, expand later
- Maintain close alignment with k6 JavaScript API versions
- Regular testing with latest k6 releases

---

## Next Steps

1. **Immediate**: Complete Phase 1 (k6/http) using existing specifications
2. **Short-term**: Begin Phase 2 design (k6/metrics, k6/crypto)  
3. **Medium-term**: Implement Phase 2 and begin Phase 3
4. **Long-term**: Complete advanced modules and jslib ecosystem

This roadmap provides a comprehensive path to achieving full k6 JavaScript API parity in Scala, prioritizing the most commonly used and critical features first.
