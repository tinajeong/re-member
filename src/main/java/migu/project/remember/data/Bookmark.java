package migu.project.remember.data;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookmark_id", updatable = false)
    Integer bookmarkId;

    @ManyToOne(targetEntity = Member.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="membId")
    private Member member;

    @Basic(optional = false)
    @Column(name = "createdAt", insertable = false, updatable = false,  columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    @OneToOne(targetEntity = Post.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="postId")
    private Post post;
}
