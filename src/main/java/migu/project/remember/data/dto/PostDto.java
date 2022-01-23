package migu.project.remember.data.dto;

public class PostDto {
    String title;
    String contents;
    String membId;
    public PostDto() {
    }

    public PostDto(String title, String contents, String membId) {
        this.title = title;
        this.contents = contents;
        this.membId = membId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getMembId() {
        return membId;
    }


    @Override
    public String toString() {
        return "PostDto{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", membId='" + membId + '\'' +
                '}';
    }
}
