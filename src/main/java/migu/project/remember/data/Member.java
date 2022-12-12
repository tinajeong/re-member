package migu.project.remember.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "memb_id", updatable = false)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String membId;

    @Override
    public String toString() {
        return "Member{" +
                "membId='" + membId + '\'' +
                '}';
    }
}
