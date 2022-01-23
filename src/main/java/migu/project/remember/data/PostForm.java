package migu.project.remember.data;

public class PostForm {
    String title;
    String contents;
    String category;

    public PostForm(String title, String contents, String category) {
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

    public String getCategory() {
        return category;
    }
}
