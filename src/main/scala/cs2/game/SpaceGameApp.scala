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
        
            canvas.onKeyPressed =(e:KeyEvent) => {
                if (e.code == KeyCode.Left){
                    if(player.x>=0){
                    g.setFill(Color.White)
                    g.fillRect(player.x,player.y,narH,narW)
                    player.moveLeft()
                    player.display(g,narW,narH)
                    }
                }
                if (e.code == KeyCode.Right){
                    if(player.x<=width.value-narW){
                    g.setFill(Color.White)
                    g.fillRect(player.x,player.y,narH,narW)
                    player.moveRight()
                    player.display(g,narW,narH)
                    }
                }
                if (e.code == KeyCode.Space){
                    val bul = player.shoot()
                    val timer = AnimationTimer (t =>{
                        g.drawImage(Images.Rasengan,bul.x,bul.y,bulW,bulH)
                        var initY = bul.y + bul.dash.y
                        bul.y -= bul.dash.y
                        g.setFill(Color.White)
                        g.fillRect(bul.x,initY,bulW,bulH)
                    }) 
                    timer.start
                }
                
            }
            canvas.requestFocus() 
            val timer4 = AnimationTimer (t => {
                player.display(g,narW,narH)
            })
            timer4.start()

            val enemies = new EnemySwarm(2,4)
            val timer3 = AnimationTimer (t =>{
                enemies.display(g)
            })
            timer3.start()
            var eneCount:Double = 0
            val timer1 = AnimationTimer (t=>{
                val eneShoot = enemies.shoot()
                eneCount += 0.25
                if(eneCount%6==0.0){val timer2 = AnimationTimer (t=>{
                    g.drawImage(Images.Shuriken,eneShoot.x,eneShoot.y,shuW,shuH)
                var initY = eneShoot.y - shuH
                        eneShoot.y -= eneShoot.dash.y
                        g.setFill(Color.White)
                        g.fillRect(eneShoot.x,initY,shuW,shuH)
                })
                timer2.start()}
            })
            timer1.start()
        } 
   
    }

}