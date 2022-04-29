package cs2.game

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import scalafx.scene.paint.Color
import scalafx.animation.AnimationTimer
import scalafx.animation.Animation
import scala.collection.mutable
import scala.collection.immutable
import scalafx.scene.text.Font

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
    val Background = new Image("images/Background.jpeg")//I prefer images/Background2.jpeg though
    val gameStartTitle = new Image("images/gameTitle.png")
    val gameSub = new Image("images/gameSub.png")
    val village = new Image("images/Village.png")
    val gameOver = new Image("images/gameOver.png")
    val gameEndBack = new Image("images/gameEndBack.png")
    val newGame = new Image("images/newGame.png")
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
            var pBulsToRemove = mutable.ListBuffer.empty[Bullet]
            var eBulsToRemove = mutable.ListBuffer.empty[Bullet]
            var eToRemove = mutable.ListBuffer.empty[Enemy]
            var player = new Player(Images.Naruto,Vec2(width.value/2-narW/2, height.value - narH), Images.Rasengan)
            var pBuls = mutable.ListBuffer.empty[Bullet]
            var eBuls = mutable.ListBuffer.empty[Bullet]
            var KeyLog = mutable.Set.empty[String]
            var eneBulToAdd = mutable.ListBuffer.empty[Bullet]
            var globalAlphaVal:Double = 0
            //gamestate
            var gameState = mutable.Stack.empty[GameState]

            var deadEneCount:Int = 0
            var lifeRemaining:Int = 3
            var eneBulCount:Double = 20
            var theta:Double = 0
            var motionCounter:Double = 300
            val downMotion = new Vec2(0,2)
            var stateLength:Double = 0
            
            //
            var showStartScreen:Boolean = true
            var enemies = new EnemySwarm(2,4)
            canvas.onKeyPressed =(e:KeyEvent) => {
                KeyLog += e.code.toString()
                 } 
                 canvas.requestFocus()
            canvas.onKeyReleased =(e:KeyEvent)=>{
                KeyLog -= e.code.toString()
            }
            canvas.requestFocus()

            

            def saveGameState() = {
                var pBulsC = pBuls.clone()
                for(x<- 0 until pBuls.length){
                    pBulsC(x)=pBuls(x).clone()
                }
                var eBulsC = eBuls.clone()
                for(x<- 0 until eBuls.length){
                    eBulsC(x)=eBuls(x).clone
                }
                var swarmC = enemies.swarm.clone()
                for(x<- 0 until enemies.swarm.length){
                    swarmC(x)=enemies.swarm(x).clone()
                }
                var currentState = new GameState(lifeRemaining,deadEneCount,player.clone(),swarmC,pBulsC,eBulsC)
                gameState.push(currentState)
            }
            def recoverGameState() = {
                var previousState = gameState.pop()
                lifeRemaining = previousState.LifeRemaining
                deadEneCount = previousState.Score
                player = previousState.Player
                enemies.swarm = previousState.Enemies
                pBuls = previousState.PBullets
                eBuls = previousState.EBullets
            }
            
            val timerAll = AnimationTimer {t =>{
                if(showStartScreen == false && lifeRemaining != 0 && KeyLog("R")==false) {
                g.drawImage(Images.Background,0,0,width.value,height.value)
                g.font = scalafx.scene.text.Font.font(20)
                g.setFill(Color.Black)
                g.strokeRect(120,55,70,15)
                g.fillText("  ♡ x "+lifeRemaining,0,30)
                g.fillText("Score:"+deadEneCount*10,0,50)
                g.fillText("Time Machine",0,70)
                g.setFill(Color.MediumVioletRed)
                g.fillRect(120,55,70,15)

                player.display(g,narW,narH)
                enemies.display(g)
                enemies.beStatic()
                //
                var deadCount:Int = 0
                eneBulCount -= 0.25
                //
                var eneBulToAdd = mutable.ListBuffer.empty[Bullet]
                if(eneBulCount==0){
                    eneBulToAdd += enemies.shoot()
                    eneBulCount = 20
                }
               
                if (KeyLog("LEFT") == true||KeyLog("A")==true){
                    if(player.x>=10){
                    player.moveLeft()
                    }
                }
                if (KeyLog("RIGHT") == true||KeyLog("D")==true){
                    if(player.x<=width.value-narW-20){
                    player.moveRight()
                    }
                }
                if (KeyLog("UP") == true||KeyLog("W")==true){
                    if(player.y>=0){
                    player.moveUp()
                    }
                }
                if (KeyLog("DOWN") == true||KeyLog("S")==true){
                    if(player.y<=height.value-narH){
                    player.moveDown()
                    }
                }
                if (KeyLog("SPACE") == true && pBuls.length == 0){
                    pBuls += player.shoot()
                }
                //can create a for loop to eliminate the overranged bullets
                for (i<- pBuls){
                    if(i.y>=0){
                        i.timeStep()
                        i.display(g,bulW,bulH)
                    } else {
                        pBulsToRemove += i
                    }
                }
                
                for (i<- eBuls){
                    if(i.y>=0){
                        i.timeStep()
                        i.display(g,shuW,shuH)
                    } else {
                        eBulsToRemove += i
                    }
                }
                for (p<- pBuls){
                    for(e <- eBuls){
                        p.intersects(bulW,bulH,shuW,shuH,new Vec2(e.x,e.y))
                        if(p.intersection==true){
                            eBulsToRemove += e
                            pBulsToRemove += p
                            }
                        }
                    } 
                    
                    
                for (e <- eBuls){
                    player.intersects(narW-10,narH,shiW-15,shiH-10,new Vec2(e.x,e.y))
                    if(player.intersection==true){
                        player.moveTo(Vec2(520,550))
                        eBulsToRemove += e
                        player.intersection = false
                        deadCount += 1   
                    }
                }
                for (e <- enemies.swarm){
                    player.intersects(narW-10,narH,shiW-15,shiH-10,new Vec2(e.x,e.y))
                    if(player.intersection==true){
                        player.moveTo(Vec2(520,550))
                        player.intersection = false
                        deadCount += 1   
                         
                    }
                    
                }
               
                for (e <- enemies.swarm){
                    for (p <- pBuls){
                    e.intersects(shiW,shiH-20,bulW,bulH,Vec2(p.x(),p.y()))
                    if(e.intersection == true){
                        eToRemove += e
                        pBulsToRemove += p
                        deadEneCount += 1
                    }
                    } 
                }
                
                for(e <- enemies.swarm){
                    if(motionCounter>0){
                    e.move(downMotion)
                    motionCounter -= 1
                    } else {
                    motionCounter = 300
                        downMotion *= -1
                    }
                }
                
                
                if(enemies.swarm.length==0){
                    enemies = new EnemySwarm(2,4)
                }
                enemies.arr = Array.range(0,enemies.swarm.length)
                enemies.swarm --= eToRemove
                pBuls --= pBulsToRemove
                eBuls --= eBulsToRemove ++= eneBulToAdd
                lifeRemaining -= deadCount 
                
                saveGameState()
                stateLength += 1
                
                /*if (KeyLog("R") == true){
                    recoverGameState()
                    
                }*/
            //Dont touch the line below this cause these are ending braces        
            } else if(showStartScreen == false && lifeRemaining != 0 && KeyLog("R")==true){
                if(gameState.isEmpty==false){
                recoverGameState()
                g.drawImage(Images.Background,0,0,width.value,height.value)
                g.font = scalafx.scene.text.Font.font(20)
                g.setFill(Color.Black)
                g.strokeRect(120,55,70,15)
                g.fillText("  ♡ x "+lifeRemaining,0,30)
                g.fillText("Score:"+deadEneCount*10,0,50)
                g.fillText("Time Machine",0,70)
                g.setFill(Color.MediumVioletRed)
                g.fillRect(120,55,70*(gameState.length.toDouble/stateLength),15)


                player.display(g,narW,narH)
                enemies.display(g)
                player.display(g,narW,narH)
                for (i<- pBuls){
                        i.display(g,bulW,bulH)
                }
                for (i<- eBuls){
                        i.display(g,bulW,bulH)
                }
            }else {
                stateLength = 0
            }

            }   else if(showStartScreen == true && lifeRemaining != 0) {
                g.drawImage(Images.village,0,0,width.value,height.value)
                g.globalAlpha = globalAlphaVal
                g.drawImage(Images.gameStartTitle,170,150)
                g.drawImage(Images.gameSub,240,420)
                globalAlphaVal += 0.025
                if(globalAlphaVal>=1) globalAlphaVal = 0
                if(KeyLog("P") == true) {
                showStartScreen = false
                g.globalAlpha = 1

            }
            } else {
                //show end screen
                g.globalAlpha = globalAlphaVal
                g.drawImage(Images.gameEndBack,0,0,width.value,height.value)
                g.drawImage(Images.gameOver,210,530)
                g.drawImage(Images.newGame,940,50)
                globalAlphaVal += 0.025
                g.font = scalafx.scene.text.Font.font(20)
                g.fillText("ENEMIES KILLED:",20,50)
                g.setFont(Font.font(70))
                g.fillText(deadEneCount.toString(),120,230)
                if(globalAlphaVal>=1) globalAlphaVal = 0
                if(KeyLog("N") == true) {
                player.moveTo(Vec2(width.value/2-narW/2, height.value - narH))
                lifeRemaining = 3
                g.globalAlpha = 1
                deadEneCount = 0
                eneBulCount = 20
                enemies = new EnemySwarm(2,4)
                }
                
                
            }





            //The end of the timer is beyond this line
            }}
            timerAll.start()
        }
   
    }

}