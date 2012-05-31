# Introduction to Functional Programming Patterns #
_in Scala_

Authored by: Josh Suereth (jsuereth)

## Description ##

Asycnhronous APIs are all the rage, however most APIs remain clunky.  This talk embarks on journey to glue together some asynchronous calls in a way that is still asynchronous.  After acheiving the concurrent nirvana, one is faced with the issue of testing.   With a few simple functional abstractions, the same code can be used in both a synchronous and asynchronous manner.

In particular, [this talk](http://jsuereth.com/intro-to-fp) covers:

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


# LICENSE #

Copyright (c) 2012, Joshua Suereth
All rights reserved.


## Slides ##
All slides are released [Creative Commons Attribution 3.0](http://creativecommons.org/licenses/by/3.0/us/)

<a rel="license" href="http://creativecommons.org/licenses/by/3.0/us/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by/3.0/us/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/us/">Creative Commons Attribution 3.0 United States License</a>.

## Code ##
All code is released under the Apache License v2.0.


Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
