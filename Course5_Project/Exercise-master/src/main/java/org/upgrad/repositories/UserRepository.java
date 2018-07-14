package  org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Notification;
import org.upgrad.models.User;
import org.upgrad.models.UserProfile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query(nativeQuery = true, value = "select * from users where upper(userName)= upper(?1)")
    String findUsername(String userName) ;


    @Query(nativeQuery = true,value="select role from users where upper(userName)= upper(?1)")
    String getUserRole(String userName);

    @Query(nativeQuery = true,value = "select * from users where upper(email)= upper(?1)")
    String findUserEmail(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into users (username,email,password) values (?1,?2,?3)")
    void addUserCredentials(String userName, String email, String password);

    @Query(nativeQuery = true,value = "select password from users where upper(username)= upper(?1)")
    String findUserPassword(String username);

    @Query(nativeQuery = true,value = "select id from users where upper(userName)= upper(?1)")
    int findUserID(String username);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from users where id=?1 ")
    void deleteUserById(int id);

    @Query(nativeQuery = true,value="select id,username,email from Users")
    List<Map> getUserDetails();

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into category (title,description) values (?1,?2)")
    void addCategory(String title, String description);

}
