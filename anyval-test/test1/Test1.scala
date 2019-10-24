class UserId(val value: String) extends AnyVal
class UserIdentifier(val value: String)

class ClassWithKeyAsUserId(map: Map[UserId, String])
class ClassWithKeyAsUserIdentifier(map: Map[UserIdentifier, String])

class WrapperOnUserId(userId: UserId)
class WrapperOnUserIdentifier(userId: UserIdentifier)

object Test {

  val testUserId = new UserId("test")

  val testClassWithKeyAsUserId = new ClassWithKeyAsUserId(Map(testUserId -> "test"))
  val testWrapperOnUserId = new WrapperOnUserId(testUserId)

  val testUserIdentifier = new UserIdentifier("test")
  val testClassWithKeyAsUserIdentifier = new ClassWithKeyAsUserIdentifier(Map(testUserIdentifier -> "test"))
  val testWrapperOnUserIdentifier = new WrapperOnUserIdentifier(testUserIdentifier)
}