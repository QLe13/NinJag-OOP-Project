package cs2.util
class Vec2 (var x:Double, var y:Double) {
  /** DO NOT MODIFY THE FOLLOWING TOSTRING METHOD **/
  override def toString():String = "("+x+","+y+")"
  //Methods for addition and subtraction of vectors
  def +  (other:Vec2):Vec2 = {
    if(this.x !=0 && this.y !=0 && other.x != 0 && other.y !=0){
      new Vec2(this.x+other.x,this.y + other.y)
    } else this
  }
  def += (other:Vec2):Unit = {
    if(this.x !=0 && this.y !=0 && other.x != 0 && other.y !=0){
      this.x = this.x + other.x
      this.y = this.y + other.y
    }
  }
  
  def -  (other:Vec2):Vec2 = {
    if(this.x !=0 && this.y !=0 && other.x != 0 && other.y !=0){
      new Vec2(this.x-other.x,this.y-other.y)
    } else this
  }
  def -= (other:Vec2):Unit = {
    if(this.x !=0 && this.y !=0 && other.x != 0 && other.y !=0){
      this.x = this.x - other.x
      this.y = this.y - other.y
    }
  }

  //Methods for multiplication and division of vectors by a scalar (non-vector)
  def *  (scalar:Double):Vec2 = {
    if(this.x != 0 && this.y != 0) {
      new Vec2(this.x * scalar, this.y * scalar)
    } else this
  }
  def *= (scalar:Double):Unit = {
    if(this.x != 0 && this.y != 0) {
      this.x = this.x * scalar
      this.y = this.y * scalar
    }
  }

  def /  (scalar:Double):Vec2 = {
    if(this.x != 0 && this.y != 0 && scalar !=0){
      new Vec2(this.x / scalar, this.y / scalar)
    } else this
  }
  def /= (scalar:Double):Unit = {
    if(this.x != 0 && this.y != 0 && scalar !=0){
      this.x = this.x / scalar
      this.y = this.y /scalar
    } else if (scalar != 0){
      this.x = this.x / scalar
      this.y = this.y /scalar
    }
  }

  //Methods to determine the length of a vector (magnitude and length should return the same value)
  def magnitude():Double = {
    if(this.x != 0 && this.y != 0){
     math.sqrt(math.pow(this.x,2)+math.pow(this.y,2))
    } else {
       0
    }
  }
  def length():Double = {
    if(this.x != 0 && this.y != 0){
     math.sqrt(math.pow(this.x,2)+math.pow(this.y,2))
    } else {
       0
    }
  }
  
  //Methods to calculate the dot product (same returns)
  def dot(other:Vec2):Double = {
    if(this.x != 0 && this.y != 0 && other.x != 0 && other.y != 0){
      this.x * other.x + this.y * other.y
    } else {
      0
    }
  }
  def **(other:Vec2):Double = {
    if(this.x != 0 && this.y != 0 && other.x != 0 && other.y != 0){
      this.x * other.x + this.y * other.y
    } else {
      0
    }
  }
  
  //Methods to determine the angle between 2 vectors (same returns)
  def angleBetween(other:Vec2):Double = {
    if(this.length!=0 && other.length != 0) math.acos(this.dot(other)/(this.length()*other.length()))
    else 0
  }
  def <>(other:Vec2):Double = {
    if(this.length!=0 && other.length != 0) math.acos(this.dot(other)/(this.length()*other.length()))
    else 0
  }

  //Methods to return a new vector that is in the same direction, but length 1 (same returns)
  def normalize():Vec2 = {
    if(this.x !=0 && this.y !=0) new Vec2(this.x/this.length,this.y/this.length)
    else new Vec2(0,0)
  }
  def unary_~ : Vec2 = {
    if(this.x !=0 && this.y !=0) new Vec2(this.x/this.length,this.y/this.length)
    else new Vec2(0,0)
  }

  //A clone operator can be useful when making "deep" copies of objects
  override def clone():Vec2 = { ??? }
}

object Vec2 {
  //These apply methods can be used for constructing Vec2 instances without saying "new"
  /** DO NOT CHANGE THE FOLLOWING THREE APPLY METHODS**/
  def apply(myX:Double, myY:Double):Vec2 = { new Vec2(myX, myY) }
  def apply(toCopy:Vec2):Vec2 = { new Vec2(toCopy.x, toCopy.y) }
  def apply():Vec2 = { new Vec2(0, 0) }

  def main(args:Array[String]):Unit = {
    /** Your solution to the physics problem described should be calculated here.
     *  Remember to print out your answer using println.
     */
     val startPoint = apply(-98.4936,29.4241)
     val Austin = apply(-97.7431,30.2672)
     val distance = (Austin-startPoint).length/1.5 * 4
     val unitvec = (Austin-startPoint).normalize()
     startPoint += unitvec*distance
     //printing answer
     if(startPoint.x > 0 && startPoint.y > 0) println(startPoint.y + " N, "+startPoint.x+" E")
     if(startPoint.x < 0 && startPoint.y > 0) println(startPoint.y + " N, "+ -startPoint.x+" W")
     if(startPoint.x > 0 && startPoint.y < 0) println(-startPoint.y + " S, "+ startPoint.x+" E")
     if(startPoint.x < 0 && startPoint.y < 0) println(-startPoint.y + " S, "+ -startPoint.x+" W")
  }
}
//just a comment line to check the github