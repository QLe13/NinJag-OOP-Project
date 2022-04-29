package cs2.adt



abstract class DoubleEndPriorityQueue[A <% Ordered[A]] {
  def isEmpty():Boolean //Return true if there are no elements in the DEPQ
  def add(elem: A):Unit //Add an element to the DEPQ
  def peekMax():A //Return the largest element in the DEPQ
  def max():A //Return AND Remove the largest element from the DEPQ
  def peekMin():A //Return the smallest element in the DEPQ
  def min():A //Return AND Remove the smallest element from the DEPQ
  def length():Int //Return the lenght of the data structure
}

object DoubleEndPriorityQueue {
  def apply[A <% Ordered[A]]():DEPQ[A] = {
    new DEPQ[A]()
  }
  /*def main(args:Array[String]):Unit = {
    var d = new DEPQ[Int]()
   for(i<- 1 to 50 by 2){
          d.add(i)
      }
      for(i<- 2 to 50 by 2){
          d.add(i)
      }
      for(i<- 1 to 50){
          println(d.max())
      }
      println(d.isEmpty())
     
  }*/
//
}