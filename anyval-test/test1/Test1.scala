class UserId(val value: String) extends AnyVal

class ClassWithKeyAsUserId(map: Map[UserId, String])

class WrapperOnUserId(userId: UserId)