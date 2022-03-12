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
            val narW = 80
            val narH = 80
            val initpos = new util.Vec2(width.value/2-narW/2,height.value-narH)
            val bulW = 20
            val bulH = 18
            val shiW = 40
            val shiH = 40
            val shuW = 15
            val shuH = 35
            val player = new Player(Images.Naruto,initpos, Images.Rasengan)
            var pBuls = collection.mutable.ListBuffer.empty[Bullet]
            var eBuls = collection.mutable.ListBuffer.empty[Bullet]
            var eneCount:Double = 0
            canvas.onKeyPressed =(e:KeyEvent) => {
                if (e.code == KeyCode.Left){
                    if(player.x>=0){
                    player.moveLeft()
                    }
                }
                if (e.code == KeyCode.Right){
                    if(player.x<=width.value-narW){
                    player.moveRight()
                    }
                }
                if (e.code == KeyCode.Space){
                    pBuls += player.shoot()
                }
                 } 
                 canvas.requestFocus()
                val enemies = new EnemySwarm(2,4)
            val timerAll = AnimationTimer (t =>{
            g.drawImage(Images.Background,0,0,width.value,height.value)
                player.display(g,narW,narH)
                for (i<- pBuls){
                    i.timeStep()
                }
                for(i <-0 to pBuls.length -1){
                    pBuls(i).display(g,bulW,bulH)
                }
                enemies.display(g)
                eneCount += 0.25
                if(eneCount%6==0){
                    eBuls += enemies.shoot()
                }
                for (i <- eBuls){
                    i.timeStep()
                    
                }
                for (i <- 0 to eBuls.length-1){
                    eBuls(i).display(g,shuW,shuH)
                }
            })
            timerAll.start()
        } 
   
    }

}