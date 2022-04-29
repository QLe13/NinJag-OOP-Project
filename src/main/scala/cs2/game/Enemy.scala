package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2

/** An enemy representation for a simple game based on sprites. Handles all
 *  information regarding the enemy's position, movements, and abilities.
 *
 *  @param pic the image representing the enemy
 *  @param initPos the initial position of the '''center''' of the enemy
 *  @param bulletPic the image of the bullets fired by this enemy
 */
class Enemy(pic:Image,initPos:Vec2, private val bulletPic:Image) extends Sprite(pic, initPos) {

  /** creates a new Bullet instance beginning from this Enemy, with an appropriate velocity
   *
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def x():Double={
    pos.x
  }
  def y():Double={
    pos.y
  }
  var theta:Double = 0
  var direction:Vec2 = new Vec2(0,0)
  
  def beStatic():Unit = {
    direction = new Vec2(5*math.cos(theta),5* math.sin(theta))
    theta += math.Pi/40
    move(direction)
  }
  
  def shoot():Bullet = {
    val vel = new Vec2(0,15)
    new Bullet(Images.Shuriken,pos + new Vec2(15,15),vel)
  }
  override def clone():Enemy ={
    var enemy = new Enemy(pic,initPos.clone(),bulletPic)
    enemy
  }

}
