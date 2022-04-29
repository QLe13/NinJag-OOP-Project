package cs2.adt

class DEPQ[A <% Ordered[A]] extends DoubleEndPriorityQueue[A] {
  class Node(var data:A, var prev:Node, var next:Node)
  var head:Node = null
  var tail:Node = null
  
  
  def add(elem: A):Unit = {
      if(head == null){
          tail = new Node(elem, null, null)
          head = tail
      }
      else if(elem>head.data){
        head.prev = new Node(elem,null,head)
        head = head.prev
      } else {
        var rover = head
        while(rover.next != null && rover.next.data > elem) {
        rover = rover.next
      } 
      if(rover == tail){
        rover.next = new Node(elem,rover,null)
        tail = rover.next
      }else {
        rover.next.prev = new Node(elem,rover,rover.next)
        rover.next = rover.next.prev
      }
      }
  }
  def peekMax():A = {
      head.data
  }
  def max():A = {
      var ret = head.data
      head = head.next
      if(head == null){
          tail = head
      }
    
      ret
  }
  def peekMin():A = {
      tail.data
  }
  def min():A = {
      var ret = tail.data
      tail = tail.prev
      if(tail == null){
          head = tail
      }
      ret
  }

  def isEmpty():Boolean = {
      head == null && tail == null
  }
  def length():Int = {
      var ret:Int = 0
      while(head!= null){
        head = head.next
        ret += 1
      }
      ret
  }
}