package migu.project.remember.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostForm {

    @NotEmpty(message = "title is required")
    @Size(min=1, max=30)
    String title;

    @NotEmpty(message = "contents is required")
    @Size(min=1)
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

    @Override
    public String toString() {
        return "PostForm{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
