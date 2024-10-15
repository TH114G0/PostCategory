package br.com.crud.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "post")
@Data
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El título no puede estar vacío")
    @NotNull(message = "El título no puede ser nulo")
    private String title;

    @NotEmpty(message = "El contenido no puede estar vacío")
    @NotNull(message = "El contenido no puede ser nulo")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    public PostModel() {}

    @Override
    public String toString() {
        return "PostModel{title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}