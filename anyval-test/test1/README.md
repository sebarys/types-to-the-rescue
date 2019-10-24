Steps:

1. `scalac Test1.scala` - compile classes
2. `javap Test` - disasseble Test object
3. `javap WrapperOnUserId` - disasseble WrapperOnUserId, there is String no UserId
4. `javap WrapperOnUserIdentifier.class` - disasseble WrapperOnUserIdentifier, there is UserIdentifier so we're boxing String in our domain class
5. `javap ClassWithKeyAsUserId.class` - disasseble ClassWithKeyAsUserId, map key is String
6. `javap ClassWithKeyAsUserIdentifier.class`- disasseble ClassWithKeyAsUserIdentifier, map key is UserIdentifier, again we're boxing String in our domain class
