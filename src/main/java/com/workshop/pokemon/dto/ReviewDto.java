package com.workshop.pokemon.dto;

public class ReviewDto {
    private Long id;
    private String title;
    private String content;
    private int star;

    public ReviewDto() {}

    public ReviewDto(Long id, String title, String content, int star) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.star = star;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", star=" + star +
                '}';
    }
}
