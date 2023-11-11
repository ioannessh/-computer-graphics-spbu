package dsu

class DSU(val n: Int) {
  private var parent: Array[Int] = {
    val arr = Array.fill(n)(0)
    arr.foldRight(0)((a, acc) => { arr(acc) = acc; acc + 1 })
    arr
  }
  private var rank: Array[Int] = Array.fill(n)(0)

  def find_set(v: Int): Int = {
    if (v < 0 || parent.length <= v)
      return -1
    else if (v != parent(v))
      parent(v) = find_set(parent(v))
    parent(v)
  }

  def union_set(u: Int, v: Int): Unit = {
    var a: Int = find_set(u)
    var b: Int = find_set(v)
    if (a != b) {
      if (rank(a) < rank(b)) {
        val c = a
        a = b
        b = c
      }
      parent(b) = a
      if (rank(a) == rank(b)) {
        rank(a) += 1
      }
    }
  }
}
