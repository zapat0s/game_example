package game.event

import game.state.{Entity, Living, Vec2i}

trait StateTransition[-T] {
  def applyTo(entity: T)
}

trait InstantStateTransition[T] extends StateTransition[T] // decide can only return instant state transitions

case object DrawWeapon extends InstantStateTransition[Living] {
  def applyTo(living: Living) {
    living.weaponDrawn = true
  }
}

case object PutAwayWeapon extends InstantStateTransition[Living] {
  def applyTo(living: Living) = {
    living.weaponDrawn = false
  }
}

case object BeginAttack extends InstantStateTransition[Living] {
  def applyTo(living: Living) = {
    living.attacking = true
  }
}

case class ChangeFacing(facing: Vec2i) extends InstantStateTransition[Living] {
  def applyTo(living: Living) = {
    living.facing := facing
  }
}

case object Death extends InstantStateTransition[Entity] {
  def applyTo(entity: Entity) {
    entity.alive = false
  }
}

trait SimulatedStateTransition[T] extends StateTransition[T] // only update is allowed to return simulated state transitions (it can also return instants)

case class ChangePosition(position: Vec2i) extends SimulatedStateTransition[Entity] {
  def applyTo(entity: Entity) {
    entity.position := position
  }
}
