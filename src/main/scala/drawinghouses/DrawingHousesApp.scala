package drawinghouses

import scala.collection.immutable._

object DrawingHousesApp extends App {

  // The House :

  //         5
  //        ---
  //      --   --
  // 3  +---------+  4
  //    | --   -- |
  //    |   ---   |
  //    | --   -- |
  // 1  +---------+  2

  val houseGraph = SimpleGraph(Set(
    1 -> 2, 1 -> 3, 1 -> 4,
    2 -> 3, 2 -> 4,
    3 -> 4, 3 -> 5,
    4 -> 5
  ))

  val waysToDrawHouse = SimpleGraphUtils.getWaysAllEdgesUsedOnce(houseGraph)

  // sequence of edges -> vertices
  val vertexSequences = waysToDrawHouse.map { lineEdges =>
    val (firstEdge, secondEdge) = (lineEdges.head, lineEdges(1))
    val start = if (firstEdge._1 != secondEdge._1 && firstEdge._1 != secondEdge._2) firstEdge._1 else firstEdge._2

    lineEdges.foldLeft(Seq(start)){ (vertices, edge) =>
      val nextVertex = if (vertices.last != edge._1) edge._1 else edge._2
      vertices :+ nextVertex
    }
  }

  println(s"nb ways to draw the house without lifting your pen = ${waysToDrawHouse.length}")
  println()
  for(verticesOneWay <- vertexSequences)
    println(verticesOneWay.mkString("-"))
}
