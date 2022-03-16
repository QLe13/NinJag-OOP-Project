package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/** A graphical sprite object used for gaming or other visual displays
 *  
 *  @constructor create a new sprite based on the given image and initial location
 *  @param img the image used to display this sprite
 *  @param pos the initial position of the '''center''' of the sprite in 2D space
 */
abstract class Sprite (protected val img:Image, protected var pos:Vec2) {

  /** moves the sprite a relative amount based on a specified vector
   *  
   *  @param direction - an offset that the position of the sprite should be moved by
   *  @return none/Unit
   */
  def move (direction:Vec2):Unit = {
    pos += direction
   }
  
  /** moves the sprite to a specific location specified by a vector (not a relative movement)
   *  
   *  @param location - the new location for the sprite's position
   *  @return none/Unit
   */
  def moveTo (location:Vec2):Unit = {
    pos = location
   }
  /** Method to display the sprite at its current location in the specified Graphics2D context
   *  
   *  @param g - a GraphicsContext object capable of drawing the sprite
   *  @return none/Unit
   */
  
  def display (g:GraphicsContext,W:Double,H:Double):Unit = {
    g.drawImage(img,pos.x,pos.y,W,H)
  }
  var intersection: Boolean = false
  def intersects (width1:Int, height1: Int, width2:Int, height2:Int, location: Vec2) {
    if (!((this.pos.x+width1 < location.x) || (this.pos.x > location.x+width2)) && !((this.pos.y+height1 < location.y) || (this.pos.y > location.y+height2))) {
       intersection = true
    } else {
      intersection = false
    }
  }

}
//