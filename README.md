# Introduction to Functional Programming Patterns #
_in Scala_

Authored by: Josh Suereth (jsuereth)

## Description ##

Asycnhronous APIs are all the rage, however most APIs remain clunky.  This talk embarks on journey to glue together some asynchronous calls in a way that is still asynchronous.  After acheiving the concurrent nirvana, one is faced with the issue of testing.   With a few simple functional abstractions, the same code can be used in both a synchronous and asynchronous manner.

In particular, this talk covers:

* Monadic workflows
* Applicative Functors and joined computation
* Essence of Iteration

As seen in the Akka futures library.

## Requirements ##

- sbt(0.11.3)
- Jekyll

## Acknowledgements ##

Thanks to all my FP peeps who drilled this stuff into me until I realized it was useful.

This presentation uses:

- [hekyll](https://github.com/bmcmurray/hekyll)
- [impress.js](https://github.com/bartaz/impress.js)
- [Jekyll](https://github.com/mojombo/jekyll)
- [Simple Build Tool](https://github.com/harrah/xsbt)
- [Scala](https://github.com/scala/scala)

## Resources ##

- [Scala in Depth](http://manning.com/suereth)
- [Scala documentation](http://docs.scala-lang.org)
- [Akka documentation](http://akka.io/docs/)
- [Essence of the Iterator Pattern](http://www.cs.ox.ac.uk/jeremy.gibbons/publications/iterator.pdf)
- [Blog: Essence of Iterator Pattern](http://etorreborre.blogspot.com/2011/06/essence-of-iterator-pattern.html)

