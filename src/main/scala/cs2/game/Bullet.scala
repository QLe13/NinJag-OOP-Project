package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2

/** Representation of a bullet/projectile for a simple game based on sprites.
 *  Handles all information regarding a bullet's position, movements, and 
 *  abilities.
 *  
 *  @param pic the image representing the bullet
 *  @param initPos the initial position of the '''center''' of the bullet
 *  @param vel the initial velocity of the bullet
 */
class Bullet(pic:Image, var initPos:Vec2, private var vel:Vec2) extends Sprite(pic, initPos) {

  /** advances the position of the Bullet over a single time step
   * 
   *  @return none/Unit
   */
  var x = initPos.x
  var y = initPos.y

  val dash = vel
  val Pic = pic
  def timeStep():Unit = {
    move(vel)
  }
  
}
