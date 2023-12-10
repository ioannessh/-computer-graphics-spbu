package dsu

import scala.annotation.tailrec
import scala.collection.mutable

class DSU(val n: Int) {
  private var parent: mutable.Map[Int, Int] = mutable.Map.empty
  private var rank: mutable.Map[Int, Int] = mutable.Map.empty

  def find_set(v: Int): Int = {
    @tailrec
    def go(v: Int, lst: List[Int]): Int = {
      if (v == parent.getOrElse(v, v)) {
        lst.foreach(parent(_) = v)
        v
      }
      else {
        go(parent(v), v :: lst)
      }
    }
    go(v, List.empty)
  }

  def union_set(u: Int, v: Int): Unit = {
    var a: Int = find_set(u)
    var b: Int = find_set(v)
    if (a != b) {
      if (rank.getOrElse(a, 0) < rank.getOrElse(b, 0)) {
        val c = a
        a = b
        b = c
      }
      parent(b) = a
      if (rank.getOrElse(a, 0) == rank.getOrElse(b, 0)) {
        rank(a) =rank.getOrElse(a, 0) + 1
      }
    }
  }
}
