import com.mtyoung.entity.Address
import com.mtyoung.persistence.AddressDao
import com.mtyoung.persistence.StateDao
import com.mtyoung.persistence.UserDao
import com.mtyoung.util.EmailMessage
import com.mtyoung.entity.User

import spock.lang.Shared
import spock.lang.Specification


class EmailMessageTest extends Specification {

    @Shared
    UserDao userDao

    @Shared
    AddressDao mailDao

    @Shared
    User bob1

    @Shared
    User bob2

    @Shared
    EmailMessage email

    @Shared
    int newMail = 0

    @Shared
    int newUser1 = 0

    @Shared
    int newUser2 = 0

    def setup() {
        given:"Create Defaults"
        mailDao = new AddressDao()
        userDao = new UserDao()
        def stateDao = new StateDao()

        def mail = [streetaddress1: "605 Park Street", streetaddress2: "",
                    city:"Watertown", state:stateDao.getState(49), zipcode:53098] as Address

        newMail = mailDao.addAddress(mail)

        bob1 = [fname: "Mike", lname:"Young",address:mail,
                    user_name:"mtyoung@madisoncollege.edu", cellnumber:"4444444444",
                    reminderthreshold:1, defaultrentalperiod:3, password:"Password"] as User

        newUser1 = userDao.addUser(bob1)

        bob2 = [fname: "John", lname:"Smith",address:mail,
                    user_name:"mike.young@charter.com", cellnumber:"3333333333",
                    reminderthreshold:1555, defaultrentalperiod:39, password:"Password"] as User

        newUser2 = userDao.addUser(bob2)

        email = new EmailMessage()
    }

    def "did that email actually send?"() {
        expect:
        bob1.user_name == "mtyoung@madisoncollege.edu"
        bob2.user_name == "mike.young@charter.com"
        email.sendFriendRequest(bob1, bob2)
    }

    def cleanup() {
        if (newUser2 != 0) {
            userDao.deleteUser(newUser2)
        }

        if (newUser1 != 0) {
            userDao.deleteUser(newUser1)
        }

        if (newMail != 0) {
            mailDao.deleteAddress(newMail)
        }
    }
}