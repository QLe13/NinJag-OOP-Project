package cs2.adt


class TheDeque[A] extends Deque[A]{
    class EmptyQueueException extends Exception("Empty Queue")

private class Node (val data:A, var prev:Node, var next:Node)
private var hed:Node = null
private var end:Node = null
private var len:Int = 0
    
def prepend(elem:A)={
if(hed == null && end == null){
    hed = new Node(elem,null,null)
    end = hed
    len +=1
}else{
hed.prev = new Node(elem,null,hed)
hed = hed.prev
len +=1
}
}
def append(elem:A)={
if(hed == null && end == null){
    hed = new Node(elem,null,null)
    end = hed
    len +=1
}else{
end.next = new Node(elem,end,null)
end = end.next
len +=1
}
}
def front():A={
if(isEmpty()) throw new EmptyQueueException
if(hed.next==null){
    val ret = hed.data
    hed = null
    end = hed
    len -=1
    ret
    
}else {
    val ret:A = hed.data
    hed.next.prev= null
    hed = hed.next
    len -=1
    ret
    
}
}
def back():A={
if(isEmpty()) throw new EmptyQueueException
if(end.prev == null){
    val ret = end.data
    end = null
    hed = end
    len -=1
    ret
    
}else{
    val ret = end.data
    end.prev.next = null
    end = end.prev
    len -=1
    ret
    
}
}
def peekFront():A={
    if(isEmpty()) throw new EmptyQueueException
        hed.data
    }
def peekBack():A={
    if(isEmpty()) throw new EmptyQueueException  
        end.data
    }

def isEmpty():Boolean = { hed == null }

def length():Int={
len
}

}