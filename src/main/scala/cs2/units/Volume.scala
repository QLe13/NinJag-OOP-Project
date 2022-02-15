package cs2.units

class Volume(private var lit:Double = 0.0) {
  //Field is in the argument list above - the volume stored in LITERS

  //Basic math operators to add, subtract, and scale volumes
  def + (other:Volume):Volume = {
  if(this.lit>0&&other.lit>0) new Volume(this.lit + other.lit)
  else this
  }
  
  def += (other:Volume):Unit  = { 
    def lit_=(n:Double):Unit= {
      lit = lit + n
    }
    if(other.lit>0&&this.lit>0)lit_=(other.lit)
  }

  def - (other:Volume):Volume = {
    if(other.lit < this.lit && this.lit>0 && other.lit>0) {
    new Volume(this.lit - other.lit)
    } else {
      this
    }
  }
  def -= (other:Volume):Unit  = {
    def lit_=(n:Double):Unit= {
      lit = lit - n
    }
    if(this.lit>other.lit&&this.lit>0&&other.lit>0)lit_=(other.lit)
  }

  def * (scalar:Double):Volume = {
    if(scalar >0&&this.lit>0) {new Volume(this.lit * scalar)}
    else {this}
  }
  def *= (scalar:Double):Unit  = {
    if(scalar>0&&this.lit>0)
    {def lit_=(n:Double):Unit= {
      lit = lit * n
    }
    lit_=(scalar)
  }
  }

  def / (scalar:Double):Volume = {
    if(scalar >0 && this.lit>0) {new Volume(this.lit / scalar)}
    else {this}
  }
  def /= (scalar:Double):Unit  = {
     if(scalar>0 && this.lit>0)
    {def lit_=(n:Double):Unit= {
      lit = lit / n
    }
    lit_=(scalar)
    }
  }

  //Getter functions that return in a variety of units
  def liters():Double = {
    lit
  }
  def milliliters():Double = {
    lit * 1000
  }
  def gallons():Double = {
    lit * 0.264172
  }
  def quarts():Double = {
    lit*1.05669
  }
  def pints():Double = {
    lit * 2.11338
  }
  def cups():Double = {
    lit * 4.22675
  }
  def teaspoons():Double = {
    lit * 202.884
  }
  def tablespoons():Double = {
    lit * 67.628
  }
}


object Volume {
  //"Constructor" apply methods operating in liters
  def apply():Volume = {
    new Volume(0)
  }
  def apply(amt:Double):Volume = {
    new Volume(amt)
  }

  //Alternative "static" methods to create volumes in other units
  def liters(amt:Double):Volume = {
    val litVol = Volume(amt)
    litVol
  } //identical to an apply method
  def milliliters(amt:Double):Volume = {
    val milVol = Volume(amt/1000)
    milVol
  }
  def gallons(amt:Double):Volume = {
    val galVol = Volume(amt/0.264172)
    galVol
  }
  def quarts(amt:Double):Volume = {
    val quaVol = Volume(amt/1.05669)
    quaVol
  }
  def pints(amt:Double):Volume = {
    val pinVol = Volume(amt/2.11338)
    pinVol
  }
  def cups(amt:Double):Volume = {
    val cupVol = Volume(amt/4.22675)
    cupVol
  }
  def teaspoons(amt:Double):Volume = {
    val teaVol = Volume(amt/202.884)
    teaVol
  }
  def tablespoons(amt:Double):Volume = {
    val tabVol = Volume(amt/67.628)
    tabVol
  }
  def main(args:Array[String]) {
    println(tablespoons(12).liters())
    println(gallons(35).liters())
}
}


