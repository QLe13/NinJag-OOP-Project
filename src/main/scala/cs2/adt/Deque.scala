package cs2.adt

abstract class Deque[A]{
    def prepend(elem:A) 
    def append(elem:A)
    def front():A
    def back():A
    def peekFront():A
    def peekBack():A
    def isEmpty():Boolean 
    def length():Int
}



object Deque {
  def apply[A:Manifest]():Deque[A] = {
    new TheDeque[A]()
  }
//
}
