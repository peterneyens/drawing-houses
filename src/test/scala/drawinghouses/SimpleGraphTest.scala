package drawinghouses

import org.scalatest.{Matchers, FlatSpec}

import scala.collection.immutable._

class SimpleGraphTest extends FlatSpec with Matchers{

  behavior of "'SimpleGraph'"

  it must "accept edges" in {
    val edges = Set(1 -> 2, 2 -> 3, 3 -> 1)
    val graph = SimpleGraph(edges)

    graph.edges shouldEqual edges
  }

  it must "have vertices" in {
    val edges = Set(1 -> 2, 2 -> 3, 3 -> 1)
    val graph = SimpleGraph(edges)
    val expectedVertices = Set(1, 2, 3)

    graph.vertices shouldEqual expectedVertices
  }

  it must "return edges start/ending in vertex" in {
    val edges = Set(1 -> 2, 2 -> 3, 3 -> 1)
    val graph = SimpleGraph(edges)
    val expectedEdges =  Set(1 -> 2, 3 -> 1)

    graph.edgesInVertex(1) shouldEqual expectedEdges
  }
}
