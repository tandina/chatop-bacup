package com.chatopreview.api.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.chatopreview.api.api.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Number surface;
    private Number price;
    private String picture;
    private String description;
    @ManyToOne
    @JsonBackReference
    private User owner;
    public int getOwnerId() {
        return this.owner.getId();
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surface=" + surface +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @CreatedDate
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @LastModifiedDate
    private Date updatedAt;

}
