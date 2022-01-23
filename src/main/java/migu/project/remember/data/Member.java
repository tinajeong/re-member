package migu.project.remember.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "memb_id", updatable = false)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String membId;

    @OneToMany(mappedBy = "member")
    private List<Post> post = new ArrayList<Post>();


    @Override
    public String toString() {
        return "Member{" +
                "membId='" + membId + '\'' +
                ", post=" + post +
                '}';
    }
}
