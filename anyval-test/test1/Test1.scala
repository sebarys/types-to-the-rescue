class UserId(val value: String) extends AnyVal

class ClassWithKeyAsUserId(map: Map[UserId, String])

class WrapperOnUserId(userId: UserId)

object Test {

  val testUserId = new UserId("test")

  val x = new ClassWithKeyAsUserId(Map(testUserId -> "test"))
  val y = new WrapperOnUserId(testUserId)
}