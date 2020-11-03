package com.lms.domain;

import com.lms.domain.enumaration.BookCategory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created By CL Verma on 10/4/20
 */

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "subject")
    private String subject;

    @NotNull
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private BookCategory category;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(subject, book.subject) &&
                Objects.equals(author, book.author) &&
                Objects.equals(category, book.category) &&
                Objects.equals(publicationDate, book.publicationDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, subject, author, category, publicationDate);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
