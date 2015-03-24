package drawinghouses

import scala.collection.immutable._

object SimpleGraphUtils {
  type Edge[A] = (A, A)

  /**
   * The number of ways how the specified graph can be travelled, using every edge once.
   * @param graph A SimpleGraph
   * @tparam A
   * @return The number of ways the specified graph can be travelled
   */
  def nbWaysAllEdgesUsedOnce[A](graph: SimpleGraph[A]) = getWaysAllEdgesUsedOnce(graph).length

  /**
   * Get all the ways how the specified graph can be travelled, using every edge once.
   * @param graph A SimpleGraph
   * @tparam A
   * @return all the possible ways (a sequence of edges) how the specified graph can be travelled
   */
  def getWaysAllEdgesUsedOnce[A](graph: SimpleGraph[A]): Seq[Seq[Edge[A]]] = {

    // how many ways starting with the specified vertex
    def getWaysFromVertex(vertex: A): Seq[Seq[Edge[A]]] = {
      // get lines (a sequence of edges) in the graph starting from the specified vertex in the specified graph
      def getLinesFromVertex(vertex: A, graphLeft: SimpleGraph[A], line: Seq[Edge[A]]): Seq[Seq[Edge[A]]] = {
        val nextEdges = graphLeft.edgesInVertex(vertex)
        if (nextEdges.isEmpty) Seq(line)
        else {
          nextEdges.to[Seq].flatMap { edge =>
            val nextVertex = if (edge._1 == vertex) edge._2 else edge._1
            getLinesFromVertex(nextVertex, SimpleGraph(graphLeft.edges - edge), line :+ edge)
          }
        }
      }

      // only lines which use all the edges are OK
      getLinesFromVertex(vertex, graph, Seq.empty)
        .filter(line => line.length == graph.edges.size)
    }

    val startVertices = graph.vertices
    startVertices.to[Seq].flatMap { startVertex =>
      getWaysFromVertex(startVertex)
    }
  }
}
