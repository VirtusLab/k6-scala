# k6-Scala API Implementation TODO

## Current Status

- [x] **Phase 1: Complete k6/http module (already spec'd)** - IN PROGRESS
- [ ] **Phase 2: Essential testing modules (metrics, crypto, timers enhancement)**
- [ ] **Phase 3: Protocol support modules (websockets, grpc)**
- [ ] **Phase 4: Advanced testing capabilities (browser, html, secrets)**
- [ ] **Phase 5: Ecosystem and utilities (jslib modules)**

## Phase 1: Complete k6/http Module ⚡ (IN PROGRESS)
**Priority:** Critical | **Effort:** 1-2 weeks | **Status:** Spec Complete

### Implementation Items
- [x] `batch()` function for parallel requests
- [x] `request()` generic method for custom HTTP verbs  
- [ ] `Response.json()` and `Response.html()` methods
- [ ] `Response.submitForm()` and `Response.clickLink()` methods
- [ ] Enhanced body format support (FormData, ArrayBuffer)

### Files to Create/Modify
- [x] `BatchRequest.scala` (NEW) - Implemented as Batch.scala with union types
- [ ] `Selection.scala` (NEW - minimal k6/html facade)
- [x] `Http.scala` (MODIFY - add batch, request methods)
- [x] `package.scala` (MODIFY - add batch, request wrappers)
- [ ] `Response.scala` (MODIFY - add json, html, form methods)

### Examples to Create
- [x] `examples/api-examples/k6-batch-requests.scala` - Created as k6-batch.scala
- [x] `examples/api-examples/k6-request.scala` - Created with all HTTP method variants
- [ ] `examples/api-examples/k6-json-responses.scala`
- [ ] `examples/api-examples/k6-html-forms.scala`

## Phase 2: Essential Testing Modules 📊
**Priority:** High | **Effort:** 2-3 weeks

### k6/metrics Module
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/metrics/` directory
- [ ] `Metrics.scala` (Native JS imports)
- [ ] `Counter.scala` (Counter metric type)
- [ ] `Gauge.scala` (Gauge metric type) 
- [ ] `Rate.scala` (Rate metric type)
- [ ] `Trend.scala` (Trend metric type)
- [ ] `package.scala` (Scala wrappers)
- [ ] Example: `examples/api-examples/k6-custom-metrics.scala`

### k6/crypto Module
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/crypto/` directory
- [ ] `Crypto.scala` (Native JS imports)
- [ ] `Hasher.scala` (Hash function bindings)
- [ ] `HashAlgorithm.scala` (Algorithm enum)
- [ ] `package.scala` (Scala-friendly API)
- [ ] Example: `examples/api-examples/k6-crypto-operations.scala`

### Global crypto (Web Crypto API)
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/webcrypto/` directory
- [ ] `WebCrypto.scala` (Global crypto object)
- [ ] `SubtleCrypto.scala` (Subtle crypto operations)
- [ ] `CryptoKey.scala` (Key types)
- [ ] `package.scala` (Scala wrappers)
- [ ] Example: `examples/api-examples/k6-webcrypto.scala`

### Enhanced k6/data Module
- [ ] `SharedArray.scala` (ENHANCE - proper constructors)
- [ ] `Data.scala` (NEW - k6/data native imports)
- [ ] `data/package.scala` (NEW - Scala wrappers)

## Phase 3: Protocol Support Modules 🌐
**Priority:** Medium-High | **Effort:** 3-4 weeks

### k6/ws Module (WebSocket Support)
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/ws/` directory
- [ ] `WebSocket.scala` (Native JS imports)
- [ ] `Socket.scala` (Socket class facade)
- [ ] `SocketEvent.scala` (Event types)
- [ ] `package.scala` (Scala-friendly API)
- [ ] Example: `examples/api-examples/k6-websockets.scala`

### k6/net/grpc Module
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/grpc/` directory
- [ ] `GRPC.scala` (Native JS imports)
- [ ] `Client.scala` (gRPC client facade)
- [ ] `Stream.scala` (Stream types)
- [ ] `package.scala` (Scala wrappers)
- [ ] Example: `examples/api-examples/k6-grpc.scala`

## Phase 4: Advanced Testing Capabilities 🎭
**Priority:** Medium | **Effort:** 4-5 weeks

### k6/browser Module
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/browser/` directory
- [ ] `Browser.scala` (Browser object)
- [ ] `BrowserContext.scala` (Context class)
- [ ] `Page.scala` (Page class)
- [ ] `Locator.scala` (Element locator)
- [ ] `ElementHandle.scala` (Element handle)
- [ ] `package.scala` (Scala API)
- [ ] Example: `examples/api-examples/k6-browser-testing.scala`

### k6/html Module (Complete Implementation)
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/html/` directory
- [ ] `HTML.scala` (Native JS imports)
- [ ] `Selection.scala` (Complete Selection API)
- [ ] `Element.scala` (Individual elements)
- [ ] `package.scala` (Scala wrappers)
- [ ] Example: `examples/api-examples/k6-html-parsing.scala`

### k6/secrets Module
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/secrets/` directory
- [ ] `Secrets.scala` (Native JS imports)
- [ ] `Secret.scala` (Secret class)
- [ ] `package.scala` (Scala wrappers)
- [ ] Example: `examples/api-examples/k6-secrets.scala`

## Phase 5: Ecosystem and Utilities 🛠️
**Priority:** Low-Medium | **Effort:** 3-4 weeks

### jslib/utils Module
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/jslib/utils/` directory
- [ ] `Utils.scala` (Native utilities)
- [ ] `RandomUtils.scala` (Random generators)
- [ ] `StringUtils.scala` (String operations)
- [ ] `package.scala` (Scala API)
- [ ] Example: `examples/api-examples/k6-utils.scala`

### jslib/httpx Module
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/jslib/httpx/` directory
- [ ] `HTTPx.scala` (Session class)
- [ ] `Session.scala` (HTTP session)
- [ ] `Middleware.scala` (Request middleware)
- [ ] `package.scala` (Scala API)
- [ ] Example: `examples/api-examples/k6-httpx-sessions.scala`

### jslib/k6chaijs Module
- [ ] Create `k6scala/src/main/scala/org/virtuslab/scalajs/k6/jslib/chaijs/` directory
- [ ] `ChaiJS.scala` (Expect functions)
- [ ] `Assertion.scala` (Assertion API)
- [ ] `Matchers.scala` (Custom matchers)
- [ ] `package.scala` (Scala API)
- [ ] Example: `examples/api-examples/k6-chaijs-assertions.scala`

### Additional jslib Modules (Lower Priority)
- [ ] jslib/testing (Advanced testing framework)
- [ ] jslib/aws (AWS services integration)
- [ ] jslib/http-instrumentation-* (Observability modules)

## Documentation & Quality
- [ ] Update main `README.md` with new capabilities
- [ ] Update `CLAUDE.md` with API coverage status
- [ ] Ensure all modules have ScalaDoc comments
- [ ] Create comprehensive usage documentation
- [ ] Set up CI/CD for cross-Scala version testing
- [ ] Performance benchmarking for new modules

## Testing Strategy
- [ ] Unit tests for pure Scala logic (where applicable)
- [ ] Compilation tests across all Scala versions
- [ ] Integration examples that work with k6 runtime
- [ ] Manual testing checklist for each module
- [ ] Regression testing for existing functionality

---

## Notes
- Each phase builds upon the previous one
- Phase 1 already has detailed specifications in `docs/k6-http-missing-features-spec.md`
- Follow existing code patterns and conventions
- Maintain backwards compatibility throughout
- Prioritize type safety and idiomatic Scala APIs
