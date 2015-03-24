package drawinghouses

import org.scalatest.{Matchers, FlatSpec}

import scala.collection.immutable._

class SimpleGraphUtilsTest extends FlatSpec with Matchers{

  behavior of "'SimpleGraphUtils'"

  it must " be able to calculate all the ways to connect all the vertices using all the edges only one time" in {
    val edges = Set(1 -> 2, 2 -> 3, 3 -> 1)
    val graph = SimpleGraph(edges)

    SimpleGraphUtils.nbWaysAllEdgesUsedOnce(graph) shouldEqual (3 * 2)
  }

}
