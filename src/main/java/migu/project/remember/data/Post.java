package migu.project.remember.data;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.time.LocalDateTime;

@Entity
@Table(name="post")
public class Post {

    @Column
    String title;

    @Column
    String contents;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "post_id", updatable = false)
    String postId;

    @ManyToOne(targetEntity = Member.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="membId")
    private Member member;


    @Basic(optional = false)
    @Column(name = "createdAt", insertable = false, updatable = false,  columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="category")
    private String category;


    public Post() {
    }

    public Post(String title, String contents, String category) {
        this.title = title;
        this.contents = contents;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getPostId() {
        return postId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", postId='" + postId + '\'' +
                ", member=" + member +
                ", createdAt=" + createdAt +
                ", category='" + category + '\'' +
                '}';
    }
}
