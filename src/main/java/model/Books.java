package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Books {

    private int id;
    private String name;
    private String author;
    private int pages;

    public Books(String name, String author, int pages) {
        this.name = name;
        this.author = author;
        this.pages = pages;
    }
}
