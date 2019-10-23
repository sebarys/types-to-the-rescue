case class UserId(value: String) extends AnyVal

class VectorOfUserIdWrapper(vectorOfUserId: Vector[UserId])

object Test {

  def test(something: Any): UserId = {
    if(something.isInstanceOf[UserId]) {
      something.asInstanceOf[UserId]
    } else {
      new UserId("other")
    }
  }

  val userIdToExperiment = UserId("testVal")

  val testRes1 = test(userIdToExperiment)
  val testRes2 = test(4)

  val t =  new VectorOfUserIdWrapper(Vector(userIdToExperiment, userIdToExperiment, userIdToExperiment))
}