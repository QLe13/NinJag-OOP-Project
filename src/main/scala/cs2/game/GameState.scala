package cs2.game

import cs2.util.Vec2
import scala.collection.mutable

class GameState (lifeRemaining:Int,score:Int, player:Player, enemies:mutable.ListBuffer[Enemy],pBullets:mutable.ListBuffer[Bullet],eBullets:mutable.ListBuffer[Bullet]){
    var LifeRemaining = lifeRemaining
    var Score = score
    var Player = player 
    var Enemies = enemies
    var PBullets = pBullets
    var EBullets = eBullets
}