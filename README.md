# Scala2HTML

Small library to write HTML using simple Scala data structures.

## Installation

Add dependency to `build.sbt`.

```scala
resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "com.github.droptheplot" % "scala2html" % "master-SNAPSHOT"
```

Import single `Tag` class and it's special constructor `<`.
```scala
import scala2html.{<, Tag}
```

Enable postfix operator notation to use `/>` method for empty tags (optional).
```scala
import scala.language.postfixOps
```

## Usage

You'll need only these functions to remember:
* `<` - Create a new `Tag`.
* `>` - Add `Tag` / `String` inside `Tag`.
* `/>` - Make `Tag` empty.

```scala
import scala.language.postfixOps
import scala2html.{<, Tag}

<("div")
// <div></div>

<("div") > "Hello!"
// <div>Hello!</div>

<("div", ("class", "container"))
// <div class="container"></div>

<("ul", ("class", "list-group")) > (
  <("div", ("class", "list-group-item active"), ("id", "1")) > "First",
  <("div", ("class", "list-group-item"), ("id", "2")) > "Second")
// <ul class="list-group">
//   <li class="list-group-item active" id="1">First</div>
//   <li class="list-group-item" id="2">Second</div>
// </ul>

<("img", ("src", "image.png")) />
// <img src="image" />

