package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2

/** The player representation for a simple game based on sprites. Handles all
 *  information regarding the player's positions, movements, and abilities.
 *
 *  @param avatar the image representing the player
 *  @param initPos the initial position of the '''center''' of the player
 *  @param bulletPic the image of the bullets fired by this player
 */
class Player(avatar:Image, initPos:Vec2, private val bulletPic:Image) extends Sprite(avatar, initPos) {

  /** moves the player sprite one "step" to the left.  The amount of this
   *  movement will likely need to be tweaked in order for the movement to feel
   *  natural.
   *
   *  @return none/Unit
   */
  var x = initPos.x
  var y = initPos.y
  def moveLeft():Unit = {
      move(new Vec2(-10,0))
      x = initPos.x
      y = initPos.y
  }

  /** moves the player sprite one "step" to the right (see note above)
   *
   *  @return none/Unit
   */
  def moveRight():Unit = {
      move(new Vec2(10,0))
      x = initPos.x
      y = initPos.y
  }

  /** creates a new Bullet instance beginning from the player, with an
   *  appropriate velocity
   *
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def shoot():Bullet = {
    val bulInitPos = new Vec2(30,-30) + initPos
    val vel = new Vec2(0,20)
    new Bullet(Images.Rasengan,bulInitPos,vel)
  }

}
