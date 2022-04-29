package cs2.adt

package cs2.adt

import org.junit._
import org.junit.Assert._

class DequeTester {
  var d:TheDeque[Int] = null

  @Before def init():Unit = {
    d = new TheDeque[Int]()
  }


  @Test def checkIsEmptyInitially():Unit = {
    assertTrue(d.isEmpty()&&d.length()==0)
  }

  
  @Test def checkCorrectPrependAndAppend():Unit = {
    for(i <- 1 to 12000000){
      if(i%2==0){
        d.append(i)
        assertTrue(d.peekBack==i)
      }else{
        d.prepend(i)
        assertTrue(d.peekFront==i)
      }
  }
  assertTrue(d.length()==12000000)
  for(i<-1 to 6000000){
    d.front()
    d.back()
  }
  assertTrue(d.isEmpty())
  }
  @Test def checkEmptyAfterFront():Unit ={
    
    for(i<-1 to 50){
      d.prepend(i)
      d.front()
    }
    assertTrue(d.isEmpty()&&d.length()==0)
  }
  @Test def checkEmptyAfterBack():Unit ={
    
    for(i<-1 to 50){
      d.prepend(i)
      d.back()
    }
    assertTrue(d.isEmpty()&&d.length()==0)
  }
  @Test def checkCorrectPeekFrontAndBack:Unit={
    for(i<-1 to 50){
      d.prepend(i)
      assertTrue(d.peekFront()==d.front())
    }
    for(i<- 1 to 50){
      d.append(i)
      assertTrue(d.peekBack()==d.back())
    }
  }
  
  @Test def checkContinuousAddingAndDropping:Unit ={
    for(i<- 1 to 100){
      d.append(i)
      assertTrue(!d.isEmpty()&&d.length()==1)
      assertTrue(d.peekBack()==d.front())
    }
    assertTrue(d.isEmpty()&&d.length()==0)
    for(i<- 1 to 100){
      d.prepend(i)
      assertTrue(!d.isEmpty()&&d.length()==1)
      assertTrue(d.peekFront()==d.back())
    }
    assertTrue(d.length()==0)
  }

  
 

  


}



