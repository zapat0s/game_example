package game.state

import scala.collection.mutable

abstract class Entity(val position:Vec2i) {
  var alive = true // needed to track an object being picked up while still processing all of it's events

  val events = new mutable.Queue[game.event.ToEntityEvent]()

  def glyph : Char
}

case class Living(pos:Vec2i, var health:Int = 100) extends Entity(pos) {
  val facing = Vec2i(1, 0)
  var weaponDrawn = false
  var attacking = false

  var itemInHand : Option[Int] = None

  def glyph = 0x263a

  def holdingItem = itemInHand.isDefined

}

class Item(pos:Vec2i, val itemType:Int) extends Entity(pos) {
  def glyph = 0x2663

}

object Item {
  val sword = 0
  val crate = 1
}