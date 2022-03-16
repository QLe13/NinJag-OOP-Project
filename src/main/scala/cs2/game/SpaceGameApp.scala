package cs2.game

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import cs2.util
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import scalafx.scene.paint.Color
import scalafx.animation.AnimationTimer
import scalafx.animation.Animation

/** main object that initiates the execution of the game, including construction
 *  of the window.
 *  Will create the stage, scene, and canvas to draw upon. Will likely contain or
 *  refer to an AnimationTimer to control the flow of the game.
 */
object Images {
    val Naruto = new Image("images/Naruto.png")
    val Rasengan = new Image("images/Rasengan.png")
    val Shuriken = new Image("images/Shuriken.png")
    val Shinobi = new Image("images/Shinobi.png")
    val Background = new Image("images/Background.jpeg")
}
object SpaceGameApp extends JFXApp {
    stage = new JFXApp.PrimaryStage{
        title = "Rasengan!!!"
        scene = new Scene(1120,630) {
            val canvas = new Canvas(width.value, height.value)
            content = canvas
            val g = canvas.graphicsContext2D
            //
            val narW = 80
            val narH = 80
            //
            val bulW = 20
            val bulH = 18
            //
            val shiW = 40
            val shiH = 40
            //
            val shuW = 15
            val shuH = 35


            val player = new Player(Images.Naruto,util.Vec2(width.value/2-narW/2, height.value - narH), Images.Rasengan)
            var pBuls = collection.mutable.ListBuffer.empty[Bullet]
            var eBuls = collection.mutable.ListBuffer.empty[Bullet]
            var KeyLog = collection.mutable.Set.empty[String]
            //
            var deadCount:Int = 0
            var eneBulCount:Double = 20
            canvas.onKeyPressed =(e:KeyEvent) => {
                KeyLog += e.code.toString()
                 } 
                 canvas.requestFocus()
            canvas.onKeyReleased =(e:KeyEvent)=>{
                KeyLog -= e.code.toString()
            }
            canvas.requestFocus()

            var enemies = new EnemySwarm(2,4)
            
            val timerAll = AnimationTimer {t =>{
            g.drawImage(Images.Background,0,0,width.value,height.value)
                player.display(g,narW,narH)
                for (i<- pBuls){
                    if(i.y>=0){
                        i.timeStep()
                        i.display(g,bulW,bulH)
                    } else {
                        pBuls -= i
                    }
                }
                //can create a for loop to eliminate the overranged bullets
                enemies.display(g)
                eneBulCount -= 0.25
                if(eneBulCount==0){
                    eBuls += enemies.shoot()
                    eneBulCount = 20
                }
                if (KeyLog("LEFT") == true){
                    if(player.x>=10){
                    player.moveLeft()
                    }
                }
                if (KeyLog("RIGHT") == true){
                    if(player.x<=width.value-narW-20){
                    player.moveRight()
                    }
                }
                if (KeyLog("UP") == true){
                    if(player.y>=0){
                    player.moveUp()
                    }
                }
                if (KeyLog("DOWN") == true){
                    if(player.y<=height.value-narH-20){
                    player.moveDown()
                    }
                }
                if (KeyLog("SPACE") == true && pBuls.length == 0){
                    pBuls += player.shoot()
                }
                for (i<- eBuls){
                    if(i.y>=0){
                        i.timeStep()
                        i.display(g,shuW,shuH)
                    } else {
                        eBuls -= i
                    }
                }
                for (p<- pBuls){
                    for(e <- eBuls){
                        p.intersects(bulW,bulH,shuW,shuH,new util.Vec2(e.x,e.y))
                        if(p.intersection==true){
                            pBuls -= p
                            eBuls -= e
                            }
                        }
                    }
                for (e <- eBuls){
                    player.intersects(narW-10,narH,shiW-15,shiH-10,new util.Vec2(e.x,e.y))
                    if(player.intersection==true){
                        player.moveTo(util.Vec2(520,550))
                        eBuls -= e
                        player.intersection = false
                        deadCount += 1   
                    }
                }
                for (e <- enemies.swarm){
                    player.intersects(narW-10,narH,shiW-15,shiH-10,new util.Vec2(e.x,e.y))
                    if(player.intersection==true){
                        player.moveTo(util.Vec2(520,550))
                        player.intersection = false
                        deadCount += 1   
                    }
                    
                }
               
                for (e <- enemies.swarm){
                    for (p <- pBuls){
                    e.intersects(shiW,shiH-20,bulW,bulH,util.Vec2(p.x(),p.y()))
                    if(e.intersection == true){
                        enemies.omit(e)
                        enemies.arr = Array.range(0,enemies.swarm.length)
                        pBuls -= p
                    }
                    }
                }
                if(enemies.swarm.length==0){
                    enemies = new EnemySwarm(2,4)
                }

            //Dont touch the line below this cause these are ending braces        
            }
            }
            timerAll.start()
        } 
   
    }

}