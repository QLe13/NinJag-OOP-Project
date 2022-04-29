package cs2.adt



import org.junit._
import org.junit.Assert._
import scala.util.Random

class DEPQTester {
var q:DEPQ[Int] = null

  @Before def init():Unit = {
    q = new DEPQ[Int]()
  }

  @Test def checkIsEmptyInitially():Unit ={
      assertTrue(q.isEmpty())
  }
  @Test def correctAddandMin():Unit ={
      for(i<- 1 to 50 by 2){
          q.add(i)
          assertTrue(q.peekMax()==i)
      }
      for(i<- 2 to 50 by 2){
          q.add(i)
      }
      for(i<- 1 to 50){
          assertTrue(q.min()==i)
      }
      assertTrue(q.isEmpty()&&q.length()==0)
      
  }
  @Test def testIsEmptyFront():Unit = {
    assertTrue(q.isEmpty())
    q.add(1)
    assertFalse(q.isEmpty())
    q.peekMax()
    assertFalse(q.isEmpty())
    q.max()
    assertTrue(q.isEmpty())
  }

  @Test def testIsEmptyMany():Unit = {
    assertTrue(q.isEmpty())
    for(i <- 0 until 500){
      q.add(Random.nextInt())
    }
    for(i <- 0 until 500){
      q.max()
    }
    assertTrue(q.isEmpty)
  }

  @Test def testMax():Unit = {
    assertTrue(q.isEmpty())
    q.add(1)
    q.add(3)
    q.add(2)
    assertTrue(q.max() == 3)
    assertTrue(q.max() == 2)
    assertTrue(q.max() == 1)
    assertTrue(q.isEmpty())
  }

  @Test def testMin():Unit = {
    assertTrue(q.isEmpty())
    q.add(2)
    q.add(3)
    q.add(4)
    q.add(1)
    assertTrue(q.min() == 1)
    assertTrue(q.min() == 2)
    assertTrue(q.min() == 3)
    assertTrue(q.min() == 4)
    assertTrue(q.isEmpty())
  }

  @Test def testPeekMax():Unit = {
    assertTrue(q.isEmpty())
    q.add(2)
    assertTrue(q.peekMax() == 2)
    q.add(5)
    assertTrue(q.peekMax() == 5)
    q.add(4)
    assertFalse(q.peekMax() == 4)
    assertTrue(q.peekMax() == 5)
  }

  @Test def testPeekMin():Unit = {
    assertTrue(q.isEmpty())
    q.add(2)
    assertTrue(q.peekMax() == 2)
    q.add(5)
    assertTrue(q.peekMax() == 5)
    q.add(4)
    assertFalse(q.peekMax() == 4)
    assertTrue(q.peekMax() == 5)
  }

  @Test def testAdd():Unit = {
    assertTrue(q.isEmpty())
    q.add(1)
    assertFalse(q.isEmpty())
    q.add(2)
    q.add(3)
    q.add(4)
    assertFalse(q.isEmpty())
  }

  @Test def testOrderMax():Unit = {
    for(i <- 0 until 50){
      q.add(Random.nextInt())
    }
    var z:Int = q.max
    var y:Int = q.max
    while(!q.isEmpty){
      z = y
      y = q.max()
      assertTrue(z > y)
    }
  }
  
  @Test def testOrderMin():Unit = {
    for(i <- 0 until 50){
      q.add(Random.nextInt())
    }
    var z:Int = q.min
    var y:Int = q.min
    while(!q.isEmpty){
      z = y
      y = q.min()
      assertTrue(z < y)
    }
  }


}
