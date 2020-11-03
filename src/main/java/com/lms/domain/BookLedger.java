package com.lms.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created By CL Verma on 10/4/20
 */

@Entity
@Table( name = "book_ledger")
public class BookLedger implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne( fetch = FetchType.EAGER)
    private Book book;

    @NotNull
    @ManyToOne( fetch = FetchType.EAGER)
    private Member member;

    @Column( name = "issued_date", nullable = false)
    private LocalDateTime issuedDate;

    @Column( name = "received_date")
    private LocalDateTime receivedDate;

    @Column( name = "status", nullable = false)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLedger that = (BookLedger) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(book, that.book) &&
                Objects.equals(member, that.member) &&
                Objects.equals(issuedDate, that.issuedDate) &&
                Objects.equals(receivedDate, that.receivedDate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, book, member, issuedDate, receivedDate, status);
    }

    @Override
    public String toString() {
        return "BookLedger{" +
                "id=" + id +
                ", book=" + book +
                ", member=" + member +
                ", issuedDate=" + issuedDate +
                ", receivedDate=" + receivedDate +
                ", status='" + status + '\'' +
                '}';
    }
}
