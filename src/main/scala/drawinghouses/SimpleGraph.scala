package drawinghouses

import scala.collection.immutable._

/**
 * A simple representation of a graph with the specified edges.
 * @param edges The edges of this graph.
 * @tparam A Type of the vertices.
 */
case class SimpleGraph[A](edges: Set[(A, A)]) {
  /**
   * The vertices of this simple graph.
   */
  val vertices = edges.flatMap(e => Set(e._1, e._2))

  /**
   * The edges starting/ending in the specified vertex.
   * @param vertex the vertex
   * @return The edges starting/ending in this vertex.
   */
  def edgesInVertex(vertex: A) =
    edges.filter{ case (a, b) => vertex == a || vertex == b }
}